package edu.pe.idat.proyectofinal2.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.SharedPreferencesManager;
import edu.pe.idat.proyectofinal2.databinding.FragmentThanksForOrderBinding;
import edu.pe.idat.proyectofinal2.models.CarritoItem;
import edu.pe.idat.proyectofinal2.models.Detalle;
import edu.pe.idat.proyectofinal2.view.MainActivity;
import edu.pe.idat.proyectofinal2.view.MenuActivity;
import edu.pe.idat.proyectofinal2.viewmodels.DetalleViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.ProductViewModel;


public class ThanksForOrderFragment extends Fragment {

    NavController navController;
    ProductViewModel productViewModel;
    DetalleViewModel detalleViewModel;
    Detalle detalle;


    FragmentThanksForOrderBinding fragmentThanksForOrderBinding;
    public ThanksForOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentThanksForOrderBinding = FragmentThanksForOrderBinding.inflate(inflater, container,false);
        return fragmentThanksForOrderBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        detalleViewModel = new ViewModelProvider(getActivity()).get(DetalleViewModel.class);

        fragmentThanksForOrderBinding.btnthanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                productViewModel.resetCart();
                redirect();


            }
        });
    }

    private void redirect(){

        startActivity(new Intent(getContext(), MenuActivity.class));
    }

}