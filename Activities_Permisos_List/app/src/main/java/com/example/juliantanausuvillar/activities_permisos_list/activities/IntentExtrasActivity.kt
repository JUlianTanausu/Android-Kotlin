package com.example.juliantanausuvillar.activities_permisos_list.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.juliantanausuvillar.activities_permisos_list.R
import kotlinx.android.synthetic.main.activity_intent_extras.*


class IntentExtrasActivity : AppCompatActivity() {
    /*
    Permite pasar valores de un Activity a otro
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_extras)

        buttonBack.setOnClickListener{ startActivity(Intent(this, IntentsActivity::class.java)) }

        getIntentExtrasFromPreviousActivity()


    }


    private fun getIntentExtrasFromPreviousActivity(){
        val name = intent.getStringExtra("name")
        val lastName = intent.getStringExtra("lastName")
        val age = intent.getIntExtra("age",-1)
        val developer = intent.getBooleanExtra("developer", false)

        if(name != null && lastName != null && age >= 0){
            textViewName.text = name
            textViewLastName.text = lastName
            textViewAge.text = "$age"
            checkBoxDeveloper.isChecked = developer
            checkBoxDeveloper.text = "Developer"


        }else{
            checkBoxDeveloper.visibility = View.INVISIBLE
        }
    }
}
