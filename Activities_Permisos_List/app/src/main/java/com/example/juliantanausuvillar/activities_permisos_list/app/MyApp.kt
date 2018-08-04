package com.example.juliantanausuvillar.activities_permisos_list.app

import android.app.Application
import com.example.juliantanausuvillar.activities_permisos_list.others.MySharedPreferences

//sirve para poner logica justo antes de que la aplicacion sea lanzada
// poner en manifest MyApp

// acceso global desde cualquier activity
//by lazy: si no hacemos uso de MySharedPreferences, el ocdigo dentro de lazy nunca sera ejecutado
val preferences: MySharedPreferences by lazy {MyApp.prefs!!}

class MyApp : Application(){

    companion object {
        var prefs: MySharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = MySharedPreferences(applicationContext)
    }
}