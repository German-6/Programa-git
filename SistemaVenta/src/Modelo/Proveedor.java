
package Modelo;


public class Proveedor {
    // Atributos de la clase Proveedor
    private int id; // Identificador único del proveedor
    private Long nit; // Número de Identificación Tributaria del proveedor
    private String nombre; // Nombre del proveedor
    private Long telefono; // Número de teléfono del proveedor
    private String direccion; // Dirección del proveedor
    private String razon; // Razón social del proveedor
    
    // Constructor vacío (por defecto)
    public Proveedor(){
        
    }
    
    // Constructor con todos los atributos
    public Proveedor(int id, Long nit, String nombre, Long telefono, String direccion, String razon) {
        this.id = id;
        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razon = razon;
    }
    
    // Métodos getters y setters para acceder y modificar los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
    
}
