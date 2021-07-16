package edu.pe.idat.proyectofinal2.repositories;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.SharedPreferencesManager;
import edu.pe.idat.proyectofinal2.commons.VolleySingleton;
import edu.pe.idat.proyectofinal2.models.CarritoItem;
import edu.pe.idat.proyectofinal2.models.Cliente;

public class ClientRepo {
    private RequestQueue mQueue;


    public void saveCliente(Context context, Cliente cliente ){
        JSONObject object = new JSONObject();
        try {
            object.put("nombre", cliente.getNombre());
            object.put("apellido", cliente.getApellido());
            object.put("dni", cliente.getDni());
            object.put("celular", cliente.getCelular());
            object.put("idlogin",1);
        }catch (JSONException x){
            x.printStackTrace();
        }

        mQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                new Constantes().URL_SAVE_CLIENTE,
                object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                            Log.d("param", String.valueOf(response));
                            try {
                                SharedPreferencesManager.setSomeIntValue(Constantes.PREF_ID_CLIENTE, response.getInt("idcliente"));
                            }catch (JSONException x){
                                x.printStackTrace();
                            }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        mQueue.add(jsonObjectRequest);
    }
}
