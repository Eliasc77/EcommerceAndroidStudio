package edu.pe.idat.proyectofinal2.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.pe.idat.proyectofinal2.models.Categoria;
import edu.pe.idat.proyectofinal2.repositories.CategoryRepo;

public class CategoriaViewModel extends ViewModel {
    public int ide;
    CategoryRepo categoryRepo = new CategoryRepo();
    MutableLiveData<Categoria> mutableCategoria = new MutableLiveData<>();


    public LiveData<List<Categoria>> getCategorias(Context context){
        return categoryRepo.getCategorias(context);
    }

    public void setCategoria( Categoria caterigoria){
        mutableCategoria.setValue(caterigoria);
    }

    public LiveData<Categoria> getCategoria(){
        return  mutableCategoria;
    }






}
