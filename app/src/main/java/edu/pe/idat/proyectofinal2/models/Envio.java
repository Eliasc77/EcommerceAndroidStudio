package edu.pe.idat.proyectofinal2.models;

public class Envio {

    private int iddelivery;
    private String distrito;
    private String direccion;
    private String referencia;

    public Envio() {
    }

    public Envio(int iddelivery, String distrito, String direccion, String referencia) {
        this.iddelivery = iddelivery;
        this.distrito = distrito;
        this.direccion = direccion;
        this.referencia = referencia;
    }

    public int getIddelivery() {
        return iddelivery;
    }

    public void setIddelivery(int iddelivery) {
        this.iddelivery = iddelivery;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
