package edu.pe.idat.proyectofinal2.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class Categoria {

    private int idcategoria;
    private String descripcion;
    private String img;

    public Categoria() {
    }

    public Categoria(int idcategoria, String descripcion, String img) {
        this.idcategoria = idcategoria;
        this.descripcion = descripcion;
        this.img = img;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idcategoria=" + idcategoria +
                ", descripcion='" + descripcion + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return getIdcategoria() == categoria.getIdcategoria() &&
                getDescripcion().equals(categoria.getDescripcion()) &&
                getImg().equals(categoria.getImg());
    }

    public static DiffUtil.ItemCallback<Categoria> itemCallback = new DiffUtil.ItemCallback<Categoria>() {
        @Override
        public boolean areItemsTheSame(@NonNull Categoria oldItem, @NonNull Categoria newItem) {
            return oldItem.getIdcategoria()==(newItem.getIdcategoria());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Categoria oldItem, @NonNull Categoria newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:categoryImage")
    public static void loadImage(ImageView imageView, String img){
        Glide.with(imageView)
                .load(img)
                .fitCenter()
                .into(imageView);
    }

}
