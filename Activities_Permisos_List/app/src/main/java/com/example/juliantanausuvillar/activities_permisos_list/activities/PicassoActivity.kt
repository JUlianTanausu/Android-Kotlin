package com.example.juliantanausuvillar.activities_permisos_list.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.juliantanausuvillar.activities_permisos_list.R


class PicassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)


        // NO FUNCIONA LA LIBRERIA PICASSO

       // Picasso.get(this).load("https//static.pexels.com/photos/288264/pexels-photo-288264.jpeg").fetch()
    }
}
