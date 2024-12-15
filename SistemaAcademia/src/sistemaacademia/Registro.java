package sistemaacademia;
import javax.swing.JOptionPane;
public class Registro {
    private Alumnos datos[]=new Alumnos[100];
    private PadreEncargado encargados[] = new PadreEncargado[100];
    private Instructores instructores[] = new Instructores[100];
    private int idPadre = 1;
    private int x;
    private int cantidadActual = 0;
    
    public void registrarUsuario(){
        while (true) {
            Alumnos alumno=new Alumnos();
            alumno.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nombre del alumno:"));
            alumno.setApellido(JOptionPane.showInputDialog(null, "Ingrese el apellido del alumno:"));
            byte edad = Byte.parseByte(JOptionPane.showInputDialog(null, "Ingrese la edad del alumno:"));
            if ( edad < 5 || edad > 10) {
                JOptionPane.showMessageDialog(null, "Solo se admiten alumnos con edad entre 5 y 10 años. Intente nuevamente.");
                return; 
            }
            alumno.setEdad(edad);
            alumno.setNickname(JOptionPane.showInputDialog(null, "Defina un usuario:"));
            alumno.setPassword(JOptionPane.showInputDialog(null, "Defina una contrasena:"));
            String estado = JOptionPane.showInputDialog(null, "Desea activar este usuario? (Si / No)").toLowerCase();
            switch (estado) {
                case "si":
                    alumno.setEstado("Activo");
                    datos[cantidadActual] = alumno;
                    cantidadActual++;
                    JOptionPane.showMessageDialog(null, "La información del alumno ha sido registrada y activada con éxito!");
                    break;
                case "no":
                    JOptionPane.showMessageDialog(null, "El usuario no fue registrado.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. El usuario no fue registrado.");
                    break;
            }
            String continuar = JOptionPane.showInputDialog(null, "¿Desea registrar otro estudiante? (Si / No)").toLowerCase();
            if (!continuar.equals("si")) {
                JOptionPane.showMessageDialog(null, "Saliendo del registro.");
                break;
            }
        }
    }
    
    public void mostrarUsuario(){
        StringBuilder sb = new StringBuilder();
        boolean hayUsuarios = false;
        
        for (int x = 0; x < cantidadActual; x++){   
          if (datos[x].getEstado().equalsIgnoreCase("Activo")) {
            sb.append("Nombre: ").append(datos[x].getNombre())
              .append("\nApellido: ").append(datos[x].getApellido())
              .append("\nEdad: ").append(datos[x].getEdad())
              .append("\nEstado: ").append(datos[x].getEstado())
              .append("\n");
            
            if (encargados[x] != null) {
                sb.append("*** Información del Padre o Encargado ***")
                  .append("\nNombre: ").append(encargados[x].getNombreEncargado())
                  .append("\nApellido: ").append(encargados[x].getApellidoEncargado())
                  .append("\nTeléfono: ").append(encargados[x].getNumeroTelefonoEncargado())
                  .append("\nRelación: ").append(encargados[x].getRelacion());
            } else {
                sb.append("*** No tiene un padre o encargado registrado ***");
            }
            if (instructores[x] != null) {
                    sb.append("\n*** Información del Instructor ***")
                      .append("\nNombre: ").append(instructores[x].nombreInstructor)
                      .append("\nHorario: ").append(instructores[x].horario);
                } else {
                    sb.append("\n*** No tiene un instructor asignado ***");
                }

            sb.append("\n\n");
            hayUsuarios = true; 
        }
      }
      if (hayUsuarios) {
        JOptionPane.showMessageDialog(null, "*** Lista de Alumnos Registrados ***\n\n" + sb.toString());
        } else { 
          JOptionPane.showMessageDialog(null, "No hay usuarios activos registrados.");
      }
        
    }
    
    public void eliminarUsuario() {
        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados para eliminar.");
            return;
        }

