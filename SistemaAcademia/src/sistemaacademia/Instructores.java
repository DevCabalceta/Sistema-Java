package sistemaacademia;
import javax.swing.JOptionPane;
public class Instructores {
    
    private byte edadUsuario;
    String nombreInstructor;
    String horario;
    
    public Instructores(Alumnos alumno) {
        this.edadUsuario = alumno.getEdad();
        this.nombreInstructor = "";
        this.horario = "";
    }

    public void asignarInstructor() {
        if ((edadUsuario >= 5) && (edadUsuario <= 6)) {
            nombreInstructor = "Gerardo Gonzalez";
            horario = "Mañana";
            JOptionPane.showMessageDialog(null, "Edad: " + edadUsuario + "\nInstructor: " + nombreInstructor+ "\nHorario: " + horario);
        } else if (edadUsuario >= 7 && edadUsuario <= 8) {
            nombreInstructor = "Francisco Rios";
            horario = "Tarde";
            JOptionPane.showMessageDialog(null, "Edad: " + edadUsuario + "\nInstructor: " + nombreInstructor+ "\nHorario: " + horario);
        } else if (edadUsuario >= 9 && edadUsuario <= 10) {
            nombreInstructor = "Monica Diaz";
            horario = "Mañana";
            JOptionPane.showMessageDialog(null, "Edad: " + edadUsuario + "\nInstructor: " + nombreInstructor+ "\nHorario: " + horario);
        } else {
            JOptionPane.showMessageDialog(null, "Datos ingresados no válidos.");
        }
    }
}
