package edu.pe.idat.proyectofinal2.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.databinding.FragmentPagoBinding;


public class PagoFragment extends Fragment {
    FragmentPagoBinding fragmentPagoBinding;
    NavController navController;

    public PagoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPagoBinding = FragmentPagoBinding.inflate(inflater, container, false);
        return fragmentPagoBinding.getRoot();
    }
}