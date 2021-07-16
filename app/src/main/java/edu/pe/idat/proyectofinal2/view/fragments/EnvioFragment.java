package edu.pe.idat.proyectofinal2.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import edu.pe.idat.proyectofinal2.models.Envio;
import edu.pe.idat.proyectofinal2.viewmodels.EnvioViewModel;


public class EnvioFragment extends Fragment {

    FragmentEnvioBinding fragmentEnvioBinding;
    EditText direccion, distrito,referencia;

    EnvioViewModel envioViewModel;
    NavController navController;

    Envio envio;

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

        navController = Navigation.findNavController(view);

        direccion = fragmentEnvioBinding.direccion;
        distrito = fragmentEnvioBinding.autoCompleteTextView;
        referencia = fragmentEnvioBinding.referencia;

        envioViewModel = new ViewModelProvider(getActivity()).get(EnvioViewModel.class);

        fragmentEnvioBinding.btnRegistrarEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envio = new Envio();
                envio.setDistrito(distrito.getText().toString());
                envio.setDireccion(direccion.getText().toString());
                envio.setReferencia(referencia.getText().toString());

                if(!validar()){
                    return;
                }else{
                    envioViewModel.saveEnvio(getActivity(), envio);
                    navController.navigate(R.id.action_envioFragment_to_metodoPagoFragment);
                }
            }
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