
package usuarios;

import Metodos_sql.Metodos_sql;
import iniciosesion.Login;
import acercade.nosotros;


public class Bienvenido extends javax.swing.JFrame {


    public Bienvenido() {
        initComponents();
        this.setLocationRelativeTo(this);
    }
    
Metodos_sql metodos = new Metodos_sql();
   String user;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnReserva = new javax.swing.JButton();
        btnCartilla = new javax.swing.JButton();
        btnMenuDelDia = new javax.swing.JButton();
        btnPromos = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnPromos1 = new javax.swing.JButton();
        lblNombre = new javax.swing.JLabel();
        btnacercade = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Bienvenido!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        btnReserva.setText("Realizar Reserva");
        btnReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservaActionPerformed(evt);
            }
        });

        btnCartilla.setText("Cartilla");
        btnCartilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartillaActionPerformed(evt);
            }
        });

        btnMenuDelDia.setText("Menu del Dia");
        btnMenuDelDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuDelDiaActionPerformed(evt);
            }
        });

        btnPromos.setText("Más Consumidos");
        btnPromos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromosActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");

        btnPromos1.setText("Promos");
        btnPromos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromos1ActionPerformed(evt);
            }
        });

        btnacercade.setText("Acerca De");
        btnacercade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnacercadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCartilla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnMenuDelDia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPromos, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                                    .addComponent(btnPromos1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(35, 35, 35)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(btnSalir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(btnacercade)
                        .addContainerGap(11, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCartilla, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMenuDelDia, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPromos1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPromos, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnSalir)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnacercade)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaActionPerformed
        // TODO add your handling code here:
            user = Login.user;
            
            //captura el nombre
            String busqueda_nombre = metodos.buscarNombre(user);
            
            //caputura id
             int  busqueda_id = metodos.buscarId(user);
             String buscarid = String.valueOf(busqueda_id);
            
            Reserva re = new Reserva();
            re.jLabelnombre.setText(busqueda_nombre);
            re.jLabelid.setText(buscarid);
            
            re.setVisible(true);
            this.dispose();
        
       
    }//GEN-LAST:event_btnReservaActionPerformed

    private void btnCartillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartillaActionPerformed
        // TODO add your handling code here:
        Cartilla ca = new Cartilla();
        ca.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCartillaActionPerformed

    private void btnMenuDelDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDelDiaActionPerformed
        // TODO add your handling code here:
        MenuDelDia me = new MenuDelDia();
        me.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuDelDiaActionPerformed

    private void btnPromosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromosActionPerformed
        // TODO add your handling code here:
        Promos pr = new Promos();
        pr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPromosActionPerformed

    private void btnPromos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromos1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPromos1ActionPerformed

    private void btnacercadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnacercadeActionPerformed
        // TODO add your handling code here:ç
        
        nosotros ac = new nosotros();
        ac.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_btnacercadeActionPerformed

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
            java.util.logging.Logger.getLogger(Bienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bienvenido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bienvenido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCartilla;
    private javax.swing.JButton btnMenuDelDia;
    private javax.swing.JButton btnPromos;
    private javax.swing.JButton btnPromos1;
    public javax.swing.JButton btnReserva;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnacercade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lblNombre;
    // End of variables declaration//GEN-END:variables
}
