package com.example.juliantanausuvillar.activities_permisos_list.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.juliantanausuvillar.activities_permisos_list.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*


class PicassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        buttonLoader.setOnClickListener { loadImages() }

        //esta carga mas rapido xk aqui primero es cacheado, desde su primera vez
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").fetch()
    }

    private fun loadImages(){
        Picasso
                .with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .fit() // coupa to el cuadro asignado pa la imagen del layout
                .into(imageViewTop)
        /*
        Picasso
                .with(this)
                .load("https://www.blogdelfotografo.com/wp-content/uploads/2015/09/paisaje-t%C3%ADpico.jpg")
                .fit() // coupa to el cuadro asignado pa la imagen del layout
                .into(imageViewBottom)
         */

        /*
        Picasso
                .with(this)
                .load("https://www.blogdelfotografo.com/wp-content/uploads/2015/09/paisaje-t%C3%ADpico.jpg")
                .fit() // coupa to el cuadro asignado pa la imagen del layout
                .transform(CircleTransform()) // renderizar la imagen como un circulo
                .into(imageViewBottom)
        */

        val context: Context = this

        Picasso
                .with(this)
                .load("https://www.blogdelfotografo.com/wp-content/uploads/2015/09/paisaje-t%C3%ADpico.jpg")
                .fetch(object: Callback {

                    override fun onSuccess() {
                        imageViewBottom.alpha = 0f
                        Picasso.with(context)
                                .load("https://www.blogdelfotografo.com/wp-content/uploads/2015/09/paisaje-t%C3%ADpico.jpg")
                                .fit()
                                .into(imageViewBottom)

                        imageViewBottom.animate().setDuration(300).alpha(1f).start()
                    }

                    override fun onError() {

                    }

                })


    }
}
