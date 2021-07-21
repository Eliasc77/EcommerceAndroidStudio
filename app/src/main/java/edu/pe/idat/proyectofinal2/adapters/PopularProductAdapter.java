package edu.pe.idat.proyectofinal2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import edu.pe.idat.proyectofinal2.databinding.PopularItemBinding;
import edu.pe.idat.proyectofinal2.models.Product;

public class PopularProductAdapter extends ListAdapter<Product,PopularProductAdapter.ViewHolder> {

    public PopularProductAdapter() {
        super(Product.itemCallback);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PopularItemBinding pItemBinding = PopularItemBinding.inflate(layoutInflater, parent,false);
        return new ViewHolder(pItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = getItem(position);
        holder.popularItemBinding.setPopular(product);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PopularItemBinding popularItemBinding;
        public ViewHolder(PopularItemBinding binding) {
            super(binding.getRoot());
            this.popularItemBinding = binding;
        }
    }


}
