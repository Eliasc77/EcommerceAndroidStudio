package edu.pe.idat.proyectofinal2.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.adapters.ProductListAdapter;
import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.SharedPreferencesManager;
import edu.pe.idat.proyectofinal2.databinding.FragmentShopBinding;
import edu.pe.idat.proyectofinal2.models.Categoria;
import edu.pe.idat.proyectofinal2.models.Product;
import edu.pe.idat.proyectofinal2.viewmodels.CategoriaViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.ProductViewModel;


public class ShopFragment extends Fragment implements ProductListAdapter.ShopInterface{

    private static final String TAG = "ShopFragment";
    FragmentShopBinding fragmentShopBinding;
    private ProductViewModel productViewModel;
    private NavController navController;
    public int id;

    ProductListAdapter productListAdapter;
    public ShopFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentShopBinding =FragmentShopBinding.inflate(inflater,container,false);

        return fragmentShopBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productListAdapter = new ProductListAdapter(this);
        fragmentShopBinding.shopRecycler.setAdapter(productListAdapter);

        id = SharedPreferencesManager.getSomeIntValue(Constantes.PREF_ID_CATEGORIA);


        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        productViewModel.getProducts(getActivity(), id).observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                productListAdapter.submitList(products);
            }
        });

        navController = Navigation.findNavController(view);
    }

    @Override
    public void addItem(Product product) {
        //Log.d(TAG, "addItem: " + product.toString());
        //llamamos al metodo  additem y le pasamos el bojeto prodyucto
        boolean isAdded = productViewModel.addItemToCart(product);
        //Log.d(TAG, "addItem: "+product.getNombreprod()+ isAdded);
        if(isAdded){
            Snackbar.make(requireView(), product.getNombreprod() + " Agregado al Carrito." , Snackbar.LENGTH_LONG)
                    .setAction("PAGAR AHORA!", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            navController.navigate(R.id.action_shopFragment_to_carritoFragment);
                        }
                    }).show();
        }else{
            Snackbar.make(requireView(), "Maximo 10 item por Producto!.", Snackbar.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemClick(Product product) {
        //Log.d(TAG, "onItemClick: "+ product.toString());
        productViewModel.setProduct(product);
        navController.navigate(R.id.action_shopFragment_to_detalleFragment);
    }
}