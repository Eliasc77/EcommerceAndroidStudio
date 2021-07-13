package edu.pe.idat.proyectofinal2.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class CarritoItem {
    private Product product;
    private int cantidad;

    public CarritoItem(Product product, int cantidad) {
        this.product = product;
        this.cantidad = cantidad;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "CarritoItem{" +
                "product=" + product +
                ", cantidad=" + cantidad +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarritoItem that = (CarritoItem) o;
        return getCantidad() == that.getCantidad() &&
                getProduct().equals(that.getProduct());
    }


    //diff
    public static DiffUtil.ItemCallback<CarritoItem> itemCallback= new DiffUtil.ItemCallback<CarritoItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull CarritoItem oldItem, @NonNull CarritoItem newItem) {
            //return oldItem.getProduct().equals(newItem.getProduct());
            //
            return  oldItem.getCantidad() == newItem.getCantidad();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CarritoItem oldItem, @NonNull CarritoItem newItem) {
            return oldItem.equals(newItem);
        }
    };


}
