
package empleados;

import Metodos_sql.ConexionBD;
import Metodos_sql.Metodos_sql;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Milagros
 */
public class ListaReservas extends javax.swing.JFrame {

    DefaultTableModel m;
    /**
     * Creates new form ListaReservas
     */
    public ListaReservas() {
        initComponents();
        this.setLocationRelativeTo(this);
        mostrarReservas();
        getContentPane().setBackground(new Color(174, 138, 138));
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
    }

    
    //METODO PARA MOSTRAR LOS PRODUCTOS
     private void mostrarReservas(){ 
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = Metodos_sql.getTabla("SELECT usuarios_id, fecha, horario, menu_id, cantidad, comensales FROM venta WHERE estado = 'reservado';");
        modelo.setColumnIdentifiers(new Object[]{"Cliente ", "Fecha", "Horario", "Menu", "Cantidad", "Comensales"});
	try{    
            while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("usuarios_id"), rs.getString("fecha"), rs.getString("horario"), rs.getString("menu_id"), rs.getString("cantidad"), rs.getString("comensales")});
            }
	jtReservas.setModel(modelo);
        } catch (Exception e){
	System.out.println(e);
        }
    }
    
     
     
    //METODO PARA BUSCAR RESERVAS POR CLIENTE
      private void  filtrarDatos(int cliente){
	DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = Metodos_sql.getTabla("select * from venta where usuarios_id like '%"+cliente+"%' ");
        modelo.setColumnIdentifiers(new Object[]{"Cliente ", "Fecha", "Horario", "Menu", "Cantidad", "Comensales"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("cliente"), rs.getString("fecha"), rs.getString("horario"), rs.getString("menu"), rs.getString("cantidad"), rs.getString("comensales") });
        }
	jtReservas.setModel(modelo);
        } catch (Exception e){
            System.out.println(e);
        }
    }
      
      
    //METODO PARA BUSCAR RESERVAS POR FECHA
      private void  filtrarFechas(){
	DefaultTableModel modelo = new DefaultTableModel();
        String fecha = ((JTextField) BuscarFecha.getDateEditor().getUiComponent()).getText();
        
        ResultSet rs = Metodos_sql.getTabla("select * from venta where fecha = '"+fecha+"' ");
        modelo.setColumnIdentifiers(new Object[]{"Cliente ", "Fecha", "Horario", "Menu", "Cantidad", "Comensales"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("usuarios_id"), rs.getString("fecha"), rs.getString("horario"), rs.getString("menu_id"), rs.getString("cantidad"), rs.getString("comensales") });
        }
	jtReservas.setModel(modelo);
        } catch (Exception e){
            System.out.println(e);
        }
    }  
      
      
    //METODO PARA QUITAR MESA
    public void despacharMesa(){
        
        int fsel = jtReservas.getSelectedRow();
        //String estado = tblMesas.getValueAt(fsel2, 2).toString();
        
        String  cliente = jtReservas.getValueAt(fsel, 0).toString();
        int clienteint =   Integer.parseInt(cliente);
        
         
        String  fecha = jtReservas.getValueAt(fsel, 1).toString();
        //int clienteint =   Integer.parseInt(cliente);
        
        
	Connection conexion = null;
        
        
        String sentencia_guardar = "UPDATE venta SET estado = 'despachado'  where venta.usuarios_id = '" + clienteint + "' and venta.fecha =  '" + fecha + "' "  ;
       
	try {
                    
             conexion = ConexionBD.conectar();
             Statement st = conexion.createStatement();
            
             st.executeUpdate(sentencia_guardar);  
             
             JOptionPane.showMessageDialog(null, "Reserva liberada!!");
             } catch (Exception e) {
             System.out.println(e.getMessage());  
             }
}  
    
    //METODO PARA BUSCAR DATOS
    private void filtrarDatos(String cliente) {

        
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = Metodos_sql.getTabla("select * from venta where usuarios_id = '"+ cliente +"' ");
        modelo.setColumnIdentifiers(new Object[]{"Cliente ", "Fecha", "Horario", "Menu", "Cantidad", "Comensales"});
        try {
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("usuarios_id"), rs.getString("fecha"), rs.getString("horario"), rs.getString("menu_id"), rs.getString("cantidad"), rs.getString("comensales")});
            }
            jtReservas.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtReservas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jtfBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        btnDespachar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        BuscarFecha = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Fecha", "Horario", "Menu", "Cantidad", "Comensales"
            }
        ));
        jScrollPane1.setViewportView(jtReservas);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Buscar Cliente:");

        jtfBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscarKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 102));
        jLabel2.setText("Inventario de Reservas");

        btnVolver.setBackground(new java.awt.Color(0, 204, 204));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnDespachar.setBackground(new java.awt.Color(204, 255, 204));
        btnDespachar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnDespachar.setForeground(new java.awt.Color(0, 153, 153));
        btnDespachar.setText("Despachar");
        btnDespachar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDespacharActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 153));
        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Buscar Fecha:");

        BuscarFecha.setDateFormatString("yyyy-MM-dd");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(252, 252, 252)
                                        .addComponent(btnDespachar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(313, 313, 313)
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BuscarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(btnBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jtfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDespachar)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BuscarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDespacharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDespacharActionPerformed
        // TODO add your handling code here:
        despacharMesa();
        int fsel, respuesta;
        
        fsel = jtReservas.getSelectedRow();
        
        try {
            if (fsel == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una reserva a despachar");
            } else {
                respuesta = JOptionPane.showConfirmDialog(null, "??Esta seguro de despachar ??sta reserva?", "Despachar", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_NO_OPTION) {
                    //mostrarReservas();
                    
                    m = (DefaultTableModel) jtReservas.getModel();
                    m.removeRow(fsel); 
                    
                }
            }    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Reserva Despachada");
            
        }
        
        
    }//GEN-LAST:event_btnDespacharActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        mostrarReservas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscarKeyReleased
        // TODO add your handling code here:
        filtrarDatos(jtfBuscar.getText());
    }//GEN-LAST:event_jtfBuscarKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        filtrarFechas();
    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(ListaReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaReservas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser BuscarFecha;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDespachar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtReservas;
    private javax.swing.JTextField jtfBuscar;
    // End of variables declaration//GEN-END:variables

@Override
    public Image getIconImage(){
    
    Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("/imagenes/icono.png"));
        return retValue;
    }

}
