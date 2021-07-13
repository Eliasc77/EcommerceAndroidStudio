package edu.pe.idat.proyectofinal2.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.VolleySingleton;
import edu.pe.idat.proyectofinal2.models.Product;

public class PopularProductRepo {

    private RequestQueue mQueue;
    List<Product> poproductoList;
    private MutableLiveData<List<Product>> mutablePopularProducts;

    public LiveData<List<Product>> getPopularProducts(Context context){
        if(mutablePopularProducts == null){
            mutablePopularProducts = new MutableLiveData<>();
            loadPopularProducts(context);
        }
        return mutablePopularProducts;
    }

    private void loadPopularProducts(final Context context) {
        poproductoList = new ArrayList<>();
        mQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                new Constantes().URL_POPULAR_PRODUCTS,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            for (int i = 0; i <= response.length(); i++) {
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    Product pop = new Product();
                                    pop.setIdproducto(obj.getInt("idproducto"));
                                    pop.setNombreprod(obj.getString("nombreprod"));
                                    pop.setPrecio(obj.getDouble("precio"));
                                    pop.setFoto(obj.getString("foto"));
                                    pop.setStock(obj.getInt("stock"));
                                    poproductoList.add(pop);
                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            mutablePopularProducts.setValue(poproductoList);
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
