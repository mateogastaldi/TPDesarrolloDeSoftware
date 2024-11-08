/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.vendedores;

import DAO.FACTORY.DAOFactory;
import exceptions.vendedor.VendedorNoEncontradoException;
import gui.ButtonColumn;
import gui.cliente.InterfazClientes;
import gui.itemMenu.InterfazItemsMenu;
import gui.pedido.InterfazPedidos;
import tp.Vendedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mateo
 */
public class InterfazVendedores extends javax.swing.JFrame {
    public void mostrar(String nombre){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("ID");
        model.addColumn("Pais");
        model.addColumn("Ciudad");
        model.addColumn("Calle");
        model.addColumn("Altura");
        model.addColumn("Editar");
        model.addColumn("Eliminar");

        //Acciones de los botones de la tabla
        Action actionEditar = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                // Obtiene el ID del vendedor desde la tabla en la columna correspondiente
                Object vendedorId = table.getModel().getValueAt(modelRow, 1); // Columna "ID"

                // Recupera los datos completos del vendedor con el ID obtenido
                Vendedor vendedor = DAOFactory.getInstance().getVendedorDAO().filtrarVendedorPorId((int) vendedorId);

                // Crea y muestra una nueva interfaz para editar los datos del vendedor
                if (vendedor != null) {
                    InterfazVendedoresEditar interfazVendedoresEditar = new InterfazVendedoresEditar(vendedor);
                    interfazVendedoresEditar.setVisible(true);
                }
            }
        };
        Action actionEliminar = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                // Obtiene el ID del VENDEDOR desde la tabla en la columna correspondiente
                Object vendedorID = table.getModel().getValueAt(modelRow, 1); // Columna "ID"

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "¿Deseas eliminar el vendedor?",
                        "Confirmación de eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if(confirm == JOptionPane.YES_OPTION){

                    try{
                        DAOFactory.getInstance().getVendedorDAO().eliminarVendedor((int) vendedorID);


                    }catch (VendedorNoEncontradoException ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
            };
        };
        if(nombre != null){
            try{
                List<Vendedor> vendedors = DAOFactory.getInstance().getVendedorDAO().filtrarVendedorPorNombre(nombre);
                Iterator<Vendedor> iterator = vendedors.iterator();
                while(iterator.hasNext()){
                    Vendedor vendedor = iterator.next();
                    model.addRow(new Object[]{
                            vendedor.getNombre(),
                            vendedor.getId(),
                            vendedor.getDireccion().getPais(),
                            vendedor.getDireccion().getCiudad(),
                            vendedor.getDireccion().getCalle(),
                            vendedor.getDireccion().getAltura()
                    });


                }

            }catch(VendedorNoEncontradoException e){
                JOptionPane.showMessageDialog(null, e.getMessage());

            }
        }
        else{
            try{
                List<Vendedor> vendedores = DAOFactory.getInstance().getVendedorDAO().getVendedores();
                Iterator<Vendedor> iterator = vendedores.iterator();
                while(iterator.hasNext()){
                    Vendedor vendedor = iterator.next();
                    model.addRow(new Object[]{
                            vendedor.getNombre(),
                            vendedor.getId(),
                            vendedor.getDireccion().getPais(),
                            vendedor.getDireccion().getCiudad(),
                            vendedor.getDireccion().getCalle(),
                            vendedor.getDireccion().getAltura()
                    });


                }

            }catch(VendedorNoEncontradoException e){
                JOptionPane.showMessageDialog(null, e.getMessage());

            }
        }
        // Crear la tabla con el modelo
        tablaVendedores.setModel(model);
        ButtonColumn buttonColumnEditar = new ButtonColumn(tablaVendedores,actionEditar,6);
        ButtonColumn buttonColumnEliminar = new ButtonColumn(tablaVendedores,actionEliminar,7);





    }


    /**
     * Creates new form InterfazVendedores
     */
    public InterfazVendedores() {
        initComponents();
    }

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
        jButton1 = new javax.swing.JButton();
        botonItemsMenu = new javax.swing.JButton();
        botonPedidos = new javax.swing.JButton();
        botonClientes = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVendedores = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        botonCrearVendedor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        buscadorNombre = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(222, 222, 222));

        jPanel2.setBackground(new java.awt.Color(222, 222, 222));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 128)));

        jButton1.setBackground(new java.awt.Color(65, 105, 225));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton1.setText("Vendedores");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        botonItemsMenu.setBackground(new java.awt.Color(65, 105, 225));
        botonItemsMenu.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        botonItemsMenu.setText("ItemMenus");
        botonItemsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonItemsMenuActionPerformed(evt);
            }
        });

        botonPedidos.setBackground(new java.awt.Color(65, 105, 225));
        botonPedidos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        botonPedidos.setText("Pedidos");
        botonPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPedidosActionPerformed(evt);
            }
        });

        botonClientes.setBackground(new java.awt.Color(65, 105, 225));
        botonClientes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        botonClientes.setText("Clientes");
        botonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonItemsMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonPedidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(botonItemsMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(222, 222, 222));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 128)));

        mostrar(null);
        jScrollPane1.setViewportView(tablaVendedores);

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(155, 155, 155));
        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Lista de Vendedores");

        botonCrearVendedor.setBackground(new java.awt.Color(65, 105, 225));
        botonCrearVendedor.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        botonCrearVendedor.setText("Crear Nuevo Vendedor");
        botonCrearVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearVendedorActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(222, 222, 222));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Buscador:");

        buscadorNombre.setBackground(new java.awt.Color(222, 222, 222));
        buscadorNombre.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        buscadorNombre.setForeground(new java.awt.Color(155, 155, 155));
        buscadorNombre.setText("Ingrese el nombre del vendedor...");
        buscadorNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscadorNombreActionPerformed(evt);
            }
        });

        botonBuscar.setBackground(new java.awt.Color(65, 105, 225));
        botonBuscar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        botonBuscar.setText("Buscar");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(botonCrearVendedor)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscadorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonBuscar)
                        .addGap(20, 20, 20))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCrearVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscadorNombre)
                    .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
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



    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botonCrearVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearVendedorActionPerformed
        // TODO add your handling code here:
        InterfazVendedoresCrear interfazVendedoresCrear = new InterfazVendedoresCrear();
        interfazVendedoresCrear.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonCrearVendedorActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        // TODO add your handling code here:
        if(buscadorNombre.getText().equals("")){
            mostrar(null);
        }else{
            mostrar(buscadorNombre.getText());
        }
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void botonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonClientesActionPerformed
        // TODO add your handling code here:
        InterfazClientes interfazClientes = new InterfazClientes();
        interfazClientes.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonClientesActionPerformed

    private void botonItemsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonItemsMenuActionPerformed
        // TODO add your handling code here:
        InterfazItemsMenu itemsMenu = new InterfazItemsMenu();
        itemsMenu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonItemsMenuActionPerformed

    private void botonPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPedidosActionPerformed
        // TODO add your handling code here:
        InterfazPedidos interfazPedidos = new InterfazPedidos();
        interfazPedidos.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonPedidosActionPerformed

    private void buscadorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscadorNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscadorNombreActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazVendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazVendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazVendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazVendedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazVendedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonClientes;
    private javax.swing.JButton botonCrearVendedor;
    private javax.swing.JButton botonItemsMenu;
    private javax.swing.JButton botonPedidos;
    private javax.swing.JTextField buscadorNombre;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tablaVendedores;
    // End of variables declaration//GEN-END:variables
}
