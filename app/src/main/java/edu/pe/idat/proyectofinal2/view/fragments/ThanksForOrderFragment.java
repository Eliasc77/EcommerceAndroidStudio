package edu.pe.idat.proyectofinal2.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.databinding.FragmentThanksForOrderBinding;


public class ThanksForOrderFragment extends Fragment {

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
    }
}