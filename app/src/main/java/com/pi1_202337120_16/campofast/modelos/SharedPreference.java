package com.pi1_202337120_16.campofast.modelos;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    Context context;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public SharedPreference(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("bd_shared", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public void setUsuarioActivo(String correo){
        editor.putString("Correo Activo", correo);
        editor.apply();
    }

    public String getUsuarioActivo(){
        return sharedPreferences.getString("Correo Activo", "no encontrado");
    }

}



