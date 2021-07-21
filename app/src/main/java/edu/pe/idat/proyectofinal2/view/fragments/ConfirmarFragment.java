package edu.pe.idat.proyectofinal2.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import edu.pe.idat.proyectofinal2.R;
import edu.pe.idat.proyectofinal2.adapters.ResumeAdapter;
import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.SharedPreferencesManager;
import edu.pe.idat.proyectofinal2.databinding.FragmentConfirmarBinding;
import edu.pe.idat.proyectofinal2.databinding.FragmentThanksForOrderBinding;
import edu.pe.idat.proyectofinal2.models.CarritoItem;
import edu.pe.idat.proyectofinal2.models.Detalle;
import edu.pe.idat.proyectofinal2.models.Envio;
import edu.pe.idat.proyectofinal2.models.Pedido;
import edu.pe.idat.proyectofinal2.viewmodels.DetalleViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.EnvioViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.PagoViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.PedidoViewModel;
import edu.pe.idat.proyectofinal2.viewmodels.ProductViewModel;


public class ConfirmarFragment extends Fragment {

    NavController navController;
    FragmentConfirmarBinding fragmentConfirmarBinding;

    DetalleViewModel detalleViewModel;
    ProductViewModel productViewModel;
    EnvioViewModel envioViewModel;
    PedidoViewModel pedidoViewModel;
    ResumeAdapter resumeAdapter;
    Pedido pedido;
    double pago;

    Detalle detalle;

    public ConfirmarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentConfirmarBinding = FragmentConfirmarBinding.inflate(inflater, container, false);
        return fragmentConfirmarBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        resumeAdapter = new ResumeAdapter();
        fragmentConfirmarBinding.recResume.setAdapter(resumeAdapter);

        navController = Navigation.findNavController(view);
        envioViewModel = new ViewModelProvider(getActivity()).get(EnvioViewModel.class);
        pedidoViewModel = new ViewModelProvider(getActivity()).get(PedidoViewModel.class);
        productViewModel = new ViewModelProvider(getActivity()).get(ProductViewModel.class);
        detalleViewModel = new ViewModelProvider(getActivity()).get(DetalleViewModel.class);

        pedido = new Pedido();
        pedido.setIdcliente(SharedPreferencesManager.getSomeIntValue(Constantes.PREF_ID_CLIENTE));
        pedido.setIdtipopago(SharedPreferencesManager.getSomeIntValue(Constantes.PREF_ID_PAGO));



        productViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {

                pedido.setTotalpagar(aDouble);
                fragmentConfirmarBinding.tvTotalP.setText("$/. " + aDouble);
            }
        });

        envioViewModel.getEnvio().observe(getViewLifecycleOwner(), new Observer<Envio>() {
            @Override
            public void onChanged(Envio envio) {
                fragmentConfirmarBinding.tvdist.setText(envio.getDistrito());
                fragmentConfirmarBinding.tvdirec.setText(envio.getDireccion());
                fragmentConfirmarBinding.tvreferencia.setText(envio.getReferencia());
                pedido.setDireccion(envio.getDireccion());
                pedido.setDistrito(envio.getDistrito());
                pedido.setReferencia(envio.getReferencia());
                if(pedido != null){
                    pedidoViewModel.savePedido(getContext(), pedido);
                }

            }
        });

        productViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CarritoItem>>() {
            @Override
            public void onChanged(List<CarritoItem> carritoItems) {
                for (CarritoItem carritoItem : carritoItems) {

                    resumeAdapter.submitList(carritoItems);
                }
            }
        });

        fragmentConfirmarBinding.btnconfirmarpago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    saveDetail();
                navController.navigate(R.id.action_confirmarFragment_to_thanksForOrderFragment);

            }
        });
    }

    public void saveDetail(){
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
    }

}