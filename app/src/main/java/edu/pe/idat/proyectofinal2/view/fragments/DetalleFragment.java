package edu.pe.idat.proyectofinal2.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.adapters.CarritoListAdapter;
import edu.pe.idat.proyectofinal2.databinding.FragmentDetalleBinding;
import edu.pe.idat.proyectofinal2.models.CarritoItem;
import edu.pe.idat.proyectofinal2.viewmodels.PopularProductViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.ProductViewModel;


public class DetalleFragment extends Fragment {
    FragmentDetalleBinding fragmentDetalleBinding;
    ProductViewModel productViewModel;
    PopularProductViewModel popularProductViewModel;

    public DetalleFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentDetalleBinding = FragmentDetalleBinding.inflate(inflater,container,false);
        return fragmentDetalleBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productViewModel = new ViewModelProvider(requireActivity()).get(ProductViewModel.class);
        //popularProductViewModel = new ViewModelProvider(requireActivity()).get(PopularProductViewModel.class);


            fragmentDetalleBinding.setProductViewModel(productViewModel);

    }


}