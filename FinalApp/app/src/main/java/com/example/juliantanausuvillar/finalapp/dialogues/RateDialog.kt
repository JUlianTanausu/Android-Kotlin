package com.example.juliantanausuvillar.finalapp.dialogues



//cambaimos import pa no tener k subir el min SDK
//import android.app.AlertDialog
//import android.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v4.app.DialogFragment

import android.app.Dialog

import android.os.Bundle
import com.example.juliantanausuvillar.finalapp.R
import com.example.juliantanausuvillar.finalapp.models.NewRateEvent
import com.example.juliantanausuvillar.finalapp.models.Rate
import com.example.juliantanausuvillar.finalapp.toast
import com.example.juliantanausuvillar.finalapp.utils.RxBus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.dialog_rate.view.*
import java.util.*

class RateDialog : DialogFragment() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var currentUser: FirebaseUser

    private fun setUpCurrentUser() {
        currentUser = mAuth.currentUser!!
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        setUpCurrentUser()
        val view = activity!!.layoutInflater.inflate(R.layout.dialog_rate, null)

        return AlertDialog.Builder(context!!)
                .setTitle(getString(R.string.dialog_title))
                .setView(view)
                .setPositiveButton(getString(R.string.dialog_ok)) { _, _ ->
                    val textRate = view.editTextRateFeedback.text.toString()
                    if (textRate.isNotEmpty()) {
                        val imgURL = currentUser.photoUrl?.toString() ?: run { "" }
                        val rate = Rate(currentUser.uid, textRate, view.ratingBarFeedback.rating, Date(), imgURL)
                        RxBus.publish(NewRateEvent(rate))
                    }
                }
                .setNegativeButton(getString(R.string.dialog_cancel)) { _, _ ->
                    activity!!.toast("Pressed Cancel")
                }
                .create()
    }

}