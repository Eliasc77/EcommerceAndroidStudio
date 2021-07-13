package edu.pe.idat.proyectofinal2.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.databinding.FragmentMetodoPagoBinding;


public class MetodoPagoFragment extends Fragment {

    FragmentMetodoPagoBinding fragmentMetodoPagoBinding;
    NavController navController;

    public MetodoPagoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentMetodoPagoBinding =FragmentMetodoPagoBinding.inflate(inflater, container, false);
        return fragmentMetodoPagoBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        fragmentMetodoPagoBinding.btnSiguiente.setOnClickListener(v->{
            navController.navigate(R.id.action_metodoPagoFragment_to_pagoFragment);
        });


    }
}