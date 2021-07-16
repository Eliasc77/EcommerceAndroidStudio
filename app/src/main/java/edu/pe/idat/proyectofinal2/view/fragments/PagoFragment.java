package edu.pe.idat.proyectofinal2.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
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

    List<CarritoItem> carrito;

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

        productViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        pagoViewModel= new ViewModelProvider(getActivity()).get(PagoViewModel.class);
        pedidoViewModel = new ViewModelProvider(getActivity()).get(PedidoViewModel.class);
        detalleViewModel = new ViewModelProvider(getActivity()).get(DetalleViewModel.class);


        tarjeta = fragmentPagoBinding.tvNrotarjeta;
        fecha = fragmentPagoBinding.tvFecha;
        propietario = fragmentPagoBinding.tvNombrePropietario;
        cvv = fragmentPagoBinding.tvCvv;

        pedido = new Pedido();
        pedido.setIdcliente(SharedPreferencesManager.getSomeIntValue(Constantes.PREF_ID_CLIENTE));
        pedido.setIdtipopago(SharedPreferencesManager.getSomeIntValue(Constantes.PREF_ID_PAGO));
        pedido.setIddelivery(SharedPreferencesManager.getSomeIntValue(Constantes.PREF_ID_DELIVERY));

        /*
        pedido.setIdcliente(3);
        pedido.setIdtipopago(1);
        pedido.setIddelivery(1);
*/
        productViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                pedido.setTotalpagar(aDouble);
            }
        });

        productViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CarritoItem>>() {
            @Override
            public void onChanged(List<CarritoItem> carritoItems) {

                for (CarritoItem carritoItem : carritoItems) {
                    detalle = new Detalle();
                    detalle.setCantidad(carritoItem.getCantidad());
                    detalle.setIdproducto(carritoItem.getProduct().getIdproducto());
                    detalle.setPrecio(carritoItem.getCantidad() * carritoItem.getProduct().getPrecio());
                    detalle.setIdpedido(SharedPreferencesManager.getSomeIntValue(Constantes.PREF_ID_PEDIDO));
                    detalleViewModel.saveDetalle(getContext(), detalle);
                }
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

                pagoViewModel.savePago(getContext(), tipoPago);
                pedidoViewModel.savePedido(getContext(), pedido);

            }
        });




    }
}