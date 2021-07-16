package edu.pe.idat.proyectofinal2.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import edu.pe.idat.proyectofinal2.models.TipoPago;
import edu.pe.idat.proyectofinal2.repositories.TipoPagoRepo;

public class PagoViewModel extends ViewModel {

    private TipoPagoRepo tipoPagoRepo = new TipoPagoRepo();

    public void savePago(Context context, TipoPago tipoPago){
        tipoPagoRepo.savePago(context, tipoPago);
    }

}
