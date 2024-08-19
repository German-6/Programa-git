package Modelo;

public class Productos {
// Variables de instancia para almacenar la información de un producto
    private int id;
    private String codigo;
    private String nombre;
    private String proveedor;
    private int stock;
    private double precio;

    // Constructor por defecto
    public Productos() {
        // Inicialización de un objeto Producto sin parámetros
    }

    // Constructor con parámetros para inicializar un producto con datos específicos
    public Productos(int id, String codigo, String nombre, String proveedor, int stock, double precio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.stock = stock;
        this.precio = precio;
    }

    // Métodos getter y setter para acceder y modificar los atributos del producto
    public int getId() {
        return id; // Devuelve el identificador del producto
    }

    public void setId(int id) {
        this.id = id; // Establece un nuevo valor para el identificador del producto
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
