package edu.pe.idat.proyectofinal2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import edu.pe.idat.proyectofinal2.databinding.ProductItemBinding;
import edu.pe.idat.proyectofinal2.models.Product;

public class ProductListAdapter extends ListAdapter<Product,ProductListAdapter.ViewHolder> {
   ShopInterface shopInterface;

    public ProductListAdapter(ShopInterface shopInterface) {

        super(Product.itemCallback);
        this.shopInterface = shopInterface;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProductItemBinding pbinging =  ProductItemBinding.inflate(layoutInflater,parent,false);
        pbinging.setShopInterface(shopInterface);
        return new ViewHolder(pbinging);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product obj = getItem(position);
        holder.productItemBinding.setProduct(obj);


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProductItemBinding productItemBinding;
        public ViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            this.productItemBinding = binding;
        }
    }

    public interface ShopInterface{
        void addItem(Product product);
        void onItemClick(Product product);
    }
}
