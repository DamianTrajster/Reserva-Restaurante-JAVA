package empleados;

import Conexion.Conectar;
import java.sql.*;
import java.util.ArrayList;


/*Metodo listar*/
public class ProductoDAO{

    public ArrayList<ProductoVO> Listar_ProductoVO(){
        ArrayList<ProductoVO> list = new ArrayList<ProductoVO>();
        Conectar conec = new Conectar();
        String sql = "SELECT * FROM menu;";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                ProductoVO vo = new ProductoVO();
                vo.setIdproducto(rs.getInt(1));
                vo.setNombre(rs.getString(2));
                vo.setPrecio(rs.getDouble(3));
                vo.setStock(rs.getInt(4));
                vo.setTipo(rs.getString(5));
                vo.setFoto(rs.getBytes(6));
                list.add(vo);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                rs.close();
                conec.desconectar();
            }catch(Exception ex){}
        }
        return list;
    }


/*Metodo agregar*/
    public void Agregar_ProductoVO(ProductoVO vo){
        Conectar conec = new Conectar();
        String sql = "INSERT INTO menu (menu_id, producto, precio, stock,tipo, foto)\n" +
"VALUES (NULL,?,?,?,?,?);";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getNombre());
            ps.setDouble(2, vo.getPrecio());
            ps.setInt(3, vo.getStock());
            ps.setString(4, vo.getTipo());
            ps.setBytes(5, vo.getFoto());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println("A "+ex.getMessage());
        }catch(Exception ex){
            System.out.println("B "+ex.getMessage());
        }finally{
            try{
                ps.close();
                conec.desconectar();
            }catch(Exception ex){}
        }
    }


/*Metodo Modificar*/
    public void Modificar_ProductoVO(ProductoVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE menu SET producto = ?, precio = ?, stock = ?,tipo = ? , foto = ?\n" +
"WHERE menu_id = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getNombre());
            ps.setDouble(2, vo.getPrecio());
            ps.setInt(3, vo.getStock());
            ps.setString(4, vo.getTipo());
            ps.setBytes(5, vo.getFoto());
            ps.setInt(6, vo.getIdproducto());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conec.desconectar();
            }catch(Exception ex){}
        }
    }

    public void Modificar_ProductoVO2(ProductoVO vo){
        Conectar conec = new Conectar();
        String sql = "UPDATE menu SET producto = ?, precio = ?, stock = ?,tipo = ? \n" +
"WHERE menu_id = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setString(1, vo.getNombre());
            ps.setDouble(2, vo.getPrecio());
            ps.setInt(3, vo.getStock());
            ps.setString(5, vo.getTipo());
            ps.setInt(6, vo.getIdproducto());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conec.desconectar();
            }catch(Exception ex){}
        }
    }

/*Metodo Eliminar*/
    public void Eliminar_ProductoVO(ProductoVO vo){
        Conectar conec = new Conectar();
        String sql = "DELETE FROM menu WHERE menu_id = ?;";
        PreparedStatement ps = null;
        try{
            ps = conec.getConnection().prepareStatement(sql);
            ps.setInt(1, vo.getIdproducto());
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conec.desconectar();
            }catch(Exception ex){}
        }
    }


}
