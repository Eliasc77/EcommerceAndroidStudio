package edu.pe.idat.proyectofinal2.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import edu.pe.idat.proyectofinal2.models.Envio;

public class EnvioViewModel extends ViewModel {

    MutableLiveData<Envio> mutableEnvio = new MutableLiveData<>();

    public void setEnvio(Envio envio){
        mutableEnvio.setValue(envio);
    }

    public LiveData<Envio> getEnvio(){
        return mutableEnvio;
    }
}
