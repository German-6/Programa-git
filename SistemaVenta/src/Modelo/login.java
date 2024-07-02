
package Modelo;

public class login {
    private int id;
    private String nombre;
    private String Correo;
    private String pass;

    public login() {
    }

    public login(int id, String nombre, String Correo, String pass) {
        this.id = id;
        this.nombre = nombre;
        this.Correo = Correo;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}
