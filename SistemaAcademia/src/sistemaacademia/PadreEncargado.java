package sistemaacademia;
public class PadreEncargado {
    
    private String nombreEncargado;
    private String apellidoEncargado;
    private long numeroTelefonoEncargado;
    private String relacion;
    private String direccion;    
    private String correo; 
    private int Id;
    
    public PadreEncargado(String nombreEncargado, String apellidoEncargado, long numeroTelefonoEncargado, String relacion, String direccion, String correo, int Id){
        this.nombreEncargado = nombreEncargado;
        this.apellidoEncargado = apellidoEncargado;
        this.numeroTelefonoEncargado = numeroTelefonoEncargado;
        this.relacion = relacion;
        this.direccion = direccion;
        this.correo = correo;
        this.Id = Id;
    }
    
    public PadreEncargado() {
        this.nombreEncargado = "";
        this.apellidoEncargado = "";
        this.numeroTelefonoEncargado = 0;
        this.relacion = "";
        this.direccion = "";    
        this.correo = "";
        this.Id = 0;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }
    
    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public String getApellidoEncargado() {
        return apellidoEncargado;
    }
    
    public void setApellidoEncargado(String apellidoEncargado) {
        this.apellidoEncargado = apellidoEncargado;
    }

    public long getNumeroTelefonoEncargado() {
        return numeroTelefonoEncargado;
    }
    
    public void setNumeroTelefonoEncargado(long numeroTelefonoEncargado) {
        this.numeroTelefonoEncargado = numeroTelefonoEncargado;
    }

    public String getRelacion() {
        return relacion;
    }
    
    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getDireccionEncargado() {
        return direccion;
    }
    
    public void setDireccionEncargado(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoEncargado() {
        return correo;
    }

    public void setCorreoEncargado(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    void setId(String idPadre) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
