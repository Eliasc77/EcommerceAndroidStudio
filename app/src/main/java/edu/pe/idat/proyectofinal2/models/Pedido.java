package edu.pe.idat.proyectofinal2.models;

public class Pedido {

    private int idpedido;
    private int idcliente;
    private double totalpagar;
    private int iddelivery;
    private int idtipopago;

    public Pedido() {
    }

    public Pedido(int idpedido, int idcliente, double totalpagar, int iddelivery, int idtipopago) {
        this.idpedido = idpedido;
        this.idcliente = idcliente;
        this.totalpagar = totalpagar;
        this.iddelivery = iddelivery;
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

    public int getIddelivery() {
        return iddelivery;
    }

    public void setIddelivery(int iddelivery) {
        this.iddelivery = iddelivery;
    }

    public int getIdtipopago() {
        return idtipopago;
    }

    public void setIdtipopago(int idtipopago) {
        this.idtipopago = idtipopago;
    }
}
