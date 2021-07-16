package edu.pe.idat.proyectofinal2.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.VolleySingleton;
import edu.pe.idat.proyectofinal2.databinding.FragmentClienteBinding;
import edu.pe.idat.proyectofinal2.models.Cliente;
import edu.pe.idat.proyectofinal2.viewmodels.ClientViewModel;


public class ClienteFragment extends Fragment {

    FragmentClienteBinding fragmentClienteBinding;
    EditText nombre, apellido, dni, celular;

    NavController navController;

    ClientViewModel clientViewModel;

    Cliente cliente;

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

        clientViewModel = new ViewModelProvider(getActivity()).get(ClientViewModel.class);

        fragmentClienteBinding.btnRegistrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliente = new Cliente();
                cliente.setNombre(nombre.getText().toString());
                cliente.setApellido(apellido.getText().toString());
                cliente.setDni(dni.getText().toString());
                cliente.setCelular(celular.getText().toString());


                if(!validar()){
                    return;
                }else{
                    //save(cliente);
                    clientViewModel.saveCliente(getActivity(),cliente);
                    navController.navigate(R.id.action_clienteFragment_to_envioFragment);
                }
                //clientViewModel.saveCliente(getActivity(), cliente);

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