        // Mostrar lista de estudiantes
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidadActual; i++) {
            sb.append((i + 1) + ". " + datos[i].getNombre() + " " + datos[i].getApellido() + "\n");
        }
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                "*** Lista de Alumnos Registrados ***\n\n" +
                sb.toString() +
                "Seleccione el número del usuario que desea eliminar (0 para cancelar):"));

        // Validar opción de usuario
        if (opcion == 0) {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
            return;
        }
        if (opcion < 1 || opcion > cantidadActual) {
            JOptionPane.showMessageDialog(null, "Número no válido.");
            return;
        }

        // Confirmación de eliminación
        String confirmacion = JOptionPane.showInputDialog(null, 
                "¿Está seguro de que desea eliminar al usuario " + 
                datos[opcion - 1].getNombre() + " " + datos[opcion - 1].getApellido() + "? (si/no)");
        if (confirmacion != null && confirmacion.equalsIgnoreCase("si")) {
            // Eliminar usuario y desplazar los elementos en los arreglos
            for (int i = opcion - 1; i < cantidadActual - 1; i++) {
                datos[i] = datos[i + 1];
                encargados[i] = encargados[i + 1];
                instructores[i] = instructores[i + 1];
            }

            // Limpiar última posición
            datos[cantidadActual - 1] = null;
            encargados[cantidadActual - 1] = null;
            instructores[cantidadActual - 1] = null;

            cantidadActual--; // Reducir la cantidad de usuarios
            JOptionPane.showMessageDialog(null, "El usuario ha sido eliminado con éxito.");
        } else if (confirmacion != null && confirmacion.equalsIgnoreCase("no")) {
            JOptionPane.showMessageDialog(null, "Operación cancelada.");
        } else {
            JOptionPane.showMessageDialog(null, "Entrada no válida. Operación cancelada.");
        }
    }
    
    public void registrarEncargado(){
        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados para asignar un padre o encargado.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidadActual; i++) {
            sb.append((i + 1)).append(". ").append(datos[i].getNombre())
              .append(" ").append(datos[i].getApellido()).append("\n");
        }
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
            "*** Lista de Alumnos Registrados ***\n\n" +
            sb.toString() +
            "Seleccione el número del alumno para asignar un padre o encargado:"));

        if (opcion < 1 || opcion > cantidadActual) {
            JOptionPane.showMessageDialog(null, "Opción no válida.");
            return;
        }
        int indiceAlumno = opcion - 1;
        PadreEncargado encargado = new PadreEncargado();
        encargado.setNombreEncargado(JOptionPane.showInputDialog(null, "Ingrese el nombre del padre o encargado:"));
        encargado.setApellidoEncargado(JOptionPane.showInputDialog(null, "Ingrese el apellido del padre o encargado:"));
        encargado.setNumeroTelefonoEncargado(Long.parseLong(JOptionPane.showInputDialog(null, "Ingrese el número de teléfono:")));
        encargado.setRelacion(JOptionPane.showInputDialog(null, "Ingrese la relación con el alumno (padre, madre, tutor, etc.):"));
        encargados[indiceAlumno] = encargado; 
        JOptionPane.showMessageDialog(null, "Padre o encargado registrado con éxito.");
    }
    
    public void asignarInstructor(){
        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados para asignar un instructor.");
            return;
        }
        StringBuilder listaAlumnos = new StringBuilder();
        for (int i = 0; i < cantidadActual; i++) {
            listaAlumnos.append(i + 1).append(". ")
                        .append(datos[i].getNombre()).append(" ")
                        .append(datos[i].getApellido()).append("\n");
        }

        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, 
                "*** Lista de Alumnos ***\n\n" + listaAlumnos.toString() + 
                "\nSeleccione el número del alumno para asignar un instructor:"));
        
        if (opcion < 1 || opcion > cantidadActual) {
            JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
            return;
        }

        int indice = opcion - 1;
        if (instructores[indice] == null) {
            instructores[indice] = new Instructores(datos[indice]);
            instructores[indice].asignarInstructor();
        } else {
            JOptionPane.showMessageDialog(null, "Este alumno ya tiene un instructor asignado.");
        }
    }
    
    public void informacionAdicional(){
        if (cantidadActual == 0) {
        JOptionPane.showMessageDialog(null, "No hay alumnos registrados para añadir información adicional.");
        return;
        }
        String nickname = JOptionPane.showInputDialog(null, "Ingrese el usuario (nickname) del alumno para añadir información adicional:");
        boolean encontrado = false;
        for (int i = 0; i < cantidadActual; i++) {
            if (datos[i].getNickname().equals(nickname)) {
                encontrado = true;
                Alumnos alumno = datos[i];
                String nuevaCiudad = JOptionPane.showInputDialog(null, "Ingrese la ciudad del alumno:");
                String nuevaDireccion = JOptionPane.showInputDialog(null, "Ingrese la dirección del alumno:");
                String nuevoCorreo = JOptionPane.showInputDialog(null, "Ingrese el correo del alumno:");
                if (!nuevaCiudad.isEmpty()) {
                    alumno.setCiudad(nuevaCiudad);
                }
                if (!nuevaDireccion.isEmpty()) {
                    alumno.setDireccion(nuevaDireccion);
                }
                if (!nuevoCorreo.isEmpty()) {
                    alumno.setCorreo(nuevoCorreo);
                }
                JOptionPane.showMessageDialog(null, "La información adicional ha sido registrada con éxito!");
                break;
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró un alumno con el usuario proporcionado.");
        }
    }
    
    public void editarInformacion(){
        if (cantidadActual == 0) {
        JOptionPane.showMessageDialog(null, "No hay alumnos registrados para editar.");
        return;
        }
        String nickname = JOptionPane.showInputDialog(null, "Ingrese el usuario del alumno que desea editar:");
        boolean encontrado = false;
        for (int i = 0; i < cantidadActual; i++) {
            if (datos[i].getNickname().equals(nickname)) {
                encontrado = true;
                Alumnos alumno = datos[i];
                String nuevoNombre = JOptionPane.showInputDialog(null, 
                    "Nombre actual: " + alumno.getNombre() + "\nIngrese el nuevo nombre (o deje vacío para no cambiar):");
                String nuevoApellido = JOptionPane.showInputDialog(null, 
                    "Apellido actual: " + alumno.getApellido() + "\nIngrese el nuevo apellido (o deje vacío para no cambiar):");
                String nuevaEdadStr = JOptionPane.showInputDialog(null, 
                    "Edad actual: " + alumno.getEdad() + "\nIngrese la nueva edad (o deje vacío para no cambiar):");
                String nuevaCiudad = JOptionPane.showInputDialog(null, 
                    "Ciudad actual: " + alumno.getCiudad() + "\nIngrese la nueva ciudad (o deje vacío para no cambiar):");
                String nuevaDireccion = JOptionPane.showInputDialog(null, 
                    "Dirección actual: " + alumno.getDireccion() + "\nIngrese la nueva dirección (o deje vacío para no cambiar):");
                String nuevoCorreo = JOptionPane.showInputDialog(null, 
                    "Correo actual: " + alumno.getCorreo() + "\nIngrese el nuevo correo (o deje vacío para no cambiar):");
                if (!nuevoNombre.isEmpty()) {
                    alumno.setNombre(nuevoNombre);
                }
                if (!nuevoApellido.isEmpty()) {
                    alumno.setApellido(nuevoApellido);
                }
                if (!nuevaEdadStr.isEmpty()) {
                    try {
                        byte nuevaEdad = Byte.parseByte(nuevaEdadStr);
                        if (nuevaEdad >= 5 && nuevaEdad <= 10) {
                            alumno.setEdad(nuevaEdad);
                        } else {
                            JOptionPane.showMessageDialog(null, "La edad debe estar entre 5 y 10 años. No se aplicará el cambio.");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Edad no válida. No se aplicará el cambio.");
                    }
                }
                if (!nuevaCiudad.isEmpty()) {
                    alumno.setCiudad(nuevaCiudad);
                }
                if (!nuevaDireccion.isEmpty()) {
                    alumno.setDireccion(nuevaDireccion);
                }
                if (!nuevoCorreo.isEmpty()) {
                    alumno.setCorreo(nuevoCorreo);
                }
                JOptionPane.showMessageDialog(null, "Los cambios se han guardado exitosamente.");
                break;
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró un alumno con el usuario proporcionado.");
        }   
    }
    
    public void inactivarAlumno(){
       if (cantidadActual == 0) {
        JOptionPane.showMessageDialog(null, "No hay alumnos registrados para inactivar.");
        return;
        }
        String nickname = JOptionPane.showInputDialog(null, "Ingrese el usuario (nickname) del alumno para inactivarlo:");
        boolean encontrado = false;
        for (int i = 0; i < cantidadActual; i++) {
            if (datos[i].getNickname().equals(nickname)) {
                encontrado = true;
                Alumnos alumno = datos[i];
                alumno.setEstado("Inactivo");
                JOptionPane.showMessageDialog(null, "El alumno ha sido inactivado con éxito!");
                break;
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró un alumno con el usuario proporcionado.");
        } 
    } 
    
    public void asignarIdPadre(){
        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay padres registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidadActual; i++) {
            sb.append((i + 1)).append(". ").append(encargados[i].getNombreEncargado())
              .append(" ").append(encargados[i].getApellidoEncargado()).append("\n");
        }

        int opcion = Integer.parseInt(JOptionPane.showInputDialog(
            "*** Lista de Padres Registrados ***\n\n" +
            sb.toString() +
            "Seleccione el número del padre para asignar un ID:"));

        if (opcion < 1 || opcion > cantidadActual) {
            JOptionPane.showMessageDialog(null, "Opción no válida.");
            return;
        }

        int indicePadre = opcion - 1;
        PadreEncargado padre = encargados[indicePadre];
        padre.setId(idPadre++);
        JOptionPane.showMessageDialog(null, "ID asignado al padre " + padre.getNombreEncargado() + " con éxito. ID: " + padre.getId());
    }
    
    public void agregarInformacionAdicional(){
        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay padres registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidadActual; i++) {
            sb.append((i + 1)).append(". ").append(encargados[i].getNombreEncargado())
              .append(" ").append(encargados[i].getApellidoEncargado()).append("\n");
        }

        int opcion = Integer.parseInt(JOptionPane.showInputDialog(
            "*** Lista de Padres Registrados ***\n\n" +
            sb.toString() +
            "Seleccione el número del padre para agregar información adicional:"));

        if (opcion < 1 || opcion > cantidadActual) {
            JOptionPane.showMessageDialog(null, "Opción no válida.");
            return;
        }

        int indicePadre = opcion - 1;
        PadreEncargado padre = encargados[indicePadre];

        padre.setDireccionEncargado(JOptionPane.showInputDialog("Ingrese la dirección:"));
        padre.setCorreoEncargado(JOptionPane.showInputDialog("Ingrese el correo:"));

        JOptionPane.showMessageDialog(null, "Información adicional agregada con éxito.");
    }
    
    public void editarInformacionPadre(){
        
    }
     
}
