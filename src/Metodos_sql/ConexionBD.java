/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodos_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author damia
 */
public class ConexionBD {
   public static String url = "jdbc:mysql://localhost/restaurante";
   public static String usuario ="root";
   public static String contraseña ="";
   public static String clase = "com.mysql.cj.jdbc.Driver";
   
   public static Connection conectar() {
      Connection conexion = null;
      
      try{
          Class.forName(clase);
          conexion = (Connection) DriverManager.getConnection(url, usuario, contraseña);
          System.out.println("conexion exitosa");
      
      } catch (Exception e) {
            System.out.println(e.getMessage());
      }    
     
      return conexion;
       
   }
   
   
}
