package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JComboBox;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductosDao {

    // Variables para manejar la conexión a la base de datos y ejecutar consultas SQL
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    // Método para registrar un nuevo producto en la base de datos
    public boolean RegistrarProductos(Productos pro) {
        String sql = "INSERT INTO productos (codigo, nombre, proveedor, stock, precio) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection(); // Obtener la conexión a la base de datos
            ps = con.prepareStatement(sql); // Preparar la consulta SQL
            // Asignar los valores de los campos del producto a los parámetros de la consulta
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setDouble(5, pro.getPrecio());
            ps.execute(); // Ejecutar la consulta INSERT
            return true; // Retornar true si la inserción fue exitosa
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;// Retornar false si hubo un error
        }
    }
    
    // Método para consultar y agregar nombres de proveedores al JComboBox
    public void ConsultarProveedor(JComboBox proveedor){
        String sql = "SELECT nombre FROM proveedor";
        try {
            con = cn.getConnection(); // Obtener la conexión a la base de datos
            ps = con.prepareStatement(sql); // Preparar la consulta SQL
            rs = ps.executeQuery(); // Ejecutar la consulta SELECT y obtener los resultados
            while (rs.next()) {
                proveedor.addItem(rs.getString("nombre"));// Agregar el nombre del proveedor al JComboBox
            }
        } catch (SQLException e) {
            System.out.println(e.toString());// Imprimir el error en caso de excepción
        }
    }
    
    // Método para listar todos los productos en la base de datos
    public List ListarProductos(){
        List<Productos> Listapro = new ArrayList(); // Crear una lista para almacenar los productos
        String sql = "SELECT * FROM productos";
        try{
            con = cn.getConnection(); // Obtener la conexión a la base de datos
            ps = con.prepareStatement(sql); // Preparar la consulta SQL
            rs = ps.executeQuery(); // Ejecutar la consulta SELECT y obtener los resultados
            while(rs.next()){
                Productos pro = new Productos();// Crear un nuevo objeto Productos
                // Asignar los valores de la base de datos a los atributos del objeto Productos
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setProveedor(rs.getString("proveedor"));
                pro.setStock(rs.getInt("stock"));
                pro.setPrecio(rs.getDouble("precio"));
                Listapro.add(pro); // Agregar el producto a la lista
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return Listapro;
    }
    
     // Método para eliminar un producto de la base de datos según su ID
    public boolean EliminarProductos(int id){
        String sql = "DELETE FROM productos WHERE id = ?";
        try{
            ps = con.prepareStatement(sql); // Preparar la consulta SQL
            ps.setInt(1, id); // Asignar el ID del producto a eliminar
            ps.execute(); // Ejecutar la consulta DELETE
            return true; // Retornar true si la eliminación fue exitosa
        }catch (SQLException e){
            System.out.println(e.toString());// Imprimir el error en caso de excepción
            return false;// Retornar false si hubo un error
        }finally{
            try {
                con.close();// Cerrar la conexión a la base de datos
            } catch (SQLException ex) {
                System.out.println(ex.toString());// Imprimir el error al cerrar la conexión
            }
        }
    }
    
    // Método para modificar los datos de un producto en la base de datos
    public boolean ModificarProductos(Productos pro){
        String sql = "UPDATE productos SET codigo=?, nombre=?, proveedor=?, stock=?, precio=? WHERE id=?";
        try {
          con = cn.getConnection(); // Obtener la conexión a la base de datos
          ps = con.prepareStatement(sql); // Preparar la consulta SQL
          // Establecer los parámetros de la consulta con los nuevos datos del producto
          ps.setString(1, pro.getCodigo());
          ps.setString(2, pro.getNombre());
          ps.setString(3, pro.getProveedor());
          ps.setInt(4, pro.getStock());
          ps.setDouble(5, pro.getPrecio());
          ps.setInt(6, pro.getId()); // Establecer el parámetro ID de la consulta UPDATE
          ps.execute(); // Ejecutar la consulta UPDATE
          return true; // Indicar que la actualización fue exitosa
        } catch (SQLException e) {
            System.out.println(e.toString()); // Imprimir el error en caso de excepción
            return false;// Indicar que hubo un error en la actualización
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}
