/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.itemMenu;

import DAO.FACTORY.DAOFactory;
import exceptions.cliente.ClienteNoEncontradoException;
import exceptions.itemMenu.ItemMenuNoEncontradoException;
import gui.ButtonColumn;
import gui.cliente.InterfazClientes;
import gui.itemMenu.categoria.InterfazCategoria;
import gui.pedido.InterfazPedidos;
import gui.vendedores.InterfazVendedores;
import tp.Cliente;
import tp.ItemMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mateo
 */
public class InterfazItemsMenu extends javax.swing.JFrame {

    public InterfazItemsMenu() {initComponents();}

    public void mostrar(String nombre){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("ID");
        model.addColumn("Precio");
        model.addColumn("Vendedor");
        model.addColumn("Categoria");
        model.addColumn("Apto Vegano");
        model.addColumn("Apto Celiaco");
        model.addColumn("Editar");
        model.addColumn("Eliminar");

        //Acciones de los botones de la tabla
        Action actionEditar = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                // Obtiene el ID del item desde la tabla en la columna correspondiente
                Object itemId = table.getModel().getValueAt(modelRow, 1); // Columna "ID"

                // Recupera los datos completos del item con el ID obtenido
                ItemMenu itemMenu = DAOFactory.getInstance().getItemsMenuDAO().filtrarItemMenuPorId((int) itemId);


                // Crea y muestra una nueva interfaz para editar los datos del cliente
                if (itemMenu != null) {
                    InterfazItemsMenuEditar editarItem = new InterfazItemsMenuEditar(itemMenu);
                    editarItem.setVisible(true);
                }
            }
        };
        Action actionEliminar = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                // Obtiene el ID del item desde la tabla en la columna correspondiente
                Object itemId = table.getModel().getValueAt(modelRow, 1); // Columna "ID"

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "¿Deseas eliminar el item?",
                        "Confirmación de eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if(confirm == JOptionPane.YES_OPTION){

                    try{
                        DAOFactory.getInstance().getItemsMenuDAO().eliminarItemMenu((int) itemId);
                        if(nombre == null){mostrar(null);}
                        else{mostrar(nombre);}


                    }catch (ItemMenuNoEncontradoException ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
            };
        };



        if(nombre != null){
            try{
                List<ItemMenu> itemMenus = DAOFactory.getInstance().getItemsMenuDAO().filtrarItemMenuPorNombre(nombre);
                Iterator<ItemMenu> iterator = itemMenus.iterator();
                while(iterator.hasNext()){
                    ItemMenu itemMenu = iterator.next();
                    model.addRow(new Object[]{
                            itemMenu.getNombre(),
                            itemMenu.getId(),
                            itemMenu.getPrecio(),
                            itemMenu.getVendedor().getNombre(),
                            itemMenu.getCategoria().getClass().getSimpleName(),
                            itemMenu.getAptoVegano(),
                            itemMenu.getAptoCeliaco(),

                    });


                }

            }catch(ItemMenuNoEncontradoException e){
                JOptionPane.showMessageDialog(null, e.getMessage());

            }
        }
        else{
            try{
                List<ItemMenu> itemMenus = DAOFactory.getInstance().getItemsMenuDAO().getItemMenus();
                Iterator<ItemMenu> iterator = itemMenus.iterator();
                while(iterator.hasNext()){
                    ItemMenu itemMenu = iterator.next();
                    model.addRow(new Object[]{
                            itemMenu.getNombre(),
                            itemMenu.getId(),
                            itemMenu.getPrecio(),
                            itemMenu.getVendedor().getNombre(),
                            itemMenu.getCategoria().getTipoItem().getSimpleName(),
                            itemMenu.getAptoVegano(),
                            itemMenu.getAptoCeliaco(),

                    });


                }

            }catch(ItemMenuNoEncontradoException e){
                JOptionPane.showMessageDialog(null, e.getMessage());

            }
        }
        // Crear la tabla con el modelo
        tablaItemsMenu.setModel(model);
        ButtonColumn buttonColumnEditar = new ButtonColumn(tablaItemsMenu,actionEditar,7);
        ButtonColumn buttonColumnEliminar = new ButtonColumn(tablaItemsMenu,actionEliminar,8);





    }
    /**
     * Creates new form InterfazVendedores
     */


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        BotonVendedores = new javax.swing.JButton();
        BotonItemMenu = new javax.swing.JButton();
        BotonPedidos = new javax.swing.JButton();
        BotonCliente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaItemsMenu = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        CrearNuevoBebida = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BuscadorDeItemMenu = new javax.swing.JTextField();
        BuscarBoton = new javax.swing.JButton();
        CrearNuevaCategoria = new javax.swing.JButton();
        CrearNuevoPLato1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(222, 222, 222));

        jPanel2.setBackground(new java.awt.Color(222, 222, 222));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 128)));

        BotonVendedores.setBackground(new java.awt.Color(65, 105, 225));
        BotonVendedores.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BotonVendedores.setText("Vendedores");
        BotonVendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonVendedoresActionPerformed(evt);
            }
        });

        BotonItemMenu.setBackground(new java.awt.Color(65, 105, 225));
        BotonItemMenu.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BotonItemMenu.setText("ItemMenus");

        BotonPedidos.setBackground(new java.awt.Color(65, 105, 225));
        BotonPedidos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BotonPedidos.setText("Pedidos");
        BotonPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonPedidosActionPerformed(evt);
            }
        });

        BotonCliente.setBackground(new java.awt.Color(65, 105, 225));
        BotonCliente.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BotonCliente.setText("Clientes");
        BotonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotonVendedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BotonItemMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BotonPedidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BotonCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(BotonVendedores, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(BotonItemMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(222, 222, 222));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 128)));

        mostrar(null);
        jScrollPane1.setViewportView(tablaItemsMenu);

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(155, 155, 155));
        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Lista de Items Menu");

        CrearNuevoBebida.setBackground(new java.awt.Color(65, 105, 225));
        CrearNuevoBebida.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        CrearNuevoBebida.setText("Crear Nueva Bebida");
        CrearNuevoBebida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearNuevoBebidaActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(222, 222, 222));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Buscador:");

        BuscadorDeItemMenu.setBackground(new java.awt.Color(222, 222, 222));
        BuscadorDeItemMenu.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        BuscadorDeItemMenu.setForeground(new java.awt.Color(155, 155, 155));

        BuscarBoton.setBackground(new java.awt.Color(65, 105, 225));
        BuscarBoton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BuscarBoton.setText("Buscar");
        BuscarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarBotonActionPerformed(evt);
            }
        });

        CrearNuevaCategoria.setBackground(new java.awt.Color(65, 105, 225));
        CrearNuevaCategoria.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        CrearNuevaCategoria.setText("Categorias");
        CrearNuevaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearNuevaCategoriaActionPerformed(evt);
            }
        });

        CrearNuevoPLato1.setBackground(new java.awt.Color(65, 105, 225));
        CrearNuevoPLato1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        CrearNuevoPLato1.setText("Crear Nuevo Plato");
        CrearNuevoPLato1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearNuevoPLato1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CrearNuevoPLato1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CrearNuevoBebida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BuscadorDeItemMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CrearNuevaCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BuscarBoton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuscadorDeItemMenu)
                    .addComponent(BuscarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CrearNuevoPLato1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CrearNuevaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CrearNuevoBebida, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void BotonVendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonVendedoresActionPerformed
        // TODO add your handling code here:
        InterfazVendedores interfazVendedores = new InterfazVendedores();
        interfazVendedores.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonVendedoresActionPerformed

    private void CrearNuevoBebidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearNuevoBebidaActionPerformed
        // TODO add your handling code here:
        InterfazItemMenuCrearBebida interfazItemMenuCrearBebida = new InterfazItemMenuCrearBebida();
        interfazItemMenuCrearBebida.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_CrearNuevoBebidaActionPerformed

    private void BuscarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarBotonActionPerformed
        // TODO add your handling code here:
        if(BuscadorDeItemMenu.getText().equalsIgnoreCase("")){mostrar(null);}
        else{mostrar(BuscadorDeItemMenu.getText());}
    }//GEN-LAST:event_BuscarBotonActionPerformed

    private void BotonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonClienteActionPerformed
        // TODO add your handling code here:
        InterfazClientes interfazClientes = new InterfazClientes();
        interfazClientes.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonClienteActionPerformed

    private void BotonPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonPedidosActionPerformed
        // TODO add your handling code here:
        InterfazPedidos interfazPedidos = new InterfazPedidos();
        interfazPedidos.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonPedidosActionPerformed

    private void CrearNuevaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearNuevaCategoriaActionPerformed
        // TODO add your handling code here:
        InterfazCategoria interfazCategoria = new InterfazCategoria();
        interfazCategoria.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_CrearNuevaCategoriaActionPerformed

    private void CrearNuevoPLato1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearNuevoPLato1ActionPerformed
        // TODO add your handling code here:
        InterfazItemMenuCrearPlato interfazItemMenuCrearPlato = new InterfazItemMenuCrearPlato();
        interfazItemMenuCrearPlato.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_CrearNuevoPLato1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazItemsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazItemsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazItemsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazItemsMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazItemsMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonCliente;
    private javax.swing.JButton BotonItemMenu;
    private javax.swing.JButton BotonPedidos;
    private javax.swing.JButton BotonVendedores;
    private javax.swing.JTextField BuscadorDeItemMenu;
    private javax.swing.JButton BuscarBoton;
    private javax.swing.JButton CrearNuevaCategoria;
    private javax.swing.JButton CrearNuevoBebida;
    private javax.swing.JButton CrearNuevoPLato1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tablaItemsMenu;
    // End of variables declaration//GEN-END:variables
}
