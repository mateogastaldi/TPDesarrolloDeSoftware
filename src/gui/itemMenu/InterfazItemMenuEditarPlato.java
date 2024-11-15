// InterfazItemMenuEditarPlato.java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.itemMenu;

import controller.ItemMenusController;
import controller.VendedoresController;
import exceptions.itemMenu.ItemMenuNoEncontradoException;
import exceptions.itemMenu.categoria.CategoriaNoEncontradaException;
import model.*;

import javax.swing.*;

import java.sql.SQLException;
import java.util.Iterator;

/**
 *
 * @author mateo
 */
public class InterfazItemMenuEditarPlato extends javax.swing.JFrame {
    
    private ItemMenu iM = null;
    private Plato plato = null;

    public DefaultComboBoxModel<String> modeloDropDownListVendedor(){
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        try{
            Iterator<Vendedor> c = VendedoresController.getInstance().getVendedores().iterator();
            while (c.hasNext()) {
                modelo.addElement(c.next().getNombre());
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return modelo;

    }
    public DefaultComboBoxModel<String> modeloDropDownListCategoria(){
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        try{
            Iterator<Categoria> c = ItemMenusController.getInstance().filtrarPorTipoItem(Plato.class).iterator();
            while (c.hasNext()) {
                modelo.addElement(c.next().getDescripcion());
            }

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return modelo;
    }


    public InterfazItemMenuEditarPlato(ItemMenu itemMenu) {
        iM = itemMenu;
        plato = (Plato) iM;
        initComponents();
        cargarDatosPlato(); // Carga los datos del Plato en los campos
    }

    // Método para cargar los datos del Plato en los campos
    private void cargarDatosPlato() {
        // Autocompletar campos con los datos del Plato
        nombreItem.setText(plato.getNombre());
        descripcionItem.setText(plato.getDescripcion());
        precio.setText(String.valueOf(plato.getPrecio()));
        peso.setText(String.valueOf(plato.getPeso()));
        Calorias.setText(String.valueOf(plato.getCalorias()));

        // Establecer el vendedor y la categoría
        DropDownListVendedor.setSelectedItem(plato.getVendedor().getNombre());
        DropDownListCategoria.setSelectedItem(plato.getCategoria().getDescripcion());

        // Establecer si es apto para celiacos o veganos
        aptoCeliacoCheckBox.setSelected(plato.aptoCeliaco());
        aptoVeganoCheckBox.setSelected(plato.aptoVegano());
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jFrame1 = new javax.swing.JFrame();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        popupMenu1 = new java.awt.PopupMenu();
        jTextField2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        CuadroTitulo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        nombreItem = new javax.swing.JTextField();
        precio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        descripcionItem = new javax.swing.JTextField();
        botonCancelar = new javax.swing.JButton();
        botonConfirmar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        aptoVeganoCheckBox = new javax.swing.JCheckBox();
        aptoCeliacoCheckBox = new javax.swing.JCheckBox();
        DropDownListCategoria = new javax.swing.JComboBox<>(modeloDropDownListCategoria());
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        DropDownListVendedor = new javax.swing.JComboBox<>(modeloDropDownListVendedor());
        peso = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Calorias = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        popupMenu1.setLabel("popupMenu1");

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(222, 222, 222));

        jPanel3.setBackground(new java.awt.Color(222, 222, 222));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 128)));

        CuadroTitulo.setEditable(false);
        CuadroTitulo.setBackground(new java.awt.Color(155, 155, 155));
        CuadroTitulo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        CuadroTitulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CuadroTitulo.setText("Editar Plato");
        CuadroTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CuadroTituloActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nombre:");
        jLabel1.setToolTipText("");

