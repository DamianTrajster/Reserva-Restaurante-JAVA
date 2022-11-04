package usuarios;

import Metodos_sql.ConexionBD;
import Metodos_sql.Metodos_sql;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


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
        
     }

    //Metodo para cancelar la compra de Productos
    public void limpiarPanel() {
        int limp;
        limp = JOptionPane.showConfirmDialog(null, "¿Está seguro de cancelar la compra?", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (limp == JOptionPane.YES_NO_OPTION) {
            DefaultTableModel modelo = (DefaultTableModel) tblSeleccion.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);

            }
            total = 0;
            jtxtTotal.setText(null);
        }
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
           
    
    //Metodo agregar mesa / usuario y productos a la db
  /*  public void  agregarTodo() {
         int fsel = tblSeleccion.getSelectedRow();
        String menuid = tblSeleccion.getValueAt(fsel, 4).toString();
        int menu_id= Integer.parseInt(menuid);
        
        String usuarioid = jLabelid.getText();
        int usuario_id= Integer.parseInt(usuarioid);
        
        
        String MesaElegida = lblMesaElegida.getText();
        int mesa_id = Integer.parseInt(MesaElegida);
        
        
        Connection conexion = null;
        String sentencia_guardar = "insert into reservas (id,usuarios_id,mesas_id, menu_id) values(null,'"+ usuario_id +"','"+ mesa_id  +"','" + menu_id  + "')";
        
        
          try {

                              conexion = ConexionBD.conectar();
                              Statement st = conexion.createStatement();
                              st.executeUpdate(sentencia_guardar);
                                  
                              
                                
                            
                          } catch (Exception e) {
                               System.out.println(e.getMessage());
                              
                         }
        
    }
     
         */  
     


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
        Dias = new javax.swing.JComboBox<>();
        Meses = new javax.swing.JComboBox<>();
        Horario = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabelnombre = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnVerCartilla = new javax.swing.JButton();
        lblMesaElegida = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        btnBuscarTipo = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabelid = new javax.swing.JLabel();

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

        jlbImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        btnConfirmar.setText("ELEGIR MESAS");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setText("LIMPIAR PRODUCTOS");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        jLabel3.setText("RESERVA");

        Dias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        Meses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        Horario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11:00", "12:00", "13:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00" }));

        jButton1.setText("CONFIRMAR");
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

        lblMesaElegida.setText("1");
        lblMesaElegida.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
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
                                            .addComponent(jtxtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Dias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Meses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlbImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnConfirmar)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblMesaElegida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabelid, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(jLabel3)))
                .addContainerGap(17, Short.MAX_VALUE))
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
                        .addComponent(jLabelid, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
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
                        .addComponent(jlbImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jtxtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Dias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Meses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Horario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMesaElegida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductos1MouseClicked
        // TODO add your handling code here:
        
      
        
        
       
        
    }//GEN-LAST:event_tblProductos1MouseClicked

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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarPanel();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
        //agregarTodo();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JComboBox<String> Dias;
    private javax.swing.JComboBox<String> Horario;
    private javax.swing.JComboBox<String> Meses;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarTipo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnVerCartilla;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabelid;
    public javax.swing.JLabel jLabelnombre;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jlbImagen;
    private javax.swing.JTextField jtxtCant;
    private javax.swing.JTextField jtxtTotal;
    private javax.swing.JLabel lblMesaElegida;
    private javax.swing.JTable tblProductos1;
    private javax.swing.JTable tblSeleccion;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
