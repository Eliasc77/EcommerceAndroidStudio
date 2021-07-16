package edu.pe.idat.proyectofinal2.repositories;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.SharedPreferencesManager;
import edu.pe.idat.proyectofinal2.commons.VolleySingleton;
import edu.pe.idat.proyectofinal2.models.Cliente;
import edu.pe.idat.proyectofinal2.models.Envio;

public class EnvioRepo {
    private RequestQueue mQueue;


    public void saveEnvio(Context context, Envio envio ){
        JSONObject object = new JSONObject();
        try {
            object.put("distrito", envio.getDistrito());
            object.put("direccion", envio.getDireccion());
            object.put("referencia", envio.getReferencia());
        }catch (JSONException x){
            x.printStackTrace();
        }

        mQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                new Constantes().URL_SAVE_DELIVERY,
                object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("envio", String.valueOf(response));
                        try {
                            SharedPreferencesManager.setSomeIntValue(Constantes.PREF_ID_DELIVERY, response.getInt("iddelivery"));
                        }catch (JSONException x){

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
