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
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.databinding.FragmentEnvioBinding;


public class EnvioFragment extends Fragment {

    FragmentEnvioBinding fragmentEnvioBinding;
    EditText direccion;
    NavController navController;

    public EnvioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        final String[] distrito = getResources().getStringArray(R.array.distritos);

        fragmentEnvioBinding.autoCompleteTextView.setAdapter(new ArrayAdapter(requireContext(), R.layout.dropdown_item, distrito));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentEnvioBinding = FragmentEnvioBinding.inflate(inflater, container , false);


        return fragmentEnvioBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        direccion = fragmentEnvioBinding.direccion;
        navController = Navigation.findNavController(view);

        fragmentEnvioBinding.btnRegistrarEnvio.setOnClickListener( v->{
            navController.navigate(R.id.action_envioFragment_to_metodoPagoFragment);
        });

    }


    public boolean validar(){
        boolean response = true;
        String direc = direccion.getText().toString();

        if(direc.isEmpty()){
            direccion.setError("Direccion Obligatorio!");
            response=false;
        }
        return response;
    }
}