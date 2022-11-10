
package empleados;

import Metodos_sql.ConexionBD;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import usuarios.Reserva;


public class Mesas extends javax.swing.JFrame {
   
    

    public Mesas() {
        initComponents();
        this.setLocationRelativeTo(this);
        botones();
        buscarMesasReservadas();
        
       
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        pnlBotones = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("MESAS");

        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        pnlBotones.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
       
        this.dispose();
       
    }//GEN-LAST:event_btnVolverActionPerformed

    //conectar con la base de datos 
   ConexionBD cc= new ConexionBD();
   Connection con=cc.conectar();
   
   int filas = 3;
   int columnas = 3;
   int largoBoton = 95;
   int anchoBoton = 95;
   int ejex = 20;
   int ejey = 20;
   
   public JToggleButton [][] JTBotones = new JToggleButton[filas][columnas];
   
   //public static Connection conexion;
   public static PreparedStatement sentenciaPreparada;
   public static ResultSet resultado;
    
   
    //METODO PARA EL DISEÑO DE LOS BOTONES
   public void botones(){
       Font fuenteLetra = new Font("Arial",Font.BOLD,12);
       int contadorMesas = 1;
       JTBotones = new JToggleButton[filas][columnas];
       for (int i = 0; i < filas; i++) {
           for (int j = 0; j < columnas; j++) {
               JTBotones[i][j] = new JToggleButton();
               JTBotones[i][j].setBounds(ejex, ejey, largoBoton, anchoBoton);
               JTBotones[i][j].setText("Mesa " + contadorMesas);
               JTBotones[i][j].setFont(fuenteLetra);
               JTBotones[i][j].setBackground(new Color(38, 197, 43));
               
               AccionBotones accion = new AccionBotones();
               JTBotones[i][j].addActionListener(accion);
               
               pnlBotones.add(JTBotones[i][j]);
               
               contadorMesas++;
               ejex += 130;
           }
           ejex = 20; // posicion inicial
           ejey += 100; // separacion de los btn
       }
   }
   /*
   //METODO PARA RESERVAR MESA
    public void reservarMesa(int numero){
	try{
		con = ConexionBD.conectar();
		String consulta = "UPDATE mesas SET reservado = 'si' WHERE numero = " + numero;
		sentenciaPreparada = con.prepareStatement(consulta);
		int mensaje = sentenciaPreparada.executeUpdate();
		
		if(mensaje > 0){
			JOptionPane.showMessageDialog(null, "Mesa reservada");
		} else JOptionPane.showMessageDialog(null, "Error al actualizar el estado de la Mesa");
	}catch (Exception e){
		System.out.println("Error: " + e);
	}
}
*/
    //METODO PARA QUITAR MESA
    public void quitarMesa(int numero){
	try{
		con = ConexionBD.conectar();
		String consulta = "UPDATE mesas SET reservado = 'no' WHERE numero = "+ numero;
		sentenciaPreparada = con.prepareStatement(consulta);
		int mensaje = sentenciaPreparada.executeUpdate();
		
		if(mensaje > 0){
			JOptionPane.showMessageDialog(null, "Se ha liberado la Mesa");
		} else JOptionPane.showMessageDialog(null, "Error al actualizar el estado de la Mesa");
	}catch (Exception e){
		System.out.println("Error: " + e);
	}
}
    
    //METODO PARA BUSCAR MESAS RESERVADAS
    public void buscarMesasReservadas(){
        
        try {
            con = ConexionBD.conectar();
            String consulta = "SELECT numero, reservado FROM mesas";
            sentenciaPreparada = con.prepareStatement(consulta);
            resultado = sentenciaPreparada.executeQuery();
            int numero;
            String reservado;
            
            while(resultado.next()){
                numero = resultado.getInt("numero");
                reservado = resultado.getString("reservado");
                
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        
                        if (JTBotones[i][j].getText().length() == 6) {
                            String numeroLetra = JTBotones[i][j].getText().charAt(5)+"";
                            int numeroN = Integer.parseInt(numeroLetra);
                            if ((numero == numeroN)&&(reservado.equals("si"))) {
                                JTBotones[i][j].setBackground(Color.RED);
                                JTBotones[i][j].setSelected(true);
                            }
                        }
                    }
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
    public class AccionBotones implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (ae.getSource().equals(JTBotones[i][j])) {
                        if (JTBotones[i][j].isSelected()) {
                            JTBotones[i][j].setBackground(Color.RED);
                            if (JTBotones[i][j].getText().length() == 6) {
                                String numeroLetra = JTBotones[i][j].getText().charAt(5)+"";
                                int numero = Integer.parseInt(numeroLetra);
                                //reservarMesa(numero);
                            }
                        }else{
                            JTBotones[i][j].setBackground(new Color(38, 197, 43));
                            if (JTBotones[i][j].getText().length() == 6) {
                                String numeroLetra = JTBotones[i][j].getText().charAt(5)+"";
                                int numero = Integer.parseInt(numeroLetra);
                                quitarMesa(numero);
                            }
                        }
                    }
                }
            }
            
        }
        
    }
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
            java.util.logging.Logger.getLogger(Mesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mesas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mesas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlBotones;
    // End of variables declaration//GEN-END:variables
}
