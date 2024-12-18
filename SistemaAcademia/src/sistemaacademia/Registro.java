package sistemaacademia;
import java.time.LocalDate;
import javax.swing.JOptionPane;
public class Registro {
    private Alumnos datos[]=new Alumnos[100];
    private PadreEncargado encargados[] = new PadreEncargado[100];
    private Instructores instructores[] = new Instructores[100];
    private Facturas facturas[] = new Facturas[100];
    private int idPadre = 1;
    private int contadorFacturas = 1;
    private int x;
    private int cantidadActual = 0;
    private Sede[] sedes = new Sede[100];
    private int cantidadSedes = 0;

   
public void registrarUsuario(){
    while (true) {
        Alumnos alumno = new Alumnos();
        alumno.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nombre del alumno:"));
        alumno.setApellido(JOptionPane.showInputDialog(null, "Ingrese el apellido del alumno:"));
        byte edad = Byte.parseByte(JOptionPane.showInputDialog(null, "Ingrese la edad del alumno:"));
        if (edad < 5 || edad > 10) {
            JOptionPane.showMessageDialog(null, "Solo se admiten alumnos con edad entre 5 y 10 años. Intente nuevamente.");
            return;
        }
        alumno.setEdad(edad);
        alumno.setNickname(JOptionPane.showInputDialog(null, "Defina un usuario:"));
        alumno.setPassword(JOptionPane.showInputDialog(null, "Defina una contrasena:"));

        String categoriaNombre = JOptionPane.showInputDialog(null, "Defina la categoria inicial del alumno (Principiante, Intermedio, Avanzado o Competidor Junior)");

        String caracteristicas = obtenerCaracteristicasPorCategoria(categoriaNombre);
        String estado = "Activa";

        Categoria categoria = new Categoria(categoriaNombre, caracteristicas, estado);
        alumno.setCategoria(categoria);

        String estadoUsuario = JOptionPane.showInputDialog(null, "Desea activar este usuario? (Si / No)").toLowerCase();
        switch (estadoUsuario) {
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

public void mostrarUsuario() {
    StringBuilder sb = new StringBuilder();
    boolean hayUsuarios = false;

    for (int x = 0; x < cantidadActual; x++) {   
        if (datos[x].getEstado().equalsIgnoreCase("Activo")) {
            sb.append("Nombre: ").append(datos[x].getNombre())
              .append("\nApellido: ").append(datos[x].getApellido())
              .append("\nEdad: ").append(datos[x].getEdad())
              .append("\nEstado: ").append(datos[x].getEstado());

            if (datos[x].getCategoria() != null) {
                Categoria categoria = datos[x].getCategoria();
                String categoriaNombre = categoria.getNombre();
                String categoriaCaracteristicas = obtenerCaracteristicasPorCategoria(categoriaNombre);
                
                sb.append("\nCategoría: ").append(categoriaNombre)
                  .append("\nCaracterísticas: ").append(categoriaCaracteristicas)
                  .append("\nEstado de la categoría: ").append(categoria.getEstado());
            } else {
                sb.append("\nCategoría: No asignada");
            }
            
            if (encargados[x] != null) {
                sb.append("\n*** Información del Padre o Encargado ***")
                  .append("\nNombre: ").append(encargados[x].getNombreEncargado())
                  .append("\nApellido: ").append(encargados[x].getApellidoEncargado())
                  .append("\nTeléfono: ").append(encargados[x].getNumeroTelefonoEncargado())
                  .append("\nRelación: ").append(encargados[x].getRelacion());
            } else {
                sb.append("\n*** No tiene un padre o encargado registrado ***");
            }
            if (instructores[x] != null) {
                sb.append("\n*** Información del Instructor ***")
                  .append("\nNombre: ").append(instructores[x].nombreInstructor)
                  .append("\nHorario: ").append(instructores[x].horario);
            } else {
                sb.append("\n*** No tiene un instructor asignado ***");
            }

            if (datos[x].getSede() != null) {
                sb.append("\n*** Información de la Sede ***")
                  .append("\nNombre de la Sede: ").append(datos[x].getSede().getNombre());
            } else {
                sb.append("\n*** No tiene una sede asignada ***");
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
                    "Nombre actual: " + alumno.getNombre() + "\nIngrese el nuevo nombre (o deje vacío para no cambiar):, ");
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
            "Seleccione el número del padre para editar la información:"));

        if (opcion < 1 || opcion > cantidadActual) {
            JOptionPane.showMessageDialog(null, "Opción no válida.");
            return;
        }

        int indicePadre = opcion - 1;
        PadreEncargado padre = encargados[indicePadre];

        padre.setNombreEncargado(JOptionPane.showInputDialog("Ingrese el nuevo nombre del padre:", padre.getNombreEncargado()));
        padre.setApellidoEncargado(JOptionPane.showInputDialog("Ingrese el nuevo apellido del padre:", padre.getApellidoEncargado()));
        padre.setNumeroTelefonoEncargado(Long.parseLong(JOptionPane.showInputDialog("Ingrese el nuevo número de teléfono:", padre.getNumeroTelefonoEncargado())));
        padre.setRelacion(JOptionPane.showInputDialog("Ingrese la nueva relación con el alumno:", padre.getRelacion()));
        padre.setDireccionEncargado(JOptionPane.showInputDialog("Ingrese la nueva direccion del padre:", padre.getDireccionEncargado()));
        padre.setCorreoEncargado(JOptionPane.showInputDialog("Ingrese la nueva relación con el alumno:", padre.getCorreoEncargado()));

        JOptionPane.showMessageDialog(null, "Información del padre editada con éxito.");
    }
    public void agregarSede() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la sede:");
        String direccion = JOptionPane.showInputDialog("Ingrese la dirección de la sede:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono de la sede:");

        Sede nuevaSede = new Sede(nombre, direccion, telefono);
        sedes[cantidadSedes] = nuevaSede;
        cantidadSedes++;
        JOptionPane.showMessageDialog(null, "Sede registrada con éxito.");
    }
    public void mostrarSedes() {
        if (cantidadSedes == 0) {
            JOptionPane.showMessageDialog(null, "No hay sedes registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidadSedes; i++) {
            sb.append("Sede ").append(i + 1).append(":\n")
              .append("Nombre: ").append(sedes[i].getNombre()).append("\n")
              .append("Dirección: ").append(sedes[i].getDireccion()).append("\n")
              .append("Teléfono: ").append(sedes[i].getTelefono()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }
    
    public void editarSede() {
        if (cantidadSedes == 0) {
            JOptionPane.showMessageDialog(null, "No hay sedes registradas para editar.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidadSedes; i++) {
            sb.append(i + 1).append(". ").append(sedes[i].getNombre()).append("\n");
        }

        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                "*** Lista de Sedes Registradas ***\n\n" + sb.toString() +
                "Seleccione el número de la sede para editar:"));

        if (opcion < 1 || opcion > cantidadSedes) {
            JOptionPane.showMessageDialog(null, "Opción no válida.");
            return;
        }

        int indiceSede = opcion - 1;
        Sede sede = sedes[indiceSede];

        String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la sede:", sede.getNombre());
        String nuevaDireccion = JOptionPane.showInputDialog("Ingrese la nueva dirección de la sede:", sede.getDireccion());
        String nuevoTelefono = JOptionPane.showInputDialog("Ingrese el nuevo teléfono de la sede:", sede.getTelefono());

        sede.setNombre(nuevoNombre);
        sede.setDireccion(nuevaDireccion);
        sede.setTelefono(nuevoTelefono);

        JOptionPane.showMessageDialog(null, "La información de la sede ha sido actualizada.");
    }
        public void inactivarSede() {
        if (cantidadSedes == 0) {
            JOptionPane.showMessageDialog(null, "No hay sedes registradas para inactivar.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidadSedes; i++) {
            sb.append(i + 1).append(". ").append(sedes[i].getNombre()).append("\n");
        }

        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                "*** Lista de Sedes Registradas ***\n\n" + sb.toString() +
                "Seleccione el número de la sede para inactivar:"));

        if (opcion < 1 || opcion > cantidadSedes) {
            JOptionPane.showMessageDialog(null, "Opción no válida.");
            return;
        }

        int indiceSede = opcion - 1;
        sedes[indiceSede] = null;

        JOptionPane.showMessageDialog(null, "Sede inactivada correctamente.");
    }
        
    public void asignarSedeAAlumno() {
        if (cantidadSedes == 0) {
            JOptionPane.showMessageDialog(null, "No hay sedes registradas.");
            return;
        }

        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados.");
            return;
        }

        StringBuilder sbSedes = new StringBuilder();
        for (int i = 0; i < cantidadSedes; i++) {
            sbSedes.append(i + 1).append(". ").append(sedes[i].getNombre()).append("\n");
        }

        int opcionSede = Integer.parseInt(JOptionPane.showInputDialog(
                "*** Lista de Sedes ***\n\n" + sbSedes.toString() +
                "Seleccione la sede a asignar:"));

        if (opcionSede < 1 || opcionSede > cantidadSedes) {
            JOptionPane.showMessageDialog(null, "Opción no válida.");
            return;
        }

        StringBuilder sbAlumnos = new StringBuilder();
        for (int i = 0; i < cantidadActual; i++) {
            sbAlumnos.append(i + 1).append(". ").append(datos[i].getNombre()).append(" ").append(datos[i].getApellido()).append("\n");
        }

        int opcionAlumno = Integer.parseInt(JOptionPane.showInputDialog(
                "*** Lista de Alumnos ***\n\n" + sbAlumnos.toString() +
                "Seleccione el número del alumno para asignarle la sede:"));

        if (opcionAlumno < 1 || opcionAlumno > cantidadActual) {
            JOptionPane.showMessageDialog(null, "Opción no válida.");
            return;
        }

        int indiceAlumno = opcionAlumno - 1;
        datos[indiceAlumno].setSede(sedes[opcionSede - 1]);
        JOptionPane.showMessageDialog(null, "Sede asignada al alumno con éxito.");
    }
    
    public void asignarInstructorAutomatico(){
        if (cantidadActual == 0) {
        JOptionPane.showMessageDialog(null, "No hay alumnos registrados para asignar instructores.");
        return;
        }

        for (int i = 0; i < cantidadActual; i++) {
            Alumnos alumno = datos[i];
            Instructores instructor = new Instructores(alumno);
            instructor.asignarInstructor();
            JOptionPane.showMessageDialog(null, 
                "Instructor asignado a " + alumno.getNickname() + "\n" +
                "Edad: " + alumno.getEdad() + "\n" +
                "Instructor: " + instructor.nombreInstructor + "\n" +
                "Horario: " + instructor.horario);
        }
      
    }
    
    public void mostrarListaInstructores(){
        String lista = "*** Lista de Instructores ***\n\n";
        lista += "1. Gerardo Gonzalez - Horario: Mañana (Edades 5-6)\n";
        lista += "2. Francisco Rios - Horario: Tarde (Edades 7-8)\n";
        lista += "3. Monica Diaz - Horario: Mañana (Edades 9-10)\n";

        JOptionPane.showMessageDialog(null, lista);
    }
    
    public void editarInstructorYHorario(){
       if (cantidadActual == 0) {
        JOptionPane.showMessageDialog(null, "No hay alumnos registrados para editar.");
        return;
        }

        String nickname = JOptionPane.showInputDialog("Ingrese el nickname del alumno:");
        boolean encontrado = false;

        for (int i = 0; i < cantidadActual; i++) {
            if (datos[i].getNickname().equals(nickname)) {
                encontrado = true;
                String nuevoInstructor = JOptionPane.showInputDialog("Ingrese el nuevo nombre del instructor:");
                String nuevoHorario = JOptionPane.showInputDialog("Ingrese el nuevo horario (Mañana/Tarde):");

                JOptionPane.showMessageDialog(null, 
                    "Instructor y horario actualizados:\n" +
                    "Alumno: " + nickname + "\n" +
                    "Nuevo Instructor: " + nuevoInstructor + "\n" +
                    "Nuevo Horario: " + nuevoHorario);
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró un alumno con ese nickname.");
        } 
    }

    public void asignarCategoria(){
        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados para asignar categorías.");
            return;
        }
        for (int i = 0; i < cantidadActual; i++) {
            Alumnos alumno = datos[i];
            if (alumno.isCategoriaEditada()) {
                JOptionPane.showMessageDialog(null, 
                    "La categoría de " + alumno.getNickname() + " ya ha sido asignada y editada. Solo se puede actualizar.");
                continue;
            }
            String nuevaCategoria = JOptionPane.showInputDialog("Ingrese la nueva categoría para " + alumno.getNickname() + ":");
            if (alumno.getCategoria() != null && alumno.getCategoria().getNombre().equals(nuevaCategoria)) {
                JOptionPane.showMessageDialog(null, "Esta categoría ya está asignada a " + alumno.getNickname());
                continue;
            }
            String nuevasCaracteristicas = obtenerCaracteristicasPorCategoria(nuevaCategoria);
            String nuevoEstado = JOptionPane.showInputDialog("Ingrese el estado de la nueva categoría (Activa/Inactiva):");

            Categoria nuevaCategoriaObj = new Categoria(nuevaCategoria, nuevasCaracteristicas, nuevoEstado);
            alumno.setCategoria(nuevaCategoriaObj);

            JOptionPane.showMessageDialog(null, 
                "Categoría asignada a " + alumno.getNickname() + ":\n" +
                "Categoría: " + nuevaCategoriaObj.getNombre() + "\n" +
                "Características: " + nuevaCategoriaObj.getCaracteristicas() + "\n" +
                "Estado: " + nuevaCategoriaObj.getEstado());
        }
    }
    
    public void mostrarListaCategorias(){
        String listaCategorias = "*** Lista de Categorías ***\n\n";
        listaCategorias += "1. Principiante - Cinturón Blanco, Posturas básicas - Estado: Activa\n";
        listaCategorias += "2. Intermedio - Cinturón Amarillo, Técnicas básicas de defensa - Estado: Activa\n";
        listaCategorias += "3. Avanzado - Cinturón Naranja, Katas básicas - Estado: Activa\n";
        listaCategorias += "4. Competidor Junior - Cinturón Verde, Participación en competencias - Estado: Activa\n";

        JOptionPane.showMessageDialog(null, listaCategorias);
    }
    
    public void editarCategoriaAlumno(){
        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados para editar.");
            return;
        }
        String nickname = JOptionPane.showInputDialog("Ingrese el nickname del alumno:");
        boolean encontrado = false;

        for (int i = 0; i < cantidadActual; i++) {
            if (datos[i].getNickname().equals(nickname)) {
                encontrado = true;

                Categoria categoriaActual = datos[i].getCategoria();

                JOptionPane.showMessageDialog(null,
                    "Categoría actual:\n" +
                    "Alumno: " + nickname + "\n" +
                    "Categoría: " + categoriaActual.getNombre() + "\n" +
                    "Características: " + categoriaActual.getCaracteristicas() + "\n" +
                    "Estado: " + categoriaActual.getEstado());

                String nuevaCategoria = JOptionPane.showInputDialog("Ingrese la nueva categoría del estudiante:");
                String nuevasCaracteristicas = obtenerCaracteristicasPorCategoria(nuevaCategoria);
                String nuevoEstado = JOptionPane.showInputDialog("Ingrese el estado de la nueva categoría (Activa/Inactiva):");

                categoriaActual.setNombre(nuevaCategoria);
                categoriaActual.setCaracteristicas(nuevasCaracteristicas);
                categoriaActual.setEstado(nuevoEstado);

                JOptionPane.showMessageDialog(null, 
                    "Categoría actualizada:\n" +
                    "Alumno: " + nickname + "\n" +
                    "Nueva categoría: " + categoriaActual.getNombre() + "\n" +
                    "Características: " + categoriaActual.getCaracteristicas() + "\n" +
                    "Estado: " + categoriaActual.getEstado());

                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró un alumno con ese nickname.");
        } 
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
    
    public void ActivarInactivarCategoria() {
        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados para editar.");
            return;
        }

        String nickname = JOptionPane.showInputDialog("Ingrese el nickname del alumno:");
        boolean encontrado = false;

        for (int i = 0; i < cantidadActual; i++) {
            if (datos[i].getNickname().equals(nickname)) {
                encontrado = true;
                Categoria categoriaActual = datos[i].getCategoria();

                if (categoriaActual == null) {
                    JOptionPane.showMessageDialog(null, "El alumno no tiene una categoría asignada.");
                    continue; // Continúa con el siguiente alumno
                }

                String nuevoEstado;
                if (categoriaActual.getEstado().equals("Activa")) {
                    nuevoEstado = "Inactiva";
                } else {
                    nuevoEstado = "Activa";
                }

                categoriaActual.setEstado(nuevoEstado);

                JOptionPane.showMessageDialog(null, 
                    "Categoría del alumno " + nickname + " actualizada:\n" +
                    "Estado: " + nuevoEstado + "\n" +
                    "Categoría: " + categoriaActual.getNombre() + "\n" +
                    "Características: " + categoriaActual.getCaracteristicas());
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontró un alumno con ese nickname.");
        }
    }
    
    public void crearFactura() {
        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados para asignar facturas.");
            return;
        }

        // Verificación del alumno
        String nickname = JOptionPane.showInputDialog("Ingrese el usuario del alumno:");
        String password = JOptionPane.showInputDialog("Ingrese la contraseña del alumno:");

        Alumnos alumnoSeleccionado = null;

        for (int i = 0; i < cantidadActual; i++) {
            if (datos[i].getNickname().equals(nickname) && datos[i].getPassword().equals(password)) {
                alumnoSeleccionado = datos[i];
                break;
            }
        }

        if (alumnoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            return;
        }

        // Crear factura
        double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto de la factura:"));
        LocalDate fecha = LocalDate.now();
        boolean estadoPago = true;

        facturas[cantidadActual] = new Facturas(contadorFacturas, monto, fecha, estadoPago, alumnoSeleccionado);
        JOptionPane.showMessageDialog(null, "Factura creada exitosamente con ID: " + contadorFacturas);

        contadorFacturas++; // Incrementar el contador de ID de factura
        cantidadActual++; 
    }
    
    public void mostrarFactura() {
        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay facturas registradas.");
            return;
        }
            // Crear lista de alumnos con facturas
        StringBuilder listaAlumnos = new StringBuilder("Seleccione un alumno:\n");
        int contador = 1;

        for (int i = 0; i < cantidadActual; i++) {
            if (facturas[i] != null) {
                listaAlumnos.append(contador)
                            .append(". ")
                            .append(facturas[i].getAlumno().getNombre())
                            .append(" - Correo: ")
                            .append(facturas[i].getAlumno().getCorreo())
                            .append("\n");
                contador++;
            }
        }
        
        String opcionStr = JOptionPane.showInputDialog(listaAlumnos);
        int opcion = Integer.parseInt(opcionStr);
        
            // Buscar y mostrar facturas del alumno seleccionado
        int seleccion = 0;
        contador = 1;
        for (int i = 0; i < cantidadActual; i++) {
            if (facturas[i] != null) {
                if (contador == opcion) {
                    seleccion = i;
                    break;
                }
                contador++;
            }
        }

        if (facturas[seleccion] != null) {
         Facturas factura = facturas[seleccion];
         String mensaje = "ID Factura: " + factura.getIdFactura() +
                          "\nMonto: " + factura.getMonto() +
                          "\nFecha: " + factura.getFechaEmision() +
                          "\nEstado de Pago: " + (factura.isEstadoPago() ? "Pagada" : "No Pagada") +
                          "\nAlumno: " + factura.getAlumno().getNombre();
         JOptionPane.showMessageDialog(null, mensaje);
        } else {
            JOptionPane.showMessageDialog(null, "Error al mostrar factura.");
        }
    }
    
    public void anularFactura() {
        if (cantidadActual == 0) {
            JOptionPane.showMessageDialog(null, "No hay alumnos registrados para asignar facturas.");
            return;
        }
        
        String nickname = JOptionPane.showInputDialog("Ingrese el usuario del alumno:");
        String password = JOptionPane.showInputDialog("Ingrese la contraseña del alumno:");

        Alumnos alumnoSeleccionado = null;

        for (int i = 0; i < cantidadActual; i++) {
            if (datos[i].getNickname().equals(nickname) && datos[i].getPassword().equals(password)) {
                alumnoSeleccionado = datos[i];
                break;
            }
        }

        if (alumnoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            return;
        }

        // Mostrar facturas del alumno seleccionado
        StringBuilder listaFacturas = new StringBuilder("Facturas del alumno:\n");

        for (int i = 0; i < cantidadActual; i++) {
            if (facturas[i] != null && facturas[i].getAlumno().equals(alumnoSeleccionado)) {
                listaFacturas.append("ID: ").append(facturas[i].getIdFactura())
                             .append(", Monto: ").append(facturas[i].getMonto())
                             .append(", Fecha: ").append(facturas[i].getFechaEmision())
                             .append("\n");
            }
        }

        if (listaFacturas.toString().equals("Facturas del alumno:\n")) {
            JOptionPane.showMessageDialog(null, "No hay facturas registradas para este alumno.");
            return;
        }

        String idFacturaStr = JOptionPane.showInputDialog(listaFacturas + "\nIngrese el ID de la factura a anular:");
        int idFactura = Integer.parseInt(idFacturaStr);

        // Buscar y anular factura
        for (int i = 0; i < cantidadActual; i++) {
            if (facturas[i] != null && facturas[i].getIdFactura() == idFactura) {
                facturas[i] = null;
                JOptionPane.showMessageDialog(null, "Factura anulada correctamente.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "No se encontró una factura con ese ID.");
    }
}


    
  


    