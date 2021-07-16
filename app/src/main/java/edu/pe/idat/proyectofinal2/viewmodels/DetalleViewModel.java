package edu.pe.idat.proyectofinal2.viewmodels;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import edu.pe.idat.proyectofinal2.models.Detalle;
import edu.pe.idat.proyectofinal2.repositories.DetalleRepo;

public class DetalleViewModel extends ViewModel {

    private DetalleRepo detalleRepo = new DetalleRepo();

    public void saveDetalle (Context context, Detalle detalle){
        detalleRepo.saveDetalle(context, detalle);
    }
}
