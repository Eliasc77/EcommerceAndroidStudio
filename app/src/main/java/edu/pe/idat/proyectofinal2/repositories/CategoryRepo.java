package edu.pe.idat.proyectofinal2.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
import edu.pe.idat.proyectofinal2.models.Categoria;
import edu.pe.idat.proyectofinal2.models.Product;

public class CategoryRepo {
    private RequestQueue mQueue;
    List<Categoria> categoriaList;

    private MutableLiveData<List<Categoria>> mutableCategoria;
    private MutableLiveData<Integer> mutableid = new MutableLiveData<>();

    public LiveData<List<Categoria>> getCategorias(Context context){
        if(mutableCategoria == null){
            mutableCategoria = new MutableLiveData<>();
            loadCategorias(context);
        }
        return mutableCategoria;
    }

    private void loadCategorias(final Context context) {
        categoriaList = new ArrayList<>();
        mQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                new Constantes().URL_CATEGORIA,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0){
                            for(int i =0; i<= response.length() ; i++){
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    Categoria cat = new Categoria();
                                    cat.setIdcategoria(obj.getInt("idcategoria"));
                                    cat.setDescripcion(obj.getString("descripcion"));
                                    cat.setImg(obj.getString("img"));
                                    categoriaList.add(cat);
                                }
                                catch (JSONException ex){
                                    ex.printStackTrace();
                                }
                            }
                            mutableCategoria.setValue(categoriaList);
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
