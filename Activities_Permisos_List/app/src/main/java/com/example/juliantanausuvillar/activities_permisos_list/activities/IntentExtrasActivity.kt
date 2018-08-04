package com.example.juliantanausuvillar.activities_permisos_list.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.juliantanausuvillar.activities_permisos_list.R
import com.example.juliantanausuvillar.activities_permisos_list.models.Student
import com.example.juliantanausuvillar.activities_permisos_list.others.ToolbarActivity
import kotlinx.android.synthetic.main.activity_intent_extras.*


class IntentExtrasActivity : ToolbarActivity() {
    /*
    Permite pasar valores de un Activity a otro
     */

    //private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_extras)
/*
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        // !! para nosotros asumir k puede ser nullo
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

*/
        toolbarToLoad(toolbar as Toolbar)
        enableHomeDisplay(true)

        buttonBack.setOnClickListener{ startActivity(Intent(this, IntentsActivity::class.java)) }

        val isExtraSet = setIntentExtrasFromPreviousActivity()
        val isParcelableSet = setParcelableExtraFromPreviousActivity()

        if(!isExtraSet && !isParcelableSet){
            checkBoxDeveloper.visibility = View.INVISIBLE
        }

    }

    private fun setParcelableExtraFromPreviousActivity(): Boolean{
        val student = intent.getParcelableExtra<Student>("student")

        //si no es nulo ejecuta esi, un is si no es nulo tipo kotlin
        student?.let{
            textViewName.text = student.name
            textViewLastName.text = student.lastName
            textViewAge.text = "${student.age}"
            checkBoxDeveloper.isChecked = student.isDeveloper
            checkBoxDeveloper.text = "Developer"
            return true
        }
        return false
    }

    private fun setIntentExtrasFromPreviousActivity(): Boolean{
        val name = intent.getStringExtra("name")
        val lastName = intent.getStringExtra("lastName")
        val age = intent.getIntExtra("age",-1)
        val developer = intent.getBooleanExtra("developer", false)

        if(name != null && lastName != null && age >= 0) {
            textViewName.text = name
            textViewLastName.text = lastName
            textViewAge.text = "$age"
            checkBoxDeveloper.isChecked = developer
            checkBoxDeveloper.text = "Developer"
            return true

        }
        return false
    }
}
