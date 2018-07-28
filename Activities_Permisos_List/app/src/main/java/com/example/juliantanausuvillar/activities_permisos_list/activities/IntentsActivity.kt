package com.example.juliantanausuvillar.activities_permisos_list.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.juliantanausuvillar.activities_permisos_list.R
import kotlinx.android.synthetic.main.activity_intents.*

class IntentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents)

        buttonIntentExtras.setOnClickListener{ goIntentExtras() }
        buttonIntentFlags.setOnClickListener{ goIntentFlags() }
        buttonIntentObject.setOnClickListener{ goIntentObject() }
    }

    private fun goIntentExtras(){
        //to lo k pongo en putExtra lo pasaria al activity IntentExtrasActivity
        val intent = Intent(this, IntentExtrasActivity::class.java)
        intent.putExtra("name", "Julian")
        intent.putExtra("lastName", "Villar")
        intent.putExtra("age", 27)
        intent.putExtra("developer", true)
        startActivity(intent)
    }



    /*
    Cada vez k pasas a un activity con un boton en android, eso se guarda en una pila
    para cuando le das al boton atras, hacer el recorrido inverso. pero ese comportamiento
    se puede cambiar con los flags
     */
    private fun goIntentFlags(){
        val intent = Intent(this, IntentExtrasActivity::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY // no guarda el activity en la pila
        //intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION // quita la animacion al pasar de activity
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // borra la pila completa
        startActivity(intent)
        //finish() // destruye el activity, no lo añade ala cola
    }


    private fun goIntentObject(){
        val intent = Intent(this, IntentExtrasActivity::class.java)
        startActivity(intent)
    }
}
