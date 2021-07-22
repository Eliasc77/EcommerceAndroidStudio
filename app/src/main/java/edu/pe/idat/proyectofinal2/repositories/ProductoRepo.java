package edu.pe.idat.proyectofinal2.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.VolleySingleton;
import edu.pe.idat.proyectofinal2.models.Product;

public class ProductoRepo {

    private RequestQueue mQueue;
    List<Product> productList;

    private MutableLiveData<List<Product>> mutableProductList;
    private MutableLiveData<List<Product>> mutableProductSerachList;

    public LiveData<List<Product>> getProducts(Context context, int id){
        if(mutableProductList == null){
            mutableProductList = new MutableLiveData<>();
            loadProducts(context, id);
        }
        return mutableProductList;
    }

    public LiveData<List<Product>> getProductStrings(Context context, String name){
        if(mutableProductList == null){
            mutableProductList = new MutableLiveData<>();
            loadSearchProducs(context, name);
        }
        return mutableProductList;
    }

    private void loadProducts(final Context context, final int id) {
        productList = new ArrayList<>();
        mQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                new Constantes().URL_PRODUCTS_BY_CATEGORY + id,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0){
                            for(int i=0; i<= response.length() ; i++){
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    Product pop = new Product();
                                    pop.setIdproducto(obj.getInt("idproducto"));
                                    pop.setNombreprod(obj.getString("nombreprod"));
                                    pop.setPrecio(obj.getDouble("precio"));
                                    pop.setDescripcion(obj.getString("descripcion"));
                                    pop.setFoto(obj.getString("foto"));

                                    pop.setStock(obj.getInt("stock"));
                                    productList.add(pop);

                                }catch (JSONException x){
                                    x.printStackTrace();
                                }
                            }
                            mutableProductList.setValue(productList);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }

    private void loadSearchProducs(final Context context, final String name){
        productList = new ArrayList<>();
        mQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                new Constantes().URL_PRODUCTO_SEARCH + name,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0){
                            for(int i=0; i<= response.length() ; i++){
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    Product pop = new Product();
                                    pop.setIdproducto(obj.getInt("idproducto"));
                                    pop.setNombreprod(obj.getString("nombreprod"));
                                    pop.setPrecio(obj.getDouble("precio"));
                                    pop.setFoto(obj.getString("foto"));
                                    pop.setDescripcion("descripcion");
                                    pop.setStock(obj.getInt("stock"));
                                    productList.add(pop);

                                }catch (JSONException x){
                                    x.printStackTrace();
                                }
                            }
                            mutableProductList.setValue(productList);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);


    }
}
