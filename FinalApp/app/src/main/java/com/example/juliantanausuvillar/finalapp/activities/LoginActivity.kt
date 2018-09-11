package com.example.juliantanausuvillar.finalapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import com.example.juliantanausuvillar.finalapp.MainActivity
import com.example.juliantanausuvillar.finalapp.R
import com.example.juliantanausuvillar.finalapp.goToActivity
import com.example.juliantanausuvillar.finalapp.toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() , GoogleApiClient.OnConnectionFailedListener {


    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val mGoogleApiClient: GoogleApiClient by lazy { getGoogleApiClient() }
    private val RC_GOOGLE_SIGN_IN = 99 // da igaul cual


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
/*
        // null si no hay usario logueado (hasta que no cierre sesion)
        if (mAuth.currentUser == null){
            toast("Nope") // lo uso asi gracias al fichero extensions
        }else{
            toast("Yep")
            mAuth.signOut() //cierra la sesion
        }
*/
        buttonLogin.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if(isValidEmail(email) && isValidPassword(password)){
                logInByEmail(email, password)
            }else{
                Toast.makeText(this,"Please make sure all the data is correct", Toast.LENGTH_SHORT).show()
            }
        }


        textViewForgotPassword.setOnClickListener {
            goToActivity<ForgotPasswordActivity>()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }


        buttonCreateAccount.setOnClickListener {
            goToActivity<SingUpActivity>()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)

        }

        buttonloginGoogle.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN )
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
    }


    private fun getGoogleApiClient(): GoogleApiClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        return GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }


    private fun loginByGoogleAccountIntoFirebase(googleAccount: GoogleSignInAccount){
        val credential = GoogleAuthProvider.getCredential(googleAccount.idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener(this){
            if (mGoogleApiClient.isConnected) {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient)
            }
            goToActivity<MainActivity>{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }


    private fun logInByEmail(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
           if (task.isSuccessful){
               if (mAuth.currentUser!!.isEmailVerified){
                   goToActivity<MainActivity>{
                       flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                   }
               }else{
                   toast("User must confirm email first")
               }

               /*val currentUser = mAuth.currentUser!!
               currentUser.displayName
               currentUser.email
               currentUser.photoUrl
               currentUser.phoneNumber
               currentUser.isEmailVerified*/
           }else{
               toast("An unexpected error")
           }
       }
    }

/*
    private fun isValidEmailAndPassword(email: String, password: String): Boolean {

        return !email.isNullOrEmpty() && !password.isNullOrEmpty()
    }

*/


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGN_IN){
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess){
                val account = result.signInAccount
                loginByGoogleAccountIntoFirebase(account!!)
            }
        }
    }


    override fun onConnectionFailed(p0: ConnectionResult) {
        toast("Conection Failed !!")
    }

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
}
