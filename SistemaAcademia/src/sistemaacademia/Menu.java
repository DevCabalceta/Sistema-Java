package sistemaacademia;
import javax.swing.JOptionPane;
public class Menu {
    private int opcion;
    Registro r=new Registro();
    
    public void mostrarMenuprincipal(){
        while(true){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "***Academia de Karate Soul and Mind***\n\n" +
                    "1. Registro de Usuarios\n" +
                    "2. Catálogos\n" +
                    "3. Factura\n" +
                    "4. Salir\n\n" +
                    "Digite opción del menú en número: "));
                switch(opcion){
                 case 1:{
                    menuRegistro();
                    break;
                 }
                 case 2:{
                    menuCatalogo();
                    break;
                 }
                 case 3:{
                    menuFactura();
                    break;
                 }
                 case 4:{
                    JOptionPane.showMessageDialog(null,"Saliendo del Sistema");
                    System.exit(0);
                    break;
                 }  
                 default:{
                    JOptionPane.showMessageDialog(null,"¡Opción incorrecta!");
                 }
             }     
        }
    }
    
    public void menuRegistro(){
        while(true){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "***Registro de Usuarios***\n\n" +
                            "1. Agregar Usuario \n" +
                            "2. Consultar Usuario \n" +
                            "3. Eliminar Usuario \n" +
                            "4. Regresar \n\n" +
                            "Digite opción del menú en número: "));
            switch (opcion) {
                case 1:
                    menuAgregarUsuario();
                    break; 
                case 2:
                    r.mostrarUsuario();
                    break;
                case 3:
                    r.eliminarUsuario();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Regresando al menú principal.");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta, digite nuevamente");
                    break;
            }
        }
    }
    
    public void menuAgregarUsuario(){
        while(true){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "***Registro de Usuarios***\n\n" +
                    "1. Registrar nuevo alumno \n" +
                    "2. Registrar padre o encargado del alumno \n" +
                    "3. Asignar instructor \n" +
                    "4. Regresar \n\n" +
                    "Digite opción del menú en número: "));
            switch (opcion) {
                case 1:
                    r.registrarUsuario();
                    break;
                case 2:
                    r.registrarEncargado();;
                    break;
                case 3:
                    r.asignarInstructor();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Regresando");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta, digite nuevamente");
                    break;
            }
        }  
    }
    
    public void menuCatalogo(){
        while(true) {
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                "*** Módulo de Catálogos ***\n\n" +
                "1. Catálogo de Alumnos\n" +
                "2. Catálogo de Padres de Familia\n" +
                "3. Catálogo de Instructores\n" +
                "4. Catálogo de Categorías\n" +
                "5. Catálogo de Sedes\n" +
                "6. Volver al Menú Principal\n\n" +
                "Digite la opción deseada:"));

        switch(opcion) {
            case 1: {
                menuCatalogoAlumno();
                break;
            }
            case 2: {
                menuCatalogoEncargado();
                break;
            }
            case 3: {
                menuCatalogoInstructor();
                break;
            }
            case 4: {
                JOptionPane.showMessageDialog(null,"Opción no implementada");
                break;
            }
            case 5: {
                menuCatalogoSede();
                break;
            }
            case 6: {
                JOptionPane.showMessageDialog(null, "Regresando al menú principal.");
                return;
            }
            default: {
                JOptionPane.showMessageDialog(null,"¡Opción incorrecta, digite nuevamente!");
                break;
            }
        }
    }
}
    
    public void menuCatalogoAlumno(){
    while(true){
        opcion = Integer.parseInt(JOptionPane.showInputDialog(
            "*** Catálogo de Alumnos ***\n\n" +
            "1. Agregar Información adicional del Alumno\n" +
            "2. Editar información del Alumno\n" +
            "3. Inactivar Alumno\n" +
            "4. Asignar Sede al Alumno\n" +  
            "5. Volver al Menú de Catálogos\n\n" +
            "Digite la opción deseada:"));
        switch(opcion){
            case 1: {
                r.informacionAdicional();
                break;
            }
            case 2: {
                r.editarInformacion();
                break;
            }
            case 3: {
                r.inactivarAlumno();
                break;
            }
            case 4: {  
                r.asignarSedeAAlumno();
                break;
            }
            case 5: {
                JOptionPane.showMessageDialog(null, "Regresando");
                return; 
            }
            default: {
                JOptionPane.showMessageDialog(null,"¡Opción incorrecta, digite nuevamente!");
                break; 
            }
        }
    }
}

    
    public void menuCatalogoEncargado(){
        while(true){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "*** Catálogo de Padres de Familia ***\n\n" +
                "1. Asignar Id a padre\n" +
                "2. Agregar Información adicional\n" +
                "3. Editar informacion\n" +
                "4. Volver al Menú de Catálogos\n\n" +
                "Digite la opción deseada:"));
            switch(opcion){
                case 1:{
                   r.asignarIdPadre();
                   break;
                }
                case 2:{
                   r.agregarInformacionAdicional();
                   break;
                }
                case 3:{
                   r.editarInformacionPadre();
                   break;
                }
                case 4:{
                   JOptionPane.showMessageDialog(null, "Regresando");
                   return; 
                }
                default:{
                   JOptionPane.showMessageDialog(null,"¡Opción incorrecta, digite nuevamente!");
                   break; 
                }
            }
        }
    }
    
    public void menuCatalogoInstructor(){
        while(true){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "*** Catálogo de Instructores ***\n\n" +
                "1. Asignar instructor automáticamente\n" +
                "2. Mostrar lista de instructores\n" +
                "3. Editar instructor y horario\n" +
                "4. Volver al Menú de Catálogos\n\n" +
                "Digite la opción deseada:"));
            switch(opcion){
                case 1:{
                   r.asignarInstructorAutomatico();
                   break;
                }
                case 2:{
                   r.mostrarListaInstructores();
                   break;
                }
                case 3:{
                   r.editarInstructorYHorario();
                   break;
                }
                case 4:{
                   JOptionPane.showMessageDialog(null, "Regresando");
                   return; 
                }
                default:{
                   JOptionPane.showMessageDialog(null,"¡Opción incorrecta, digite nuevamente!");
                   break; 
                }
            }
        }  
    }
    
    public void menuCatalogoSede(){
        while(true){
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "*** Catálogo de Sedes ***\n\n" +
                "1. Agregar Sede\n" +
                "2. Mostrar Sedes Registradas\n" +
                "3. Editar Información de la Sede\n" +
                "4. Inactivar Sede\n" +
                "5. Volver al Menú de Catálogos\n\n" +
                "Digite la opción deseada:"));

            switch(opcion){
                case 1: {
                    r.agregarSede(); 
                    break;
                }
                case 2: {
                    r.mostrarSedes();  
                    break;
                }
                case 3: {
                    r.editarSede();  
                    break;
                }
                case 4: {
                    r.inactivarSede();  
                    break;
                }
                case 5: {
                    JOptionPane.showMessageDialog(null, "Regresando");
                    return;
                }
                default: {
                    JOptionPane.showMessageDialog(null, "¡Opción incorrecta, digite nuevamente!");
                    break;
                }
            }
        }
    }

    
    public void menuFactura(){    
    }
}
