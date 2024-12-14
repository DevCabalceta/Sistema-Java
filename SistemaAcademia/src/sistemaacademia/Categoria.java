package sistemaacademia;
public class Categoria {
   private String nombre;
    private String caracteristicas;
    private String estado; 

    public Categoria(String nombre, String caracteristicas, String estado) {
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    } 
}
