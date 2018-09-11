package com.example.juliantanausuvillar.finalapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import com.example.juliantanausuvillar.finalapp.R
import com.example.juliantanausuvillar.finalapp.goToActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sing_up.*
import java.util.regex.Pattern


class SingUpActivity : AppCompatActivity() {

    private val  mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        buttonGoLogIn.setOnClickListener {
            goToActivity<LoginActivity>{
                //para que el atras, luego no vuelva aqui, no guarda en historial esta ventana
                flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            } //goActivity lo hemos importado de las extensions. fichero Extensions

            // animaciones
            //-----------
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            //overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)



            // SI NO seria de la siguiente manera

            // ---------------------------------------------------------------------------
           // val intent = Intent(this,LoginActivity::class.java)

            //para que el atras, luego no vuelva aqui, no guarda en historial esta ventana
           // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
           // startActivity(intent)
            //--------------------------------------------------------------------------
        }


        buttonSignUp.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val confirmPassword = editTextConfirmPassword.text.toString()

            if(isValidEmail(email) && isValidPassword(password) && isValidConfirmPassword(password,confirmPassword)){
                signUpByEmail(email, password)
            }else{
                Toast.makeText(this,"Please make sure all the data is correct", Toast.LENGTH_SHORT).show()
            }
        }


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


        editTextPassword.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (isValidPassword(editTextPassword.text.toString())){
                    editTextPassword.error = null
                }else{
                    editTextPassword.error = "Password should contain 1 lowercase, 1 uppercase, 1number, 1 special character and 4 characteres length at least"
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        editTextConfirmPassword.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (isValidConfirmPassword(editTextPassword.text.toString(), editTextConfirmPassword.text.toString())){
                    editTextConfirmPassword.error = null
                }else{
                    editTextConfirmPassword.error = "Confirm Password does not match with Password"
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


    }

    private fun signUpByEmail(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                mAuth.currentUser!!.sendEmailVerification().addOnCompleteListener(this){
                    Toast.makeText(this,"An email has been sent to you. Please, confirm before sign in", Toast.LENGTH_SHORT).show()

                    goToActivity<LoginActivity>{
                        flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }


            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(this,"An unexpected error occurred, please try again", Toast.LENGTH_SHORT).show()


            }


        }
    }


/*
    private fun isValidEmailAndPassword(email: String, password: String): Boolean{
        return !email.isNullOrEmpty() &&
                !password.isNullOrEmpty() &&
                password == editTextConfirmPassword.text.toString()
    }
    */

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        // Necesita contener ->     1 Num /   1 Minuscula / 1 Mayuscula /    1 Especial /     Min caracteres 4
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%6^+=!])(?=\\S+$).{4,}$"
        val pattern = Pattern.compile(passwordPattern)
        return pattern.matcher(password).matches()
    }

    private fun isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}
