package edu.pe.idat.proyectofinal2.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import edu.pe.idat.proyectofinal2.models.Pedido;
import edu.pe.idat.proyectofinal2.repositories.PedidoRepo;

public class PedidoViewModel extends ViewModel {

    private PedidoRepo pedidoRepo = new PedidoRepo();

    public void savePedido(Context context, Pedido pedido){
        pedidoRepo.savePedido(context,pedido);
    }
}
