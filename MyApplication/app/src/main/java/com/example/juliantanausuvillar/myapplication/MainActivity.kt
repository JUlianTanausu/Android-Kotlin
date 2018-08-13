package com.example.juliantanausuvillar.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mylibrary.ToolbarActivity

class MainActivity : ToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
