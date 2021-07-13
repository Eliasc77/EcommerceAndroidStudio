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
import android.widget.EditText;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.databinding.FragmentClienteBinding;


public class ClienteFragment extends Fragment {

    FragmentClienteBinding fragmentClienteBinding;
    EditText nombre, apellido, dni, celular;

    NavController navController;

    public ClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentClienteBinding = FragmentClienteBinding.inflate(inflater, container,false);
        return fragmentClienteBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        nombre = fragmentClienteBinding.tvNombre;
        apellido = fragmentClienteBinding.tvApellido;
        dni = fragmentClienteBinding.tvDni;
        celular = fragmentClienteBinding.tvCelular;

        fragmentClienteBinding.btnRegistrarCliente.setOnClickListener(v->{
            if(!validar()){
                return;
            }else{
                navController.navigate(R.id.action_clienteFragment_to_envioFragment);
            }
        });

    }


    public boolean validar(){
        boolean response = true;
        String name = nombre.getText().toString();
        String surname = apellido.getText().toString();
        String idcar= dni.getText().toString();
        String phone = celular.getText().toString();

        if(name.isEmpty()){
            nombre.setError("Ingrese su Nombre!");
            response=false;
        }if(surname.isEmpty()){
            apellido.setError("Ingrese su Apellido!");
            response=false;
        }if(idcar.isEmpty()){
            dni.setError("Ingrese su DNI!");
            response=false;
        }if(phone.isEmpty()){
            celular.setError("Ingrese su celular");
            response=false;
        }
        return response;
    }
}