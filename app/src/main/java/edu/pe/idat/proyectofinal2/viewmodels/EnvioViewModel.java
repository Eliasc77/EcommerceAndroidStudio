package edu.pe.idat.proyectofinal2.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import edu.pe.idat.proyectofinal2.models.Envio;
import edu.pe.idat.proyectofinal2.repositories.EnvioRepo;

public class EnvioViewModel extends ViewModel {
    private EnvioRepo envioRepo;

    public void saveEnvio(Context context, Envio envio){
        envioRepo = new EnvioRepo();
        envioRepo.saveEnvio(context, envio);
    }
}
