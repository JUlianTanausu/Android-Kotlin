package com.example.juliantanausuvillar.activities_permisos_list.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.juliantanausuvillar.activities_permisos_list.MainActivity
import com.example.juliantanausuvillar.activities_permisos_list.R
import com.example.juliantanausuvillar.activities_permisos_list.others.goToActivity
import com.example.juliantanausuvillar.activities_permisos_list.others.loadbyURL
import com.example.juliantanausuvillar.activities_permisos_list.others.snackBar
import com.example.juliantanausuvillar.activities_permisos_list.others.toast
import kotlinx.android.synthetic.main.activity_extension_functions.*

class ExtensionFunctionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extension_functions)

        buttonToast.setOnClickListener { toast("I love extension functions")}
        buttonSnackBar.setOnClickListener { snackBar("I love extension functions", action = "Undo") { toast("undo")} }
        buttonLoadByUrl.setOnClickListener { imageViewLoadedByUrl.loadbyURL("https://udemy-images.udemy.com/course/480x270/1325930_f5f6_3.jpg") }
        buttonGoToActivity.setOnClickListener { goToActivity<MainActivity>{ putExtra("id", 1) } }
    }
}
