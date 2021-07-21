package edu.pe.idat.proyectofinal2.models;

public class Pedido {

    private int idpedido;
    private int idcliente;
    private double totalpagar;
    private String distrito;
    private String direccion;
    private String referencia;
    private boolean estado;
    private int idtipopago;

    public Pedido() {
    }

    public Pedido(int idpedido, int idcliente, double totalpagar, String distrito,
                  String direccion, String referencia, boolean estado, int idtipopago) {
        this.idpedido = idpedido;
        this.idcliente = idcliente;
        this.totalpagar = totalpagar;
        this.distrito = distrito;
        this.direccion = direccion;
        this.referencia = referencia;
        this.estado = estado;
        this.idtipopago = idtipopago;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public double getTotalpagar() {
        return totalpagar;
    }

    public void setTotalpagar(double totalpagar) {
        this.totalpagar = totalpagar;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdtipopago() {
        return idtipopago;
    }

    public void setIdtipopago(int idtipopago) {
        this.idtipopago = idtipopago;
    }
}
