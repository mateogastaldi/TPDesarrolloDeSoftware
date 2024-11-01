/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import exceptions.ClienteNoEncontradoException;
import memory.ClienteMemory;
import tp.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mateo
 */
public class InterfazClientes extends javax.swing.JFrame {

    /**
     * Creates new form InterfazVendedores
     */
    public InterfazClientes() {
        initComponents();
    }

    public void mostrar(String nombre){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("ID");
        model.addColumn("Pais");
        model.addColumn("Ciudad");
        model.addColumn("Calle");
        model.addColumn("Altura");
        model.addColumn("Acciones");

        if(nombre != null){
            try{
                List<Cliente> clientes = ClienteMemory.getInstance().filtrarClientePorNombre(nombre);
                Iterator<Cliente> iterator = clientes.iterator();
                while(iterator.hasNext()){
                    Cliente cliente = iterator.next();
                    model.addRow(new Object[]{
                            cliente.getNombre(),
                            cliente.getId(),
                            cliente.getDireccion().getPais(),
                            cliente.getDireccion().getCiudad(),
                            cliente.getDireccion().getCalle(),
                            cliente.getDireccion().getAltura()
                    });

                }

            }catch(ClienteNoEncontradoException e){
                JOptionPane.showMessageDialog(null, e.getMessage());

            }
        }
        else{
            try{
                List<Cliente> clientes = ClienteMemory.getInstance().getClientes();
                Iterator<Cliente> iterator = clientes.iterator();
                while(iterator.hasNext()){
                    Cliente cliente = iterator.next();
                    model.addRow(new Object[]{
                            cliente.getNombre(),
                            cliente.getId(),
                            cliente.getDireccion().getPais(),
                            cliente.getDireccion().getCiudad(),
                            cliente.getDireccion().getCalle(),
                            cliente.getDireccion().getAltura()
                    });
    
                }

            }catch(ClienteNoEncontradoException e){
                JOptionPane.showMessageDialog(null, e.getMessage());

            }
        }
        tablaClientes.setModel(model);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        BotonVendedores = new javax.swing.JButton();
        BotonItemMenus = new javax.swing.JButton();
        BotonPedidos = new javax.swing.JButton();
        BotonClientes = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        crearCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BuscadorPorNombre = new javax.swing.JTextField();
        BotonBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();

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

        BotonItemMenus.setBackground(new java.awt.Color(65, 105, 225));
        BotonItemMenus.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BotonItemMenus.setText("ItemMenus");
        BotonItemMenus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonItemMenusActionPerformed(evt);
            }
        });

        BotonPedidos.setBackground(new java.awt.Color(65, 105, 225));
        BotonPedidos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BotonPedidos.setText("Pedidos");
        BotonPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonPedidosActionPerformed(evt);
            }
        });

        BotonClientes.setBackground(new java.awt.Color(65, 105, 225));
        BotonClientes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        BotonClientes.setText("Clientes");
        BotonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonClientesActionPerformed(evt);
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
                    .addComponent(BotonItemMenus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BotonPedidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BotonClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(BotonVendedores, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(BotonItemMenus, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(222, 222, 222));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 128)));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(155, 155, 155));
        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("Lista de Clientes");

        crearCliente.setBackground(new java.awt.Color(65, 105, 225));
        crearCliente.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        crearCliente.setText("Crear Nuevo Clientes");
        crearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearClienteActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(222, 222, 222));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Buscador:");

        BuscadorPorNombre.setBackground(new java.awt.Color(222, 222, 222));
        BuscadorPorNombre.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        BuscadorPorNombre.setForeground(new java.awt.Color(155, 155, 155));
        BuscadorPorNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscadorPorNombreActionPerformed(evt);
            }
        });

        BotonBuscar.setBackground(new java.awt.Color(65, 105, 225));
        BotonBuscar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        BotonBuscar.setText("Buscar");
        BotonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBuscarActionPerformed(evt);
            }
        });

        mostrar(null);
        jScrollPane2.setViewportView(tablaClientes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(crearCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BuscadorPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotonBuscar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(crearCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuscadorPorNombre)
                    .addComponent(BotonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void crearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearClienteActionPerformed
        // TODO add your handling code here:
        InterfazClienteCrear crearCliente = new InterfazClienteCrear();
        crearCliente.setVisible(true);
        this.setVisible(false); 
    }//GEN-LAST:event_crearClienteActionPerformed

    private void BotonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBuscarActionPerformed
        // TODO add your handling code here:
        if(BuscadorPorNombre.getText().equals("")){
            mostrar(null);
        }else{
            mostrar(BuscadorPorNombre.getText());
        }
    }//GEN-LAST:event_BotonBuscarActionPerformed

    private void BuscadorPorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscadorPorNombreActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_BuscadorPorNombreActionPerformed

    private void BotonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonClientesActionPerformed

    private void BotonItemMenusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonItemMenusActionPerformed
        // TODO add your handling code here:
        InterfazItemsMenu itemMenu = new InterfazItemsMenu();
        itemMenu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonItemMenusActionPerformed

    private void BotonPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonPedidosActionPerformed
        // TODO add your handling code here:
        InterfazPedidos interfazPedidos = new InterfazPedidos();
        interfazPedidos.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonPedidosActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonBuscar;
    private javax.swing.JButton BotonClientes;
    private javax.swing.JButton BotonItemMenus;
    private javax.swing.JButton BotonPedidos;
    private javax.swing.JButton BotonVendedores;
    private javax.swing.JTextField BuscadorPorNombre;
    private javax.swing.JButton crearCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
