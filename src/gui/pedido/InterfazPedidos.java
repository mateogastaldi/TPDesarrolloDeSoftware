/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.pedido;

import controller.PedidosController;
import exceptions.Pedido.PedidoNoEncontradoException;
import gui.ButtonColumn;
import gui.cliente.InterfazClientes;
import gui.itemMenu.InterfazItemsMenu;
import gui.vendedores.InterfazVendedores;
import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mateo
 */
public class InterfazPedidos extends javax.swing.JFrame {

    /**
     * Creates new form InterfazVendedores
     */
    public void mostrar(int id,String vendedor, String cliente){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Cliente");
        model.addColumn("Vendedor");
        model.addColumn("Estado");
        model.addColumn("Actualizar");
        model.addColumn("Eliminar");

        //Acciones de los botones de la tabla
        Action actionActualizarEstado = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());

                // Obtiene el ID del pedido desde la tabla en la columna correspondiente
                Object pedidoId = table.getModel().getValueAt(modelRow, 0); // Columna "ID"

                // Recupera los datos completos del pedido con el ID obtenido
                Pedido pedido = null;
                try{
                    pedido = PedidosController.getInstance().filtrarPedidoPorId((int) pedidoId);
                }catch (PedidoNoEncontradoException | SQLException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }


                // Crea y muestra una nueva interfaz para actualizar el estado pedido
                if (pedido != null) {
                    pedido.actualizarEstado();
                    mostrar(id,vendedor,cliente);
                }
            }
        };
        Action actionEliminar = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                // Obtiene el ID del pedido desde la tabla en la columna correspondiente
                Object pedidoId = table.getModel().getValueAt(modelRow, 0); // Columna "ID"

                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "¿Deseas eliminar el pedido?",
                        "Confirmación de eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if(confirm == JOptionPane.YES_OPTION){
                    try{
                        PedidosController.getInstance().eliminarPedido((int) pedidoId);
                        mostrar(id,vendedor,cliente);
                    }catch (PedidoNoEncontradoException | SQLException ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
            };
        };


        try{
            List<Pedido> pedidoList = PedidosController.getInstance().getPedido();
            if(id > 0) {pedidoList.retainAll((List<Pedido>)PedidosController.getInstance().filtrarPedidoPorId(id));}
            if(vendedor != null && !vendedor.equals("")) {pedidoList.retainAll(PedidosController.getInstance().filtrarPedidoPorVendedor(vendedor));}
            if(cliente != null && !cliente.equals("")) {pedidoList.retainAll(PedidosController.getInstance().filtrarPorNombreCliente(cliente));}
            Iterator<Pedido> ip = pedidoList.iterator();
            while(ip.hasNext()){
                Pedido pedido = ip.next();
                model.addRow(new Object[]{
                        pedido.getId(),
                        pedido.getCliente().getNombre(),
                        pedido.getVendedor().getNombre(),
                        pedido.getEstado().stringEstado()
                });
            }
        }catch (PedidoNoEncontradoException | SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }





        // Crear la tabla con el modelo
        tablaPedidos.setModel(model);
        ButtonColumn buttonColumnEditar = new ButtonColumn(tablaPedidos, actionActualizarEstado,4);
        ButtonColumn buttonColumnEliminar = new ButtonColumn(tablaPedidos,actionEliminar,5);





    }
    public InterfazPedidos() {
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
        vendedores = new javax.swing.JButton();
        itemMenus = new javax.swing.JButton();
        pedidos = new javax.swing.JButton();
        clientes = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        botonCrearPedido = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        filtrarPorId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        filtrarPorVendedor = new javax.swing.JTextField();
        filtrarPorCliente = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(222, 222, 222));

        jPanel2.setBackground(new java.awt.Color(222, 222, 222));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 128)));

        vendedores.setBackground(new java.awt.Color(65, 105, 225));
        vendedores.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        vendedores.setText("Vendedores");
        vendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendedoresActionPerformed(evt);
            }
        });

        itemMenus.setBackground(new java.awt.Color(65, 105, 225));
        itemMenus.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        itemMenus.setText("ItemMenus");
        itemMenus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenusActionPerformed(evt);
            }
        });

        pedidos.setBackground(new java.awt.Color(65, 105, 225));
        pedidos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        pedidos.setText("Pedidos");
        pedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedidosActionPerformed(evt);
            }
        });

        clientes.setBackground(new java.awt.Color(65, 105, 225));
        clientes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        clientes.setText("Clientes");
        clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vendedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(itemMenus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pedidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(vendedores, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(itemMenus, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(222, 222, 222));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 128)));

        mostrar(-1,null,null);
        jScrollPane1.setViewportView(tablaPedidos);

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(155, 155, 155));
        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Lista de Pedidos");

        botonCrearPedido.setBackground(new java.awt.Color(65, 105, 225));
        botonCrearPedido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        botonCrearPedido.setText("Crear Nuevo Pedido");
        botonCrearPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearPedidoActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(222, 222, 222));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Filtrar por id:");

        filtrarPorId.setBackground(new java.awt.Color(222, 222, 222));
        filtrarPorId.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        filtrarPorId.setForeground(new java.awt.Color(155, 155, 155));

        jLabel2.setBackground(new java.awt.Color(222, 222, 222));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Filtrar por Cliente:");

        jLabel3.setBackground(new java.awt.Color(222, 222, 222));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Filtrar por Vendedor:");

        filtrarPorVendedor.setBackground(new java.awt.Color(222, 222, 222));
        filtrarPorVendedor.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        filtrarPorVendedor.setForeground(new java.awt.Color(155, 155, 155));

        filtrarPorCliente.setBackground(new java.awt.Color(222, 222, 222));
        filtrarPorCliente.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        filtrarPorCliente.setForeground(new java.awt.Color(155, 155, 155));

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filtrarPorVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(filtrarPorId, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(filtrarPorCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(botonCrearPedido)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtrarPorId, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filtrarPorVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filtrarPorCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCrearPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void vendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendedoresActionPerformed
        // TODO add your handling code here:
        InterfazVendedores interfazVendedores = new InterfazVendedores();
        interfazVendedores.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_vendedoresActionPerformed

    private void botonCrearPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearPedidoActionPerformed
        // TODO add your handling code here:
        InterfazPedidoCrear interfazPedidoCrear = new InterfazPedidoCrear();
        interfazPedidoCrear.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonCrearPedidoActionPerformed

    private void clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesActionPerformed
        // TODO add your handling code here:
        InterfazClientes interfazClientes = new InterfazClientes();
        interfazClientes.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_clientesActionPerformed

    private void itemMenusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenusActionPerformed
        // TODO add your handling code here:
        InterfazItemsMenu interfazItemsMenu = new InterfazItemsMenu();
        interfazItemsMenu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_itemMenusActionPerformed

    private void pedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pedidosActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        // TODO add your handling code here:
        int id;
        if (!filtrarPorId.getText().equals("")) {id = Integer.parseInt(filtrarPorId.getText());}
        else{ id = -1;}

        String cliente = filtrarPorCliente.getText();

        String vendedor = filtrarPorVendedor.getText();
        mostrar(id, vendedor, cliente);
    }//GEN-LAST:event_botonBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonCrearPedido;
    private javax.swing.JButton clientes;
    private javax.swing.JTextField filtrarPorCliente;
    private javax.swing.JTextField filtrarPorId;
    private javax.swing.JTextField filtrarPorVendedor;
    private javax.swing.JButton itemMenus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton pedidos;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JButton vendedores;
    // End of variables declaration//GEN-END:variables
}
