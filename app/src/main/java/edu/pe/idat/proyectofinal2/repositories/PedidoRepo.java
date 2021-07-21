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
import edu.pe.idat.proyectofinal2.models.Pedido;

public class PedidoRepo {

    private RequestQueue mQueue;


    public void savePedido(Context context, Pedido pedido ){
        JSONObject object = new JSONObject();
        try {
            object.put("idcliente", pedido.getIdcliente());
            object.put("totalpagar", pedido.getTotalpagar());
            object.put("distrito", pedido.getDistrito());
            object.put("direccion", pedido.getDireccion());
            object.put("referencia", pedido.getReferencia());
            object.put("estado", pedido.isEstado());
            object.put("idtipopago", pedido.getIdtipopago());

        }catch (JSONException x){
            x.printStackTrace();
        }

        mQueue = VolleySingleton.getInstance(context).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                new Constantes().URL_SAVE_PEDIDO,
                object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("pedido", String.valueOf(response));
                        try {
                            SharedPreferencesManager.setSomeIntValue(Constantes.PREF_ID_PEDIDO, response.getInt("idpedido"));
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