        nombreItem.setBackground(new java.awt.Color(222, 222, 222));
        nombreItem.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        nombreItem.setForeground(new java.awt.Color(0, 0, 0));
        nombreItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreItemActionPerformed(evt);
            }
        });
        nombreItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreItemKeyTyped(evt);
            }
        });

        precio.setBackground(new java.awt.Color(222, 222, 222));
        precio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        precio.setForeground(new java.awt.Color(0, 0, 0));
        precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioActionPerformed(evt);
            }
        });
        precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precioKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Descripcion:");
        jLabel7.setToolTipText("");

        descripcionItem.setBackground(new java.awt.Color(222, 222, 222));
        descripcionItem.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        descripcionItem.setForeground(new java.awt.Color(0, 0, 0));
        descripcionItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descripcionItemActionPerformed(evt);
            }
        });
        descripcionItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                descripcionItemKeyTyped(evt);
            }
        });

        botonCancelar.setBackground(new java.awt.Color(161, 19, 32));
        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonConfirmar.setBackground(new java.awt.Color(65, 105, 225));
        botonConfirmar.setText("Confirmar");
        botonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfirmarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Precio");
        jLabel11.setToolTipText("");

        aptoVeganoCheckBox.setBackground(new java.awt.Color(222, 222, 222));
        aptoVeganoCheckBox.setForeground(new java.awt.Color(0, 0, 0));
        aptoVeganoCheckBox.setText("Apto Vegano");
        aptoVeganoCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aptoVeganoCheckBoxActionPerformed(evt);
            }
        });

        aptoCeliacoCheckBox.setBackground(new java.awt.Color(222, 222, 222));
        aptoCeliacoCheckBox.setForeground(new java.awt.Color(0, 0, 0));
        aptoCeliacoCheckBox.setText("Apto Celiaco");
        aptoCeliacoCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aptoCeliacoCheckBoxActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Categoria:");
        jLabel8.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Vendedor:");
        jLabel9.setToolTipText("");

        peso.setBackground(new java.awt.Color(222, 222, 222));
        peso.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        peso.setForeground(new java.awt.Color(0, 0, 0));
        peso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesoActionPerformed(evt);
            }
        });
        peso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pesoKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Peso:");
        jLabel10.setToolTipText("");

        Calorias.setBackground(new java.awt.Color(222, 222, 222));
        Calorias.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Calorias.setForeground(new java.awt.Color(0, 0, 0));
        Calorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaloriasActionPerformed(evt);
            }
        });
        Calorias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CaloriasKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Calorias:");
        jLabel12.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CuadroTitulo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(peso, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DropDownListVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(121, 121, 121)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(botonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Calorias, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(descripcionItem, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(aptoVeganoCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(DropDownListCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(aptoCeliacoCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(147, 147, 147)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CuadroTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionItem, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DropDownListCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DropDownListVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(aptoCeliacoCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aptoVeganoCheckBox)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(peso, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Calorias, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    
    private void CuadroTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CuadroTituloActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_CuadroTituloActionPerformed

    private void nombreItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreItemActionPerformed

    private void precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioActionPerformed

    private void descripcionItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descripcionItemActionPerformed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_descripcionItemActionPerformed

    private void botonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarActionPerformed
    // TODO add your handling code here:
    // Obtén y valida los datos ingresados
    String nombre = nombreItem.getText();
    double pesoIngresado = Double.parseDouble(peso.getText());
    String descripcion = descripcionItem.getText();

    double caloriasIngresadas = Double.parseDouble(Calorias.getText());
    double precioIngresado = Double.parseDouble(precio.getText());

    // Obtén el vendedor y la categoría seleccionados
    Vendedor vendedor = null;     
    Categoria categoria = null;
	try {
        vendedor = VendedoresController.getInstance().filtrarVendedorPorNombre((String)DropDownListVendedor.getSelectedItem()).stream().findFirst().orElse(null);
		categoria = (Categoria) ItemMenusController.getInstance().filtrarCategoriaPorNombre((String)DropDownListCategoria.getSelectedItem()).stream().findFirst().orElse(null);
	} catch (CategoriaNoEncontradaException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    // Verifica si el plato es apto para veganos o celíacos
    boolean aptoCeliaco = aptoCeliacoCheckBox.isSelected();
    boolean aptoVegano = aptoVeganoCheckBox.isSelected();

    try {
                // Obtén el plato existente y actualízalo
        plato.editarItem(nombre, descripcion, precioIngresado, aptoVegano, aptoCeliaco, categoria, vendedor, caloriasIngresadas, pesoIngresado);

        // Redirige a la interfaz de items del menú
        InterfazItemsMenu interfazItemsMenu = new InterfazItemsMenu();
        interfazItemsMenu.setVisible(true);
        this.setVisible(false);
    } catch (ItemMenuNoEncontradoException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_botonConfirmarActionPerformed

    private void descripcionItemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descripcionItemKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_descripcionItemKeyTyped

    private void nombreItemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreItemKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isLetter(c)) && c!=' ') evt.consume();
    }//GEN-LAST:event_nombreItemKeyTyped

    private void precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if( (c<'0' || c>'9') && c!='.') evt.consume();
    }//GEN-LAST:event_precioKeyTyped

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        // TODO add your handling code here:
        InterfazItemsMenu interfazItemsMenu = new InterfazItemsMenu();
        interfazItemsMenu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void aptoCeliacoCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aptoCeliacoCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aptoCeliacoCheckBoxActionPerformed

    private void aptoVeganoCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aptoVeganoCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aptoVeganoCheckBoxActionPerformed

    private void CaloriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaloriasActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_CaloriasActionPerformed

    private void CaloriasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CaloriasKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if( (c<'0' || c>'9') && c!='.') evt.consume();
    }//GEN-LAST:event_CaloriasKeyTyped

    private void pesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if( (c<'0' || c>'9') && c!='.') evt.consume();
    }//GEN-LAST:event_pesoKeyTyped

    private void pesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesoActionPerformed

   



    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Calorias;
    private javax.swing.JTextField CuadroTitulo;
    private javax.swing.JComboBox<String> DropDownListCategoria;
    private javax.swing.JComboBox<String> DropDownListVendedor;
    private javax.swing.JCheckBox aptoCeliacoCheckBox;
    private javax.swing.JCheckBox aptoVeganoCheckBox;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonConfirmar;
    private javax.swing.JTextField descripcionItem;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField2;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JTextField nombreItem;
    private javax.swing.JTextField peso;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTextField precio;
    // End of variables declaration//GEN-END:variables
}
