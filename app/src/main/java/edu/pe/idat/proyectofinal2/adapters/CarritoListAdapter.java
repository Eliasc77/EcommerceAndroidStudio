package edu.pe.idat.proyectofinal2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import edu.pe.idat.proyectofinal2.databinding.CartItemBinding;
import edu.pe.idat.proyectofinal2.models.CarritoItem;

public class CarritoListAdapter extends ListAdapter<CarritoItem, CarritoListAdapter.ViewHolder> {
    private CarritoInterface carritoInterface;
    public CarritoListAdapter(CarritoInterface carritoInterface) {
        super(CarritoItem.itemCallback);
        this.carritoInterface = carritoInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CartItemBinding cartItemBinding = CartItemBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(cartItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //enviamos la lista de carritos  capturado al databinding de cart_item
        holder.cartItemBinding.setCarritoItem(getItem(position));
        holder.cartItemBinding.executePendingBindings();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CartItemBinding cartItemBinding;
        public ViewHolder(CartItemBinding cartItemBinding) {
            super(cartItemBinding.getRoot());
            this.cartItemBinding = cartItemBinding;

            //evento click para eliminar
            cartItemBinding.btnborrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    carritoInterface.deleteItem(getItem(getAdapterPosition()));
                }
            });
            cartItemBinding.ivplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity =getItem(getAdapterPosition()).getCantidad();
                    if(quantity<10){
                        quantity++;
                        carritoInterface.changeQuantity(getItem(getAdapterPosition()), quantity);
                    }


                }
            });
            cartItemBinding.ivminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity =getItem(getAdapterPosition()).getCantidad();
                    if(quantity>1){
                        quantity--;
                        carritoInterface.changeQuantity(getItem(getAdapterPosition()), quantity);
                    }

                }
            });

        }
    }

    public interface CarritoInterface{
        void deleteItem(CarritoItem carritoItem);
        void changeQuantity(CarritoItem carritoItem, int cantidad);
    }
}
