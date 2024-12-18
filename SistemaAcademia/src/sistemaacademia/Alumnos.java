package sistemaacademia;

public class Alumnos {

    private String nombre;
    private String apellido;
    private byte edad;
    private String nickname;
    private String password;
    private String estado;
    private String ciudad;
    private String direccion;
    private String correo;
    private Sede sede;
    private Categoria categoria;
    private boolean categoriaEditada;

public Alumnos(String nombre, String apellido, byte edad, String nickname, String password, String estado, String ciudad, String direccion, String correo, Categoria categoria) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
    this.nickname = nickname;
    this.password = password;
    this.estado = estado;
    this.ciudad = ciudad;
    this.direccion = direccion;
    this.correo = correo;
    this.sede = sede;
    this.categoria = categoria;
}


public Alumnos() {
    this.nombre = "";
    this.apellido = "";
    this.edad = 0;
    this.nickname = "";
    this.password = "";
    this.estado = "";
    this.ciudad = "";
    this.direccion = "";
    this.correo = "";
    this.sede = null;
    this.categoria = null;
    this.categoriaEditada = false;
}
private String obtenerCaracteristicasPorCategoria(String categoria) {
    switch (categoria) {
        case "Principiante":
            return "Cinturón Blanco, Posturas básicas";
        case "Intermedio":
            return "Cinturón Amarillo, Técnicas básicas de defensa";
        case "Avanzado":
            return "Cinturón Naranja, Katas básicas";
        case "Competidor Junior":
            return "Cinturón Verde, Participación en competencias";
        default:
            return "Características no disponibles";
    }
}
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isCategoriaEditada() {
        return categoriaEditada;
    }

    public void setCategoriaEditada(boolean categoriaEditada) {
        this.categoriaEditada = categoriaEditada;
    }

}