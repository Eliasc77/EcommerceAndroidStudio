package edu.pe.idat.proyectofinal2.models;

public class Envio {


    private String distrito;
    private String direccion;
    private String referencia;

    public Envio() {
    }

    public Envio( String distrito, String direccion, String referencia) {

        this.distrito = distrito;
        this.direccion = direccion;
        this.referencia = referencia;
    }

    @Override
    public String toString() {
        return "Envio{" +
                "distrito='" + distrito + '\'' +
                ", direccion='" + direccion + '\'' +
                ", referencia='" + referencia + '\'' +
                '}';
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
