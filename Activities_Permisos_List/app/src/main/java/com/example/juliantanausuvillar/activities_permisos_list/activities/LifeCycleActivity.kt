package com.example.juliantanausuvillar.activities_permisos_list.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.juliantanausuvillar.activities_permisos_list.R

class LifeCycleActivity : LifeCycleEventsActivity() {

    private var exitEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)
    }

    override fun onStart(){
        super.onStart()
    }


    // boton de atras de android
    override fun onBackPressed() {
        if(exitEnabled){
            super.onBackPressed()
        }
        exitEnabled = true;
        Toast.makeText(this, "Click back again to exit this screen", Toast.LENGTH_SHORT).show()

        //pone a false pasado 2 segundos
        Handler().postDelayed(Runnable { exitEnabled = false }, 2000)
    }
}
