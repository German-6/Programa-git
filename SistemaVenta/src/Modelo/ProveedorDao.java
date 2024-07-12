
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Clase DAO (Data Access Object) para interactuar con la tabla 'proveedor' en la base de datos
public class ProveedorDao {
    Connection con; // Objeto Connection para la conexión a la base de datos
    Conexion cn = new Conexion(); // Instancia de la clase Conexion para establecer la conexión
    PreparedStatement ps; // Objeto PreparedStatement para ejecutar consultas preparadas
    ResultSet rs; // Objeto ResultSet para almacenar los resultados de consultas SELECT
    
    // Método para registrar un nuevo proveedor en la base de datos
    public boolean RegistrarProveedor(Proveedor pr){
        String sql = "INSERT INTO proveedor (nit, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection(); // Obtener la conexión a la base de datos
            ps = con.prepareStatement(sql); // Preparar la consulta SQL
            // Establecer los parámetros de la consulta con los datos del proveedor
            ps.setLong(1, pr.getNit());
            ps.setString(2, pr.getNombre());
            ps.setLong(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.execute(); // Ejecutar la consulta INSERT
            return true; // Indicar que la inserción fue exitosa
        } catch (SQLException e) {
            System.out.println(e.toString()); // Imprimir el error en caso de excepción
            return false; // Indicar que hubo un error en la inserción
        }finally{
            try {
                con.close(); // Cerrar la conexión a la base de datos
            } catch (SQLException e) {
                System.out.println(e.toString()); // Imprimir el error en caso de fallo al cerrar la conexión
            }
        }
                
    }
    
    // Método para listar todos los proveedores almacenados en la base de datos
    public List ListarProveedor() {
        List<Proveedor> Listapr = new ArrayList(); // Lista para almacenar los proveedores recuperados de la base de datos
        String sql = "SELECT * FROM proveedor"; // Consulta SQL para seleccionar todos los registros de la tabla 'proveedor'
        try {
            con = cn.getConnection(); // Obtener la conexión a la base de datos
            ps = con.prepareStatement(sql); // Preparar la consulta SQL
            rs = ps.executeQuery(); // Ejecutar la consulta SELECT y obtener los resultados
            while (rs.next()) {
                // Crear un objeto Proveedor para cada registro recuperado
                Proveedor pr = new Proveedor();
                // Establecer los atributos del proveedor con los datos recuperados de la base de datos
                pr.setId(rs.getInt("id"));
                pr.setNit(rs.getLong("nit"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getLong("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazon(rs.getString("razon"));
                Listapr.add(pr); // Agregar el proveedor a la lista
            }
        } catch (SQLException e) {
            System.out.println(e.toString()); // Imprimir el error en caso de excepción
        }
        return Listapr; // Devolver la lista de proveedores recuperados
    }
    
    // Método para eliminar un proveedor de la base de datos por su ID
    public boolean EliminarProveedor(int id) {
        String sql = "DELETE FROM proveedor WHERE id = ?"; // Consulta SQL para eliminar un proveedor por su ID
        try {
            con = cn.getConnection(); // Obtener la conexión a la base de datos
            ps = con.prepareStatement(sql); // Preparar la consulta SQL
            ps.setInt(1, id); // Establecer el parámetro ID de la consulta DELETE
            ps.execute(); // Ejecutar la consulta DELETE
            return true; // Indicar que la eliminación fue exitosa
        } catch (SQLException e) {
            System.out.println(e.toString());  // Imprimir el error en caso de excepción
            return false; // Indicar que hubo un error en la eliminación
        }finally{
            try {
                con.close();
            }catch (SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    
    // Método para modificar los datos de un proveedor en la base de datos
    public boolean ModificarProveedor(Proveedor pr){
        String sql = "UPDATE proveedor SET nit=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id=?";
        try {
          con = cn.getConnection(); // Obtener la conexión a la base de datos
          ps = con.prepareStatement(sql); // Preparar la consulta SQL
          // Establecer los parámetros de la consulta con los nuevos datos del proveedor
          ps.setLong(1, pr.getNit());
          ps.setString(2, pr.getNombre());
          ps.setLong(3, pr.getTelefono());
          ps.setString(4, pr.getDireccion());
          ps.setString(5, pr.getRazon());
          ps.setInt(6, pr.getId()); // Establecer el parámetro ID de la consulta UPDATE
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

