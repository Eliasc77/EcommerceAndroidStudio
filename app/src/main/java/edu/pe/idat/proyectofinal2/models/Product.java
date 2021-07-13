package edu.pe.idat.proyectofinal2.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class Product {

    private int idproducto;
    private int idcategoria;
    private String nombreprod;
    private String foto;
    private String descripcion;
    private String estado;
    private double precio;
    private int stock;

    public Product() {

    }

    public Product(int idproducto, int idcategoria, String nombreprod, String foto, String descripcion, String estado, double precio, int stock) {
        this.idproducto = idproducto;
        this.idcategoria = idcategoria;
        this.nombreprod = nombreprod;
        this.foto = foto;
        this.descripcion = descripcion;
        this.estado = estado;
        this.precio = precio;
        this.stock = stock;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombreprod() {
        return nombreprod;
    }

    public void setNombreprod(String nombreprod) {
        this.nombreprod = nombreprod;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idproducto=" + idproducto +
                ", idcategoria=" + idcategoria +
                ", nombreprod='" + nombreprod + '\'' +
                ", foto='" + foto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getIdproducto() == product.getIdproducto() &&
                getIdcategoria() == product.getIdcategoria() &&
                Double.compare(product.getPrecio(), getPrecio()) == 0 &&
                getStock() == product.getStock() &&
                getNombreprod().equals(product.getNombreprod()) &&
                getFoto().equals(product.getFoto()) &&
                getDescripcion().equals(product.getDescripcion()) &&
                getEstado().equals(product.getEstado());
    }

    public static DiffUtil.ItemCallback<Product> itemCallback = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getIdproducto() == newItem.getIdproducto();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:productoImage")
    public static void loadImage(ImageView imageView, String foto){
        Glide.with(imageView)
                .load(foto)
                .fitCenter()
                .into(imageView);
    }
}
