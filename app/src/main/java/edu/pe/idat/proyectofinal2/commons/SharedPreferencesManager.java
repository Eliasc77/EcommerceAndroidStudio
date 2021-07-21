package edu.pe.idat.proyectofinal2.commons;

import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String APP_SETTINGS_FILE = "APP_SETTINGS";

    public SharedPreferencesManager() {
    }

    private static SharedPreferences getSharedPreferences(){
        return MyApp.getContext().getSharedPreferences(APP_SETTINGS_FILE,
                MyApp.MODE_PRIVATE);
    }

    //almacenar valores

    public static  void setSomeIntValue(String nombrePropiedad, int valor){
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(nombrePropiedad, valor);
        editor.commit();
    }
    //devolver valores
    public static  int getSomeIntValue(String nombrePropiedad){
        return  getSharedPreferences().getInt(nombrePropiedad,0);
    }

    public static void setSomeStringValue(String nombrePropiedad,String valor){
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(nombrePropiedad, valor);
        editor.commit();
    }
    public static String getSomeStringValue(String nombrePropiedad){
        return getSharedPreferences().getString(nombrePropiedad,"");
    }
}
