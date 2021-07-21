package edu.pe.idat.proyectofinal2.view.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.SharedPreferencesManager;
import edu.pe.idat.proyectofinal2.databinding.FragmentPagoBinding;
import edu.pe.idat.proyectofinal2.models.CarritoItem;
import edu.pe.idat.proyectofinal2.models.Detalle;
import edu.pe.idat.proyectofinal2.models.Pedido;
import edu.pe.idat.proyectofinal2.models.TipoPago;
import edu.pe.idat.proyectofinal2.viewmodels.DetalleViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.EnvioViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.PagoViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.PedidoViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.ProductViewModel;


public class PagoFragment extends Fragment {
    FragmentPagoBinding fragmentPagoBinding;
    NavController navController;
    EditText tarjeta, fecha, propietario, cvv;
    TipoPago tipoPago;



    ProductViewModel productViewModel;
    //pago
    Pedido pedido;
    PagoViewModel pagoViewModel;
    PedidoViewModel pedidoViewModel;
    DetalleViewModel detalleViewModel;
    EnvioViewModel envioViewModel;

    private  int mes,ano,dia;

    Detalle detalle;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        productViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        pagoViewModel= new ViewModelProvider(getActivity()).get(PagoViewModel.class);
        //pedidoViewModel = new ViewModelProvider(getActivity()).get(PedidoViewModel.class);
        detalleViewModel = new ViewModelProvider(getActivity()).get(DetalleViewModel.class);
        envioViewModel = new ViewModelProvider(getActivity()).get(EnvioViewModel.class);


        tarjeta = fragmentPagoBinding.tvNrotarjeta;
        fecha = fragmentPagoBinding.tvFecha;
        propietario = fragmentPagoBinding.tvNombrePropietario;
        cvv = fragmentPagoBinding.tvCvv;

        fragmentPagoBinding.tvCvv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 3){
                    Toast.makeText(getContext(), "la cantidad maxima es 3", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        fragmentPagoBinding.btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipoPago = new TipoPago();
                tipoPago.setDescripcion(tarjeta.getText().toString());
                tipoPago.setFecha(fecha.getText().toString());
                tipoPago.setPropietario(propietario.getText().toString());
                tipoPago.setCvv(cvv.getText().toString());



                if(!validar()){
                    return;
                }else{
                    pagoViewModel.savePago(getContext(), tipoPago);
                    //pedidoViewModel.savePedido(getContext(), pedido);
                    //saveDetail();

                    navController.navigate(R.id.action_pagoFragment_to_confirmarFragment);
                }



            }
        });

        fragmentPagoBinding.tvFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    final Calendar c= Calendar.getInstance();
                    dia=c.get(Calendar.DAY_OF_MONTH);
                    mes=c.get(Calendar.MONTH);
                    ano=c.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            fragmentPagoBinding.tvFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                        }
                        // AQUÍ ESTA LA CLAVE:

                    }, ano, mes, dia);
                    datePickerDialog.show();

                }

        });

    }

    public boolean validar(){
        boolean response = true;
        String card = tarjeta.getText().toString();
        String date = fecha.getText().toString();
        String property= propietario.getText().toString();
        String idcvv = cvv.getText().toString();

        if(card.isEmpty()){
            tarjeta.setError("Ingrese su Tarjeta!");
            response=false;
        }if(date.isEmpty()){
            fecha.setError("Ingrese Fecha!");
            response=false;
        }if(property.isEmpty()){
            propietario.setError("Ingrese su Nombre de Propietario!");
            response=false;
        }if(idcvv.isEmpty()){
            cvv.setError("Ingrese su cvv");
            response=false;
        }
        return response;
    }

}