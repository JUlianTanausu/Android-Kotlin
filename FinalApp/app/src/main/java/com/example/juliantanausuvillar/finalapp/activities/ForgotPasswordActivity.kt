package com.example.juliantanausuvillar.finalapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import com.example.juliantanausuvillar.finalapp.R
import com.example.juliantanausuvillar.finalapp.goToActivity
import com.example.juliantanausuvillar.finalapp.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    // hasta k no usemos mAuth, no va ejecutar el codigo. eso hace lazy. solo se llama 1 vez
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        // listener para cuando texto cambia
        editTextEmail.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (isValidEmail(editTextEmail.text.toString())){
                    editTextEmail.error = null
                }else{
                    editTextEmail.error = "Email is not valid"
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        buttonGoLogIn.setOnClickListener{
            goToActivity<LoginActivity>{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }


        buttonForgot.setOnClickListener {
            val email = editTextEmail.text.toString()
            if (isValidEmail(email)){
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(this){
                    toast("email has been sent to reset your password")
                    goToActivity<LoginActivity>{
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
            }else{
                toast("Please make sure the email address is correct")
            }
        }
    }


    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches()
    }
}
