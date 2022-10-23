
package usuarios;

import Metodos_sql.Metodos_sql;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Reserva extends javax.swing.JFrame {

    //Declaramos e inicializamos las variables para los JTable y Total de los productos
    DefaultTableModel m;
    static double total = 0;
    
     double precioactual=0.0;
      double importe=0.0;

    public Reserva() {
        initComponents();
        this.setLocationRelativeTo(this);
        mostrarCarnes();
        mostrarPastas();
        mostrarPizzas();
        mostrarBebidas();
        mostrarPostres();
        mostrarMenuDelDia();
        mostrarPromos();
    }
    
    //Metodo para mostrar los Productos 
     private void mostrarCarnes(){
	DefaultTableModel modelo = new DefaultTableModel();
        String carnes = "carnes";
        
	ResultSet rs = Metodos_sql.getTabla("select producto,precio,stock from menu where tipo = '" + carnes + "'");
        modelo.setColumnIdentifiers(new Object[]{"Producto", "Precio", "Stock"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("producto"), rs.getString("precio"), rs.getString("stock")});
        }
	tblProductos1.setModel(modelo);
        } catch (Exception e){
	System.out.println(e);
        }
    }
     
    private void mostrarPastas(){
	DefaultTableModel modelo = new DefaultTableModel();
        String pastas = "pastas";
        
	ResultSet rs = Metodos_sql.getTabla("select producto,precio,stock from menu where tipo = '" + pastas + "'");
        modelo.setColumnIdentifiers(new Object[]{"Producto", "Precio", "Stock"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("producto"), rs.getString("precio"), rs.getString("stock")});
        }
	tblProductos1.setModel(modelo);
        } catch (Exception e){
	System.out.println(e);
        }
    }

    private void mostrarPizzas(){
	DefaultTableModel modelo = new DefaultTableModel();
        String pizzas = "pizzas";
        
	ResultSet rs = Metodos_sql.getTabla("select producto,precio,stock from menu where tipo = '" + pizzas + "'");
        modelo.setColumnIdentifiers(new Object[]{"Producto", "Precio", "Stock"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("producto"), rs.getString("precio"), rs.getString("stock")});
        }
	tblProductos1.setModel(modelo);
        } catch (Exception e){
	System.out.println(e);
        }
    }
    
    private void mostrarBebidas(){
	DefaultTableModel modelo = new DefaultTableModel();
        String bebidas = "bebidas";
        
	ResultSet rs = Metodos_sql.getTabla("select producto,precio,stock from menu where tipo = '" + bebidas + "'");
        modelo.setColumnIdentifiers(new Object[]{"Producto", "Precio", "Stock"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("producto"), rs.getString("precio"), rs.getString("stock")});
        }
	tblProductos1.setModel(modelo);
        } catch (Exception e){
	System.out.println(e);
        }
    }
    
    private void mostrarPostres(){
	DefaultTableModel modelo = new DefaultTableModel();
        String postres = "postres";
        
	ResultSet rs = Metodos_sql.getTabla("select producto,precio,stock from menu where tipo = '" + postres + "'");
        modelo.setColumnIdentifiers(new Object[]{"Producto", "Precio", "Stock"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("producto"), rs.getString("precio"), rs.getString("stock")});
        }
	tblProductos1.setModel(modelo);
        } catch (Exception e){
	System.out.println(e);
        }
    }
    
    private void mostrarMenuDelDia(){
	DefaultTableModel modelo = new DefaultTableModel();
        String menu_del_dia = "menu del dia";
        
	ResultSet rs = Metodos_sql.getTabla("select producto,precio,stock from menu where tipo = '" + menu_del_dia + "'");
        modelo.setColumnIdentifiers(new Object[]{"Producto", "Precio", "Stock"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("producto"), rs.getString("precio"), rs.getString("stock")});
        }
	tblProductos1.setModel(modelo);
        } catch (Exception e){
	System.out.println(e);
        }
    }
    
    private void mostrarPromos(){
	DefaultTableModel modelo = new DefaultTableModel();
        
	ResultSet rs = Metodos_sql.getTabla("select m.producto, m.precio, m.stock from menu m inner join promociones p on m.menu_id = p.menu_id");
        modelo.setColumnIdentifiers(new Object[]{"Producto", "Precio", "Stock"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("producto"), rs.getString("precio"), rs.getString("stock")});
        }
	tblProductos1.setModel(modelo);
        } catch (Exception e){
	System.out.println(e);
        }
    }
    
    //Metodo para cancelar la compra de Productos
    public void limpiarPanel(){
        int limp;
       
	limp = JOptionPane.showConfirmDialog(null,"¿Está seguro de cancelar la compra?","Advertencia",JOptionPane.YES_NO_OPTION);
	if(limp == JOptionPane.YES_NO_OPTION){
		DefaultTableModel modelo = (DefaultTableModel) tblSeleccion.getModel();
		while(modelo.getRowCount()>0){
                    modelo.removeRow(0);
                
                 
		}
          precioactual = Double.parseDouble(jtxtTotal.getText()) - importe;
          jtxtTotal.setText(null);  
               
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tblSeleccion = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        jlbImagen = new javax.swing.JLabel();
        btnCarnes = new javax.swing.JButton();
        btnPastas = new javax.swing.JButton();
        btnPizzas = new javax.swing.JButton();
        btnBebidas = new javax.swing.JButton();
        btnPostres = new javax.swing.JButton();
        btnPromos = new javax.swing.JButton();
        btnMenuDelDia = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtxtCant = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductos1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jtxtTotal = new javax.swing.JTextField();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblSeleccion.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        tblSeleccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Precio", "Cantidad", "Importe"
            }
        ));
        tblSeleccion.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tblSeleccionComponentAdded(evt);
            }
        });
        tblSeleccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSeleccionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSeleccion);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        jlbImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnCarnes.setText("Carnes");
        btnCarnes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarnesActionPerformed(evt);
            }
        });

        btnPastas.setText("Pastas");
        btnPastas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPastasActionPerformed(evt);
            }
        });

        btnPizzas.setText("Pizzas");
        btnPizzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPizzasActionPerformed(evt);
            }
        });

        btnBebidas.setText("Bebidas");
        btnBebidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBebidasActionPerformed(evt);
            }
        });

        btnPostres.setText("Postres");
        btnPostres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostresActionPerformed(evt);
            }
        });

        btnPromos.setText("Promos");
        btnPromos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromosActionPerformed(evt);
            }
        });

        btnMenuDelDia.setText("Menu del Dia");
        btnMenuDelDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuDelDiaActionPerformed(evt);
            }
        });

        jLabel1.setText("Cant:");

        tblProductos1.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        tblProductos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Precio", "Stock"
            }
        ));
        tblProductos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductos1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProductos1);

        jLabel2.setText("Total:");

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel3.setText("RESERVA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addGap(37, 37, 37)
                                .addComponent(btnQuitar)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConfirmar)
                            .addComponent(btnCancelar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCarnes)
                                .addGap(18, 18, 18)
                                .addComponent(btnPastas)
                                .addGap(18, 18, 18)
                                .addComponent(btnPizzas)
                                .addGap(18, 18, 18)
                                .addComponent(btnBebidas)
                                .addGap(18, 18, 18)
                                .addComponent(btnPostres)))
                        .addGap(18, 18, 18)
                        .addComponent(btnMenuDelDia)
                        .addGap(18, 18, 18)
                        .addComponent(btnPromos)))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVolver)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCarnes)
                    .addComponent(btnPastas)
                    .addComponent(btnPizzas)
                    .addComponent(btnBebidas)
                    .addComponent(btnPostres)
                    .addComponent(btnMenuDelDia)
                    .addComponent(btnPromos))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar)
                            .addComponent(btnQuitar)
                            .addComponent(jLabel1)
                            .addComponent(jtxtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnConfirmar)
                        .addGap(41, 41, 41)
                        .addComponent(btnCancelar)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnVolver)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSeleccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSeleccionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSeleccionMouseClicked

    private void tblProductos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductos1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductos1MouseClicked

    private void tblSeleccionComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tblSeleccionComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSeleccionComponentAdded

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        Bienvenido bv = new Bienvenido();
        bv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        // TODO add your handling code here:
        Mesas ms = new Mesas();
        ms.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCarnesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarnesActionPerformed
        // TODO add your handling code here:
        mostrarCarnes();
    }//GEN-LAST:event_btnCarnesActionPerformed

    private void btnPastasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPastasActionPerformed
        // TODO add your handling code here:
        mostrarPastas();
    }//GEN-LAST:event_btnPastasActionPerformed

    private void btnPizzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPizzasActionPerformed
        // TODO add your handling code here:
        mostrarPizzas();
    }//GEN-LAST:event_btnPizzasActionPerformed

    private void btnBebidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBebidasActionPerformed
        // TODO add your handling code here:
        mostrarBebidas();
    }//GEN-LAST:event_btnBebidasActionPerformed

    private void btnPostresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostresActionPerformed
        // TODO add your handling code here:
        mostrarPostres();
    }//GEN-LAST:event_btnPostresActionPerformed

    private void btnMenuDelDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDelDiaActionPerformed
        // TODO add your handling code here:
        mostrarMenuDelDia();
    }//GEN-LAST:event_btnMenuDelDiaActionPerformed

    private void btnPromosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromosActionPerformed
        // TODO add your handling code here:
        mostrarPromos();
    }//GEN-LAST:event_btnPromosActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:

    int fsel = tblProductos1.getSelectedRow();
    try {
	String producto, precio, cant, stock, importe;
	double calculo = 0.0, subt = 0.0;
	int canti = 1;

	if(fsel == -1){
		JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
	}else{
		m = (DefaultTableModel) tblProductos1.getModel();
		producto = tblProductos1.getValueAt(fsel, 0).toString();
		precio = tblProductos1.getValueAt(fsel, 1).toString();
		stock = tblProductos1.getValueAt(fsel, 2).toString();
		cant = jtxtCant.getText();
		
		//Realizamos los calculos
		subt = (Double.parseDouble(precio) * Integer.parseInt(cant));
		importe = String.valueOf(subt);

		m = (DefaultTableModel) tblSeleccion.getModel();
		String index[] = {producto,precio,cant,importe};
		m.addRow(index);
                
                calculo = (Double.parseDouble(precio) * Integer.parseInt(jtxtCant.getText()));
                total = total + calculo;
                jtxtTotal.setText("" + total);
	}
    } catch (Exception e){}

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
        double subt = 0.0, importe = 0.0, precioactual = 0.0;
        int fsel, respuesta;
        fsel = tblSeleccion.getSelectedRow();
        try {
            if(fsel == -1){
		JOptionPane.showMessageDialog(null, "Debe seleccionar el producto a eliminar");
            } else{
		respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este producto?","Eliminar",JOptionPane.YES_NO_OPTION);
		if(respuesta == JOptionPane.YES_NO_OPTION){
			importe = Double.parseDouble(tblSeleccion.getValueAt(fsel,3).toString());
			precioactual = Double.parseDouble(jtxtTotal.getText()) - importe;
			total = precioactual;
			jtxtTotal.setText("" + total);
                        m = (DefaultTableModel)tblSeleccion.getModel();
                        m.removeRow(fsel);
                        
                        jtxtTotal.setText(null);
		}
               
	}
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Articulo eliminado");
        }
        
        

    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarPanel();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBebidas;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCarnes;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnMenuDelDia;
    private javax.swing.JButton btnPastas;
    private javax.swing.JButton btnPizzas;
    private javax.swing.JButton btnPostres;
    private javax.swing.JButton btnPromos;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jlbImagen;
    private javax.swing.JTextField jtxtCant;
    private javax.swing.JTextField jtxtTotal;
    private javax.swing.JTable tblProductos1;
    private javax.swing.JTable tblSeleccion;
    // End of variables declaration//GEN-END:variables
}
