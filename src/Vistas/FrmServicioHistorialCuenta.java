/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import controlador.servicio.TransaccionServicio;
import controlador.utilidades.Sesion;
import vista.tabla.ModeloTablaHistorial;

/**
 *
 * @author Usuario
 */
public class FrmServicioHistorialCuenta extends javax.swing.JPanel {

    ModeloTablaHistorial modelo = new ModeloTablaHistorial();
    TransaccionServicio ts = new TransaccionServicio();

    /**
     * Creates new form FrmServicioHistorialCuenta
     */
    public FrmServicioHistorialCuenta() {
        initComponents();
        cargarHistorial();
        cargarDatos();
    }

    private void cargarDatos() {
        jLabel8.setText(String.valueOf(Sesion.getCuenta().getPersona().getCuentaBancaria().getNroCuenta()));
        jLabel15.setText(String.valueOf(Sesion.getCuenta().getPersona().getCuentaBancaria().getSaldo()));
        jLabel14.setText(String.valueOf(Sesion.getCuenta().getPersona().nom()));
        // jLabel15.setText(Sesion.getCuenta().getPersona().);());
    }

    private void cargarHistorial() {
        modelo.setLista(ts.ListarTransacciones(Sesion.getCuenta().getPersona().getCuentaBancaria().getNroCuenta()));
        jTable1.setModel(modelo);
        jTable1.updateUI();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAsignar = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        labelMetric2 = new org.edisoncor.gui.label.LabelMetric();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(36, 47, 65));
        setLayout(null);

        pnlAsignar.setBackground(new java.awt.Color(36, 47, 65));

        jPanel7.setBackground(new java.awt.Color(65, 135, 124));
        jPanel7.setPreferredSize(new java.awt.Dimension(590, 40));
        jPanel7.setRequestFocusEnabled(false);

        labelMetric2.setBackground(new java.awt.Color(40, 153, 255));
        labelMetric2.setText("Historial Personal");
        labelMetric2.setColorDeSombra(new java.awt.Color(36, 47, 65));
        labelMetric2.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(labelMetric2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(labelMetric2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(97, 212, 195));
        jPanel3.setLayout(null);

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setForeground(new java.awt.Color(36, 47, 65));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Usuario", "Accion", "Descripcion", "Fecha"
            }
        ));
        jTable1.setSelectionBackground(new java.awt.Color(223, 247, 244));
        jTable1.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane2.setViewportView(jTable1);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(0, 100, 700, 210);

        jLabel11.setBackground(new java.awt.Color(0, 0, 255));
        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(36, 47, 65));
        jLabel11.setText("DOLAR");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(460, 40, 60, 16);

        jLabel13.setBackground(new java.awt.Color(0, 0, 255));
        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(36, 47, 65));
        jLabel13.setText("Moneda :");
        jPanel3.add(jLabel13);
        jLabel13.setBounds(400, 40, 60, 16);

        jLabel10.setBackground(new java.awt.Color(0, 0, 255));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(36, 47, 65));
        jLabel10.setText("Numero de cuenta :");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(330, 70, 120, 20);

        jLabel8.setBackground(new java.awt.Color(0, 0, 255));
        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(36, 47, 65));
        jPanel3.add(jLabel8);
        jLabel8.setBounds(460, 70, 120, 20);

        jLabel15.setBackground(new java.awt.Color(0, 0, 255));
        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(36, 47, 65));
        jPanel3.add(jLabel15);
        jLabel15.setBounds(150, 70, 170, 10);

        jLabel9.setBackground(new java.awt.Color(0, 0, 255));
        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(36, 47, 65));
        jLabel9.setText("Saldo Actual:");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(60, 70, 77, 16);

        jLabel7.setBackground(new java.awt.Color(0, 0, 255));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(36, 47, 65));
        jLabel7.setText("Usuario:");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(90, 40, 50, 16);

        jLabel14.setBackground(new java.awt.Color(0, 0, 255));
        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(36, 47, 65));
        jPanel3.add(jLabel14);
        jLabel14.setBounds(150, 40, 160, 20);

        jLabel5.setBackground(new java.awt.Color(0, 0, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(36, 47, 65));
        jLabel5.setText("CUENTA DE AHORROS");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(30, 10, 135, 16);

        javax.swing.GroupLayout pnlAsignarLayout = new javax.swing.GroupLayout(pnlAsignar);
        pnlAsignar.setLayout(pnlAsignarLayout);
        pnlAsignarLayout.setHorizontalGroup(
            pnlAsignarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAsignarLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlAsignarLayout.setVerticalGroup(
            pnlAsignarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAsignarLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        add(pnlAsignar);
        pnlAsignar.setBounds(0, 0, 700, 456);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private org.edisoncor.gui.label.LabelMetric labelMetric2;
    private javax.swing.JPanel pnlAsignar;
    // End of variables declaration//GEN-END:variables
}
