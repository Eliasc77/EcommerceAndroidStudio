package edu.pe.idat.proyectofinal2.commons;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    public static VolleySingleton mInstance;

    private RequestQueue mRequestQueue;

    //crearemos un constructor privado solo en esta clase con acceso desde dentro
    private VolleySingleton(Context context){
        // Hacemos el contexto para todo el tiempo de vida de la aplicación, no solo para una sola actividad.
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());

    }

    // este método es acceder al objeto VolleySingleton una vez y en un hilo a la vez
    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    //// este método es para acceder a la cola de solicitudes desde el lado exterior
    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
