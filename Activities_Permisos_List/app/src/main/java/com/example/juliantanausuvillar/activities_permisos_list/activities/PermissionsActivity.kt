package com.example.juliantanausuvillar.activities_permisos_list.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.example.juliantanausuvillar.activities_permisos_list.R
import kotlinx.android.synthetic.main.activity_permissions.*

class PermissionsActivity : AppCompatActivity() {

    private val requestCameraPermission = 100;
    private val requestCameraPicture = 200;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        buttonPicture.setOnClickListener{ getPictureFromCameraAskingpermissions() }
        //buttonPicture.setOnClickListener{ getPictureFromCamera() }
    }

    // sin pedir permiso, en caso de la camara si se puede, otros no
    private fun getPictureFromCamera(){
        // Asegurarnos de que no hay permiso de camara en el manifest
        // Crear intent para capturar la foto
        val pictureInten = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // COmprobar que podemos manejar la captura de fotos (tenemos camara y app de camara)
        if(pictureInten.resolveActivity(packageManager)!=null){
            startActivityForResult(pictureInten, requestCameraPicture)
        }else{
            // no hay camara o app de camara
            Toast.makeText(this, "No hay camara", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getPictureFromCameraAskingpermissions(){
        // añadir permiso al manifest
        // Comporbar permiso de la camara
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            // si no ha sido aceptado previamente (Para versiones desde la API 23 en adelante
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),100)
        }else {
            // si ha sido aceptado previamente (tbn versiones inferiores api 23)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
            startActivityForResult(intent,requestCameraPicture)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        // switch de kotlin
        when (requestCode){
            requestCameraPermission -> {
                if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // Permiso aceptado
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE)
                    startActivityForResult(intent,requestCameraPicture)
                } else {
                    // Permiso denegado
                    Toast.makeText(this, "You can't take a picture if you don't allow it", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            requestCameraPicture -> {
                // Comprobar si el resultado es bueno
                if (resultCode == Activity.RESULT_OK){
                    // Obtenemos los extras del intent recibido por parametro
                    val extras = data!!.extras
                    // Formamos el bitmap a partir de los extras
                    val imageBitmap = extras.get("data") as Bitmap
                    // cargamos la foto como bitmap en el imageView
                    imageViewPicture.setImageBitmap(imageBitmap)
                } else{
                    // la foto no ha sido tomada con exito
                    Toast.makeText(this, "Picture has failed", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
