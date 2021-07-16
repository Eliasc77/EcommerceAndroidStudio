package edu.pe.idat.proyectofinal2.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import edu.pe.idat.proyectofinal2.models.Cliente;
import edu.pe.idat.proyectofinal2.repositories.ClientRepo;

public class ClientViewModel extends ViewModel {
    private ClientRepo clientRepo;

    // NO FUNCA =<
    public void saveCliente(Context context, Cliente cliente){
        clientRepo = new ClientRepo();
        clientRepo.saveCliente(context, cliente);
    }
}
