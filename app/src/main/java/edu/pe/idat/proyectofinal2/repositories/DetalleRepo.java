package edu.pe.idat.proyectofinal2.repositories;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.pe.idat.proyectofinal2.commons.Constantes;
import edu.pe.idat.proyectofinal2.commons.SharedPreferencesManager;
import edu.pe.idat.proyectofinal2.commons.VolleySingleton;
import edu.pe.idat.proyectofinal2.models.Detalle;
import edu.pe.idat.proyectofinal2.models.Envio;

public class DetalleRepo {
    private RequestQueue mQueue;


    public void saveDetalle(Context context, Detalle detalle ){
        JSONObject object=new JSONObject();
        try {
            object.put("idpedido", detalle.getIdpedido());
            object.put("idproducto", detalle.getIdproducto());
            object.put("cantidad", detalle.getCantidad());
            object.put("precio", detalle.getPrecio());
        }catch (JSONException x){
            x.printStackTrace();
        }

        mQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                new Constantes().URL_SAVE_DETALLE,
                object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("detalle", String.valueOf(response));

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
