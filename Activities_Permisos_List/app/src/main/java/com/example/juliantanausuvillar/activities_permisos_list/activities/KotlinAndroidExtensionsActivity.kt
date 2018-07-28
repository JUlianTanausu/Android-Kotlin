package com.example.juliantanausuvillar.activities_permisos_list.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.juliantanausuvillar.activities_permisos_list.R

// con esto no hace falta ahcer nunca mas findViewById --> kotlin extensions
import kotlinx.android.synthetic.main.activity_kotlin_android_extensions.*

class KotlinAndroidExtensionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_android_extensions)

        val btn = findViewById<Button>(R.id.buttonById)
        btn.setOnClickListener { Toast.makeText(this, "CLick By ID", Toast.LENGTH_SHORT).show() }

        // KOTLIN EXTENSIONS
        buttonByKAT.setOnClickListener{ Toast.makeText(this, "CLick By KAT", Toast.LENGTH_SHORT).show() }
    }
}
