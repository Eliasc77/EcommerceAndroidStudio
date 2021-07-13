package edu.pe.idat.proyectofinal2.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.pe.idat.proyectofinal2.databinding.CategoryItemBinding;
import edu.pe.idat.proyectofinal2.databinding.FragmentShopBinding;
import edu.pe.idat.proyectofinal2.models.Categoria;
import edu.pe.idat.proyectofinal2.view.MainActivity;
import edu.pe.idat.proyectofinal2.view.fragments.MenuFragment;
import edu.pe.idat.proyectofinal2.view.fragments.ShopFragment;

public class CategoriaAdapter extends ListAdapter<Categoria,CategoriaAdapter.ViewHolder> {


    CategoriaInterface categoriaInterface;
    public CategoriaAdapter(CategoriaInterface categoriaInterface) {
        super(Categoria.itemCallback);
        this.categoriaInterface = categoriaInterface;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CategoryItemBinding rbinding =CategoryItemBinding.inflate(layoutInflater,parent,false);
        rbinding.setCategoriaInterface(categoriaInterface);
        return new ViewHolder(rbinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categoria categoria =getItem(position);
        holder.categoryItemBinding.setCategoria(categoria);
        /*
        holder.categoryItemBinding.imgviewcategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle miBundle = new Bundle();
                miBundle.putString("id", categoria.getIdcategoria()+"");
                miBundle.putString("descripcion", categoria.getDescripcion());
                Intent intent =new Intent(context, MainActivity.class.asSubclass(ShopFragment.class));
                intent.putExtras(miBundle);
                context.startActivity(intent);
            }
        });
        */


    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        CategoryItemBinding categoryItemBinding;
        public ViewHolder(CategoryItemBinding binding) {
            super(binding.getRoot());
            this.categoryItemBinding = binding;
        }
    }

    public interface CategoriaInterface{
        void onItemClick(Categoria categoria);
    }
}
