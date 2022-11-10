package usuarios;

import Metodos_sql.ConexionBD;
import Metodos_sql.Metodos_sql;
import static Metodos_sql.Metodos_sql.resultado;
import static Metodos_sql.Metodos_sql.sentencia_preparada;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Dimension;



//IMPORTS DE ITEXT PDF
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import java.awt.ScrollPane;




import java.io.FileOutputStream;





public class Reserva extends javax.swing.JFrame {



    //Declaramos e inicializamos las variables para los JTable y Total de los productos
    DefaultTableModel m;
    static double total = 0;
    Metodos_sql msql = new Metodos_sql();
    Connection con;

    public Reserva() {
        initComponents();
        this.setLocationRelativeTo(this);
        mostrarDatos();
        mostrarTipos(cmbTipo);
        this.tblProductos1.setDefaultRenderer(Object.class,  new FilaColor());
        this.tblMesas.setDefaultRenderer(Object.class,  new MesasColor());
        mostrarMesas();
        
        //caputura id
             //int  busqueda_id = buscarMesa();
             //String buscarid = String.valueOf(busqueda_id);
            //lblMesaElegida.setText(buscarid);
     }

   
    
    //METODO PARA MOSTRAR LOS PRODUCTOS
     private void mostrarDatos(){
           
         
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = Metodos_sql.getTabla("select * from menu");
        modelo.setColumnIdentifiers(new Object[]{"Producto ", "Precio", "Stock", "id"});
	try{
            
            
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("producto"), rs.getString("precio"), rs.getString("stock"), rs.getString("menu_id") });
        }
        
        
	tblProductos1.setModel(modelo);
        } catch (Exception e){
	System.out.println(e);
        }
    }
     
     
     //imagenes
      public void MostrarImagen() {
          
           int fsel = tblProductos1.getSelectedRow();
           String  menu_id = tblProductos1.getValueAt(fsel, 3).toString();
           int menu_idint =   Integer.parseInt(menu_id);
          
          
           Connection conexion = null;
           ResultSet rs =Metodos_sql.getTabla("Select foto from menu where menu_id="+menu_idint );
         
          
         try{
                    
               
              while(rs.next()){
                  
                      Image i=null;
                      Blob blob = rs.getBlob("foto");
                      i= javax.imageio.ImageIO.read(blob.getBinaryStream());
                      ImageIcon image = new ImageIcon(i.getScaledInstance(250, 218, 0));
                     
                     if(blob != null ){
                        lbImagen.setIcon(image);
                     } else {
                         System.out.println(image);
                            }
                  
                  }

                }catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
          
      }
     
     
   
    //LLENAR COMBOBOX
          private void mostrarTipos(JComboBox cmbTipo){
	
        ResultSet rs = Metodos_sql.getTabla("select DISTINCT tipo from menu");
        
	try{
	while (rs.next()){
		cmbTipo.addItem(rs.getString("tipo"));
        }
	
        } catch (Exception e){
	System.out.println(e);
        }
    }
          
     
     // BUscar por tipo 
        private void  buscarTipos(JComboBox cmbTipo){
         
	DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = Metodos_sql.getTabla("select * from menu where tipo like '%"+ cmbTipo.getSelectedItem() +"%' ");
        modelo.setColumnIdentifiers(new Object[]{"Producto ", "Precio", "Stock", "id"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("producto"), rs.getString("precio"), rs.getString("stock"), rs.getString("menu_id") });
        }
	tblProductos1.setModel(modelo);
        } catch (Exception e){ 
            System.out.println(e);
        }
    }
     
     
     
     
  //METODO PARA BUSCAR DATOS
      private void  filtrarDatos(String producto){
	DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = Metodos_sql.getTabla("select * from menu where producto like '%"+producto+"%' ");
        modelo.setColumnIdentifiers(new Object[]{"Producto ", "Precio", "Stock", "id"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("producto"), rs.getString("precio"), rs.getString("stock"), rs.getString("menu_id") });
        }
	tblProductos1.setModel(modelo);
        } catch (Exception e){
            System.out.println(e);
        }
    }
      
      
      //METODO PARA RESTAR STOCK
           public void RestarStock(){
               
             int cant = Integer.parseInt(jtxtCant.getText());
             int fsel = tblProductos1.getSelectedRow();
             String stock = tblProductos1.getValueAt(fsel, 2).toString();
             int stockint = Integer.parseInt(stock);
             
              String  menu_id = tblProductos1.getValueAt(fsel, 3).toString();
              int menu_idint =   Integer.parseInt(menu_id);
             
               if (stockint > 0 &&  cant <= stockint) {
                    Connection conexion = null;
                     String sentencia_guardar ="UPDATE menu SET stock = stock - '" + cant +   "' where menu_id = " +  menu_idint;
                            try {

                              conexion = ConexionBD.conectar();
                              Statement st = conexion.createStatement();
                              int n = st.executeUpdate(sentencia_guardar);
                                  
                              
                                
                            
                          } catch (Exception e) {
                               System.out.println(e.getMessage());
                              
                         }
               
               
               
               
               
               } 
              
        
        
        
    }
           
     // METODO PARA devolver STOCK
           
     public void devolverStock(){
               
          
             int fsel = tblSeleccion.getSelectedRow();
             String cant = tblSeleccion.getValueAt(fsel, 2).toString();
             int cantint = Integer.parseInt(cant);
             
              String  menu_id = tblSeleccion.getValueAt(fsel, 4).toString();
              int menu_idint =   Integer.parseInt(menu_id);
             
               if (cantint > 0) {
                    Connection conexion = null;
                     String sentencia_guardar ="UPDATE menu SET stock = stock + '" + cantint +   "' where menu_id = " +  menu_idint;
                            try {

                              conexion = ConexionBD.conectar();
                              Statement st = conexion.createStatement();
                              int n = st.executeUpdate(sentencia_guardar);
                                  
                              
                                
                            
                          } catch (Exception e) {
                               System.out.println(e.getMessage());
                              
                         }
               
               
               
               
               
               } 
              
        
        
        
    }
           

           
   //METODO PARA CONTROL STOCK         
    public class FilaColor extends DefaultTableCellRenderer {
            
        public Component getTableCellRendererComponent (JTable table, Object velue, boolean isSelected, boolean hasFocus, int row, int column){
            
            String stock = tblProductos1.getValueAt(row,2).toString();
            int stockint = Integer.parseInt(stock);
            
            if(stockint == 0) {
                this.setBackground(Color.red);
            }  else if(stockint <= 5) {
                this.setBackground(Color.orange);
            }else {
                this.setBackground(Color.white);
                this.setForeground(Color.black);
            }
  
          return super.getTableCellRendererComponent(table, velue, isSelected, hasFocus, row, column);
        }
                
    }
    
    
    public void confirmarVenta(){
	int fsel = tblSeleccion.getSelectedRow();
        int fsel2 = tblMesas.getSelectedRow();
	String fecha = ((JTextField)Calendario.getDateEditor().getUiComponent()).getText();
	String subtotal = tblSeleccion.getValueAt(fsel, 3).toString();
        String mesaid = tblMesas.getValueAt(fsel2, 0).toString();
        String usuarioid = jLabelid.getText();
        String horario = Horario.getSelectedItem().toString();
        String cantidad = tblSeleccion.getValueAt(fsel, 2).toString();
        String menuid = tblSeleccion.getValueAt(fsel, 4).toString();
        String comensales = jtxtComensales.getText();
       String estado = "reservado";
       
        
	Connection conexion = null;
        //String sql = "SELECT * FROM venta ORDER BY idventa DESC LIMIT 1";
        
        String sentencia_guardar = "insert into venta (idventa,fecha,subtotal, mesas_id,usuarios_id,horario,cantidad,menu_id,comensales,estado) values(null,'"+ fecha +"','"+ subtotal +"','"+ mesaid +"','"+ usuarioid+"','"+ horario+"','"+ cantidad+"','"+ menuid+"','"+ comensales+"','"+ estado+"')";
       
	try {
                
             conexion = ConexionBD.conectar();
             Statement st = conexion.createStatement();
            
             st.executeUpdate(sentencia_guardar);  
             
             JOptionPane.showMessageDialog(null, "Plato agregado!!, ingrese los demás o por favor emita el ticket.");
             } catch (Exception e) {
             System.out.println(e.getMessage());  
             }
}
    /*
    public int buscarMesa(){
        int busqueda_id= 0;
        Connection conexion = null;
        try {
            conexion  = ConexionBD.conectar();
            
            String sentencia_buscar=("SELECT * from mesas where reservado = 'si' order by id desc limit 1" );
            
            sentencia_preparada = conexion.prepareStatement(sentencia_buscar);
            resultado= sentencia_preparada.executeQuery();
            
            if(resultado.next()){
               busqueda_id = resultado.getInt(1);
              
            }    
            conexion.close();   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return busqueda_id;
    
    }
      */     
    
    //METODO PARA MOSTRAR MESAS
      private void  mostrarMesas(){
	DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = Metodos_sql.getTabla("select * from mesas");
        modelo.setColumnIdentifiers(new Object[]{"Id ", "Numero", "Reservado"});
	try{
	while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("id"), rs.getString("numero"), rs.getString("reservado")});
        }
	tblMesas.setModel(modelo);
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    
    //METODO PARA CONTROLAR DISPONIBILIDAD DE MESAS        
    public class MesasColor extends DefaultTableCellRenderer {
            
        public Component getTableCellRendererComponent (JTable table, Object velue, boolean isSelected, boolean hasFocus, int row, int column){
            
            String estado = tblMesas.getValueAt(row,2).toString();
            //int stockint = Integer.parseInt(estado);
            
            if(estado.equals("si")) {
                this.setBackground(new Color(240,128,128));
            }  else if(estado.equals("no")) {
                this.setBackground(new Color(144,238,144));
            }
          return super.getTableCellRendererComponent(table, velue, isSelected, hasFocus, row, column);
        }
                
    }  

    
    //METODO PARA RESERVAR MESA
    public void reservarMesa(){
        
        int fsel = tblMesas.getSelectedRow();
        //String estado = tblMesas.getValueAt(fsel2, 2).toString();
        
        String  menu_id = tblMesas.getValueAt(fsel, 0).toString();
        int menu_idint =   Integer.parseInt(menu_id);
             
        
	Connection conexion = null;
        
        
        String sentencia_guardar = "UPDATE mesas SET reservado = 'si' where id = " + menu_idint;
                
       
	try {
                    
             conexion = ConexionBD.conectar();
             Statement st = conexion.createStatement();
            
             st.executeUpdate(sentencia_guardar);  
             
             JOptionPane.showMessageDialog(null, "Mesa reservada!!");
             } catch (Exception e) {
             System.out.println(e.getMessage());  
             }
}

    //METODO PARA QUITAR MESA
    public void liberarMesa(){
        
        int fsel = tblMesas.getSelectedRow();
        //String estado = tblMesas.getValueAt(fsel2, 2).toString();
        
        String  menu_id = tblMesas.getValueAt(fsel, 0).toString();
        int menu_idint =   Integer.parseInt(menu_id);
        
	Connection conexion = null;
        
        
        String sentencia_guardar = "UPDATE mesas SET reservado = 'no' where id = " + menu_idint;
       
	try {
                    
             conexion = ConexionBD.conectar();
             Statement st = conexion.createStatement();
            
             st.executeUpdate(sentencia_guardar);  
             
             JOptionPane.showMessageDialog(null, "Mesa liberada!!");
             } catch (Exception e) {
             System.out.println(e.getMessage());  
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
        lbImagen = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jtxtCant = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProductos1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jtxtTotal = new javax.swing.JTextField();
        btnVolver = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Horario = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabelnombre = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnVerCartilla = new javax.swing.JButton();
        cmbTipo = new javax.swing.JComboBox<>();
        btnBuscarTipo = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabelid = new javax.swing.JLabel();
        Calendario = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jtxtComensales = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMesas = new javax.swing.JTable();
        btnConfMesa = new javax.swing.JButton();
        btnQuitarMesa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblSeleccion.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        tblSeleccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Precio", "Cantidad", "Importe", "ID"
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

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setText("QUITAR");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        lbImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Cant:");

        jtxtCant.setText("1");

        tblProductos1.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        tblProductos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Precio", "Stock", "Id"
            }
        ));
        tblProductos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductos1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblProductos1);

        jLabel2.setText("Total:");

        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel3.setText("RESERVA");

        Horario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11:00", "12:00", "13:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));

        jButton1.setText("CONFIRMAR PLATO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelnombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Buscar");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnVerCartilla.setText("Ver Cartilla");
        btnVerCartilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerCartillaActionPerformed(evt);
            }
        });

        cmbTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbTipoMouseClicked(evt);
            }
        });
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        btnBuscarTipo.setText("Buscar Categoria");
        btnBuscarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTipoActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabelid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        Calendario.setDateFormatString("yyyy-MM-dd");

        jLabel4.setText("Comensales:");

        jtxtComensales.setText("1");

        jButton2.setText("TICKET");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tblMesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Numero", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tblMesas);

        btnConfMesa.setText("Confirmar Mesa");
        btnConfMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfMesaActionPerformed(evt);
            }
        });

        btnQuitarMesa.setText("Quitar Mesa");
        btnQuitarMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarMesaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabelid, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(101, 101, 101)
                                                .addComponent(btnBuscarTipo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnReset)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(102, 102, 102)))
                                        .addComponent(btnVerCartilla, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jtxtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jtxtComensales, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(Horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(298, 298, 298)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConfMesa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnQuitarMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabelnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVerCartilla, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscarTipo)
                            .addComponent(btnReset))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jtxtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jtxtComensales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(31, 31, 31)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConfMesa)
                            .addComponent(btnQuitarMesa)))
                    .addComponent(Calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductos1MouseClicked
        // TODO add your handling code here:
            MostrarImagen();    
    }//GEN-LAST:event_tblProductos1MouseClicked

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
//        Bienvenido bv = new Bienvenido();
//        bv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
          
         

        try {
            String producto, precio, cant, stock, importe, menu_id;
            double calculo = 0.0, subt = 0.0;
            int fsel = tblProductos1.getSelectedRow();
             stock = tblProductos1.getValueAt(fsel, 2).toString();
             int stockint = Integer.parseInt(stock);
             int canti = Integer.parseInt(jtxtCant.getText());
          
                

            if (fsel == -1 ) {
                 JOptionPane.showMessageDialog(null, "Debe seleccionar un producto");
            } else {
                  if(stockint > 0 &&  canti <= stockint) {
                        m = (DefaultTableModel) tblProductos1.getModel();
                       producto = tblProductos1.getValueAt(fsel, 0).toString();
                       precio = tblProductos1.getValueAt(fsel, 1).toString();
                       menu_id = tblProductos1.getValueAt(fsel, 3).toString();
                       cant =  jtxtCant.getText();

                       //Realizamos los calculos
                       subt = (Double.parseDouble(precio) * Integer.parseInt(cant));
                       importe = String.valueOf(subt);

                       m = (DefaultTableModel) tblSeleccion.getModel();
                       String index[] = {producto, precio, cant, importe, menu_id};
                       m.addRow(index);

                       calculo = (Double.parseDouble(precio) * Integer.parseInt(jtxtCant.getText()));
                       total = total + calculo;
                       jtxtTotal.setText("" + total);
                       
                     RestarStock();
                     mostrarDatos();
                } else {
                     JOptionPane.showMessageDialog(null, "no hay stock suficiente de este producto");
                }
               
            }
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
        double subt = 0.0, importe = 0.0, precioactual = 0.0;
        int fsel, respuesta;
        fsel = tblSeleccion.getSelectedRow();
        try {
            if (fsel == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar el producto a eliminar");
            } else {
                respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este producto?", "Eliminar", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_NO_OPTION) {
                    devolverStock();
                    mostrarDatos();
                    importe = Double.parseDouble(tblSeleccion.getValueAt(fsel, 3).toString());
                    precioactual = Double.parseDouble(jtxtTotal.getText()) - importe;
                    total = precioactual;
                    jtxtTotal.setText("" + total);
                    m = (DefaultTableModel) tblSeleccion.getModel();
                    m.removeRow(fsel);
                  
                }
                
         
            }
           
                    
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Articulo eliminado");
        }

    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnVerCartillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerCartillaActionPerformed
        // TODO add your handling code here:
        Cartilla ca = new Cartilla();
        ca.setVisible(true);
       //this.dispose();
    }//GEN-LAST:event_btnVerCartillaActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        filtrarDatos(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tblSeleccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSeleccionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSeleccionMouseClicked

    private void tblSeleccionComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tblSeleccionComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSeleccionComponentAdded

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:     
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void cmbTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbTipoMouseClicked
        // TODO add your handling code here:   
    }//GEN-LAST:event_cmbTipoMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnBuscarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTipoActionPerformed
        // TODO add your handling code here:
        buscarTipos(cmbTipo);
    }//GEN-LAST:event_btnBuscarTipoActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        mostrarDatos();
    }//GEN-LAST:event_btnResetActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
    int Seleccionada = tblSeleccion.getSelectedRow(); 
        
        try {
          if(Seleccionada == -1){
              JOptionPane.showMessageDialog(null, "Seleccione un plato para confirmar");
        }else {
            confirmarVenta();
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnConfMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfMesaActionPerformed
        // TODO add your handling code here:
        reservarMesa();
        mostrarMesas();
    }//GEN-LAST:event_btnConfMesaActionPerformed

    private void btnQuitarMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarMesaActionPerformed
        // TODO add your handling code here:
        liberarMesa();
        mostrarMesas();
    }//GEN-LAST:event_btnQuitarMesaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        Document documento = new Document();
        
        String usuarioid = jLabelid.getText();
        
        
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento,new  FileOutputStream(ruta + "/Desktop/" + jLabelnombre.getText().trim() + ".pdf" ));
            
            com.itextpdf.text.Image header =  com.itextpdf.text.Image.getInstance("src/imagenes/bannerPDF.jpg");
            header.scaleToFit(650,1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            
            
            //clientes
            
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Informacion del Cliente. \n \n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY ));
            
            documento.open();
            documento.add(header);
            documento.add(parrafo);
            
            PdfPTable tablaCliente = new PdfPTable(3);
            
            tablaCliente.addCell("ID");
            tablaCliente.addCell("nombre");
            tablaCliente.addCell("correo");
            
        
           
            
            try {
              Connection conexion = null;
              conexion = ConexionBD.conectar();
              PreparedStatement pst = conexion.prepareStatement("select id,nombre,correo from usuarios where id = '" + usuarioid + "' ");
              ResultSet rs = pst.executeQuery();
              
              if(rs.next()) {
                  do{
                      tablaCliente.addCell(rs.getString(1));
                      tablaCliente.addCell(rs.getString(2));
                      tablaCliente.addCell(rs.getString(3));
                  } while(rs.next());
                  
                  documento.add(tablaCliente);
                  
              }
              
              
              //PLATOS
              
            Paragraph parrafo2 = new Paragraph();
            parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo2.add("\n \n  Platos Registrados. \n \n");
            parrafo2.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY ));
            
            
             
            documento.add(parrafo2);
            
            PdfPTable tablaPlatos = new PdfPTable(4);
            
            tablaPlatos.addCell("ID plato");
            tablaPlatos.addCell("producto");
            tablaPlatos.addCell("cantidad");
            tablaPlatos.addCell("precio");
            
            
             
            
                try {
                    Connection conexion2 = null;
                    conexion2 = ConexionBD.conectar();
                    PreparedStatement pst2 = conexion2.prepareStatement("select m.menu_id, producto,venta.cantidad, precio from menu m inner join venta on m.menu_id =venta.menu_id where venta.usuarios_id = '" + usuarioid + "' ");
                    ResultSet rs2 = pst2.executeQuery();
                    
                  if(rs2.next()) {
                  do{
                       tablaPlatos.addCell(rs2.getString(1));
                       tablaPlatos.addCell(rs2.getString(2));
                       tablaPlatos.addCell(rs2.getString(3));
                       tablaPlatos.addCell(rs2.getString(4));
                  } while(rs2.next());
                  
                  documento.add(tablaPlatos);
                  
              }
                  
               } catch (Exception e) {
                    System.out.println(e.getMessage());
                    
                }
                
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            
             //MESA
                   /*   
                        select DISTINCT id
                        from mesas m
                        inner JOIN  venta on m.id = venta.mesas_id
                        where venta.usuarios_id= 2;
                  */  
                   
                    Paragraph parrafo3 = new Paragraph();
                    parrafo3.setAlignment(Paragraph.ALIGN_CENTER);
                    parrafo3.add("\n \n  Mesa Registrada. \n \n");
                    parrafo3.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY ));
                    
                    documento.add(parrafo3);
            
                    PdfPTable mesas = new PdfPTable(1);

                    mesas.addCell("Numero Mesas");
                    
                    
                    
                    
                    
                try {
                    Connection conexion3 = null;
                    conexion3 = ConexionBD.conectar();
                    PreparedStatement pst3 = conexion3.prepareStatement("select distinct id from mesas m inner join venta on m.id = venta.mesas_id where venta.usuarios_id = '" + usuarioid + "' ");
                    ResultSet rs3 = pst3.executeQuery();
                    
                  if(rs3.next()) {
                  do{
                       mesas.addCell(rs3.getString(1));
                       
                  } while(rs3.next());
                  
                  documento.add(mesas);
                  
              }
                  
               } catch (Exception e) {
                    System.out.println(e.getMessage());
                    
                }
                
                
                
                        
                    
                

                  
                  //TOTAL
                   
                  
                  /* 
                    select sum(subtotal) as total
                    from venta
                    where venta.usuarios_id= 2;  
                  
                  */
                  
                  
                    Paragraph parrafo4 = new Paragraph();
                    parrafo4.setAlignment(Paragraph.ALIGN_CENTER);
                    parrafo4.add("\n \n  Total a Pagar \n \n");
                    parrafo4.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY ));
                    
                    documento.add(parrafo4);
            
                    PdfPTable totalapagar = new PdfPTable(1);

                    totalapagar.addCell("Total");
                    
                    
                    
                    
                    
                try {
                    Connection conexion4 = null;
                    conexion4 = ConexionBD.conectar();
                    PreparedStatement pst4 = conexion4.prepareStatement("select sum(subtotal) as total from venta where venta.usuarios_id = '" + usuarioid + "' ");
                    ResultSet rs4 = pst4.executeQuery();
                    
                  if(rs4.next()) {
                  do{
                       totalapagar.addCell(rs4.getString(1));
                       
                  } while(rs4.next());
                  
                  documento.add(totalapagar);
                  
              }
                  
               } catch (Exception e) {
                    System.out.println(e.getMessage());
                    
                }
                
                
                
                    Paragraph parrafo5 = new Paragraph();
                    parrafo5.setAlignment(Paragraph.ALIGN_CENTER);
                    parrafo5.add("\n \n  Muchas Gracias por su compra , vuelva pronto!! \n \n");
                    parrafo5.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY ));   
                    
                    documento.add(parrafo5);
            
                   
                  
            
            
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Ticket generado correctamente, muchas gracias por su compra");
            
            
           
            
            
            
            
            
            
            
      
            
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
        
        
        
        
        
       
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private com.toedter.calendar.JDateChooser Calendario;
    private javax.swing.JComboBox<String> Horario;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarTipo;
    private javax.swing.JButton btnConfMesa;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnQuitarMesa;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnVerCartilla;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabelid;
    public javax.swing.JLabel jLabelnombre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jtxtCant;
    private javax.swing.JTextField jtxtComensales;
    private javax.swing.JTextField jtxtTotal;
    private javax.swing.JLabel lbImagen;
    private javax.swing.JTable tblMesas;
    private javax.swing.JTable tblProductos1;
    private javax.swing.JTable tblSeleccion;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
