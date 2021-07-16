package edu.pe.idat.proyectofinal2.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import edu.pe.idat.proyectofinal2.models.CarritoItem;
import edu.pe.idat.proyectofinal2.models.Product;

public class CartRepo {

    //creamos el objeto
    private MutableLiveData<List<CarritoItem>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Double> mutableTotalPrice = new MutableLiveData<>();

    //metodo para retornar el livedata
    public LiveData<List<CarritoItem>> getCart(){
        if(mutableCart.getValue() == null){
            //inicializamos el carrto si esta vacio
            initCart();
        }
        return mutableCart;
    }

    public void initCart() {
        mutableCart.setValue(new ArrayList<>());
        calcularTotal();
    }

    //metodo para agregar al carrito
    public boolean addItemToCart(Product product){
        if(mutableCart.getValue() ==null){
            initCart();
        }
        //se inicia el array con items cargado
        List<CarritoItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        //creamos un lopp
        //para ver si existe productos con el mismo id
        for(CarritoItem cartItem: cartItemList){
            //validamos si el producto que tenemos eeen el arraylist es
            // igaul al id producto que estamos ingresando
            if(cartItem.getProduct().getIdproducto()==(product.getIdproducto())){
                //validacion de solo 10 items al carrito
                if(cartItem.getCantidad() == 10){
                    return false;
                }
                //hacemos un update al carrito
                //index almacenamos la posicion en la que nos encontramo
                int index = cartItemList.indexOf(cartItem);
                //hacemos un update ala cantidad , sumando la antigua +1
                cartItem.setCantidad(cartItem.getCantidad()+1);
                ///hacemos un update al arraylist enviandole la posicion + el objeto
                cartItemList.set(index, cartItem);
                // le enviamos el arraylist a nuestro mutable
                mutableCart.setValue(cartItemList);
                calcularTotal();
                return  true;
            }
        }

        CarritoItem cartItem  = new CarritoItem(product,1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        calcularTotal();

        return true;
    }

    public void removeItemFromCart(CarritoItem carritoItem){

        if(mutableCart.getValue() == null){
            return;
        }
        //traemos los valores del array y lo almacenamos en caritemlis
        List<CarritoItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        //usamos el metodo remove y le enviamos el objeto
        cartItemList.remove(carritoItem);

        //actualizamos el array
        mutableCart.setValue(cartItemList);
        calcularTotal();

    }

    //change quantity
    public void changeQuantity (CarritoItem carritoItem, int quantity){
        if(mutableCart.getValue() == null){
            return;
        }
        List<CarritoItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        CarritoItem updateitem = new CarritoItem(carritoItem.getProduct(), quantity);
        cartItemList.set(cartItemList.indexOf(carritoItem), updateitem);
        mutableCart.setValue(cartItemList);
        calcularTotal();
    }

    ///luego agregamos este metodo en los demas ya que cada que cambiamos una cantidad
    //    //o borramos un producto se debera volver a calcular
    public void calcularTotal(){
        if(mutableCart.getValue() == null) return;
        //
        double total = 0.00;
        List<CarritoItem> carritoItemList = mutableCart.getValue();
        //creamos un arreglo para recorrer cada uno de los elementos y sumar los precio
        for(CarritoItem carritoItem : carritoItemList){
            total += carritoItem.getProduct().getPrecio() * carritoItem.getCantidad();
        }
        mutableTotalPrice.setValue(total);
    }


    //enviara el valor
    public LiveData<Double>getTotalPrice(){
        if(mutableTotalPrice.getValue()==null){
            mutableTotalPrice.setValue(0.00);
        }
        return  mutableTotalPrice;
    }

}
