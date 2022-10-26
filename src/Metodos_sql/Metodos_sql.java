/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos_sql;


import static Metodos_sql.ConexionBD.conectar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;


/**
 *
 * @author damia
 */
public class Metodos_sql {

    public static ConexionBD conexion= new ConexionBD();
    
    public static PreparedStatement sentencia_preparada;
    public static ResultSet resultado;
    public static String sql;
    public static int resultado_numero = 0 ;
    
    
    //Metodo para guardar el usuario en la Base de datos
    public int guardar(String nombre, String user, String contraseña, String correo) {
       int resultado = 0;
       Connection conexion = null;
       
       String sentencia_guardar = ("insert into usuarios(nombre,user,contraseña,correo)values (?,?,?,?)");
       
        try {
            conexion = ConexionBD.conectar();
            
            sentencia_preparada = conexion.prepareStatement(sentencia_guardar);
            
           
            sentencia_preparada.setString(1, nombre);
            sentencia_preparada.setString(2, user);
            sentencia_preparada.setString(3, contraseña);
            sentencia_preparada.setString(4, correo);
            
            resultado = sentencia_preparada.executeUpdate();
            
               conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
        return resultado;
    }
    
    //metodo para buscar el nombre
    public static String buscarNombre (String user){
        
        String busqueda_nombre = null;
        Connection conexion = null;
        try {
            conexion  = ConexionBD.conectar();
            
            String sentencia_buscar=("select nombre from usuarios where user = '" + user + "'" );
            
            sentencia_preparada = conexion.prepareStatement(sentencia_buscar);
            resultado= sentencia_preparada.executeQuery();
            
            if(resultado.next()){
                String nombre = resultado.getString("nombre");
                busqueda_nombre = nombre;
            }
            
            conexion.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return busqueda_nombre;
    }
    
    //metodo para ver si estoy registrado
    public static String buscarUsuarioRegistrado(String user, String contreseña){
        String busqueda_usuario = null;
        
        Connection conexion = null;
        
        try {
            conexion = ConexionBD.conectar();
            String sentencia_buscar_usuario = ("select nombre, user,contraseña from usuarios  where user ='" +user+ "' && contraseña= '" + contreseña + "'" );
            sentencia_preparada= conexion.prepareStatement(sentencia_buscar_usuario);
            
            resultado = sentencia_preparada.executeQuery();
            
            if(resultado.next()){
                busqueda_usuario= "usuario encontrado";
                
                
            }else {
                 busqueda_usuario= "usuario no encontrado";
            }
            
        conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return busqueda_usuario;
    }
    
    //metodo para tomar los datos de la tabla Menu
    public static ResultSet getTabla(String Consulta){
       Connection conexion = conectar();
       Statement st;
       ResultSet datos=null;
       try{
           st = conexion.createStatement();
           datos = st.executeQuery(Consulta);
       } catch (Exception e){System.out.println(e.toString());}
       return datos;
   }
    
    


}
