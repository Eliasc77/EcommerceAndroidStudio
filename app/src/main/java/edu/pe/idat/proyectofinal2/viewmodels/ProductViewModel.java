package edu.pe.idat.proyectofinal2.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.pe.idat.proyectofinal2.models.CarritoItem;
import edu.pe.idat.proyectofinal2.models.Product;
import edu.pe.idat.proyectofinal2.repositories.CartRepo;
import edu.pe.idat.proyectofinal2.repositories.ProductoRepo;

public class ProductViewModel extends ViewModel {

    ProductoRepo productoRepo = new ProductoRepo();
    CartRepo cartRepo = new CartRepo();


    MutableLiveData<Product> mutableProduct = new MutableLiveData<>();

    public LiveData<List<Product>> getProducts(Context context, int id){
        return productoRepo.getProducts(context, id);
    }

    public LiveData<List<Product>>getProductStrings(Context context, String url){
        return productoRepo.getProductStrings(context, url);
    }

    public void setProduct(Product product){
        mutableProduct.setValue(product);
    }

    public LiveData<Product> getProduct(){
        return mutableProduct;
    }


    //METODOS PARA EL CARRITO :
    //ESTE METODO NOS RETORNARA EL CARRITO YA SEA SI ESTA VACIO O CON ITEMS.
    public  LiveData<List<CarritoItem>> getCart(){
        return  cartRepo.getCart();
    }
    //
    public boolean addItemToCart(Product product){
        return cartRepo.addItemToCart(product);
    }

    public void removeItemFromCart(CarritoItem carritoItem){
        cartRepo.removeItemFromCart(carritoItem);
    }


    public void changeQuantity( CarritoItem carritoItem , int quantity){
        cartRepo.changeQuantity(carritoItem, quantity);
    }

    //calcular total prceio
    public LiveData<Double>getTotalPrice(){
        return cartRepo.getTotalPrice();
    }


    //resetear carrito
    public void resetCart(){
        cartRepo.initCart();
    }
}
