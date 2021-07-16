package edu.pe.idat.proyectofinal2.models;

public class Detalle {

    private int idetalle;
    private int idpedido;
    private int idproducto;
    private int cantidad;
    private double precio;

    public Detalle() {
    }

    public Detalle(int idetalle, int idpedido, int idproducto, int cantidad, double precio) {
        this.idetalle = idetalle;
        this.idpedido = idpedido;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdetalle() {
        return idetalle;
    }

    public void setIdetalle(int idetalle) {
        this.idetalle = idetalle;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

