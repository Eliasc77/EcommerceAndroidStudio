package edu.pe.idat.proyectofinal2.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.pe.idat.proyectofinal2.models.Categoria;
import edu.pe.idat.proyectofinal2.models.Product;
import edu.pe.idat.proyectofinal2.repositories.PopularProductRepo;


public class PopularProductViewModel extends ViewModel {
    PopularProductRepo popularProductRepo = new PopularProductRepo();
    MutableLiveData<Product> mutablePopProduct = new MutableLiveData<>();

    public LiveData<List<Product>> getPopularProducto(Context context){
        return popularProductRepo.getPopularProducts(context);
    }


}
