package edu.pe.idat.proyectofinal2.models;

public class TipoPago {

    private int idtipopago;
    private String descripcion;
    private String fecha;
    private String propietario;
    private String cvv;

    public TipoPago() {
    }

    public TipoPago(int idtipopago, String descripcion, String fecha, String propietario, String cvv) {
        this.idtipopago = idtipopago;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.propietario = propietario;
        this.cvv = cvv;
    }

    public int getIdtipopago() {
        return idtipopago;
    }

    public void setIdtipopago(int idtipopago) {
        this.idtipopago = idtipopago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}

