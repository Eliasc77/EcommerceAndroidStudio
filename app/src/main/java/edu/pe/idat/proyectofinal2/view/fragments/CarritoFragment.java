package edu.pe.idat.proyectofinal2.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.adapters.CarritoListAdapter;
import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.SharedPreferencesManager;
import edu.pe.idat.proyectofinal2.databinding.FragmentCarritoBinding;
import edu.pe.idat.proyectofinal2.models.CarritoItem;
import edu.pe.idat.proyectofinal2.viewmodels.ProductViewModel;


public class CarritoFragment extends Fragment implements CarritoListAdapter.CarritoInterface {

    private static final String TAG = "CarritoFragment";
    FragmentCarritoBinding fragmentCarritoBinding;
    ProductViewModel productViewModel;

    NavController navController;


    public CarritoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentCarritoBinding = FragmentCarritoBinding.inflate(inflater, container , false);
        return fragmentCarritoBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //view
        navController = Navigation.findNavController(view);

        //inicializamos nuestro adaptador
        CarritoListAdapter carritoListAdapter = new CarritoListAdapter(this);
        fragmentCarritoBinding.recCarr.setAdapter(carritoListAdapter);

        //Inicializamos nuestro viewmodel
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        //llamamos al metodo q nos retornara la lista de carritos
        productViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CarritoItem>>() {
            @Override
            public void onChanged(List<CarritoItem> carritoItems) {
                //aca enviaremos la lista q obtenemos de carros al adaptor
                carritoListAdapter.submitList(carritoItems);
                fragmentCarritoBinding.buyNowCart.setEnabled(carritoItems.size()>0);
            }
        });

        //llamos añl metoto q nos retornara el totla precio
        productViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCarritoBinding.igvPrice.setText("$ "+ Math.round(aDouble * 0.18));
                fragmentCarritoBinding.totalPriceCart.setText("$ "+aDouble.toString());

            }
        });

        fragmentCarritoBinding.buyNowCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_carritoFragment_to_clienteFragment);
            }
        });
    }

    @Override
    public void deleteItem(CarritoItem carritoItem) {
        Log.d(TAG, "deleteItem: " +carritoItem.getProduct().getNombreprod());
        productViewModel.removeItemFromCart(carritoItem);
    }

    @Override
    public void changeQuantity(CarritoItem carritoItem, int cantidad) {
        productViewModel.changeQuantity(carritoItem,cantidad);

    }
}