package sistemaacademia;
import java.time.LocalDate;
public class Facturas {
    private int idFactura;
    private double monto;
    private LocalDate fechaEmision;
    private boolean estadoPago;
    private Alumnos alumno;
    
    public Facturas(int idFactura, double monto, LocalDate fechaEmision, boolean estadoPago, Alumnos alumno) {
        this.idFactura = idFactura;
        this.alumno = alumno;
        this.monto = monto;
        this.fechaEmision = fechaEmision;
        this.estadoPago = estadoPago; 
        this.alumno = alumno;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }
    
    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }
    
}
