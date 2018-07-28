package com.example.juliantanausuvillar.introduccionkotlin.others

import android.util.Log

class Interfaces {

    private var anonymousObjectImplementingInterface: Any? = null // ANY = cualqer tipo, String , int. etc




    private fun showCase1(){
        anonymousObjectImplementingInterface = object : Interface1 {
            override fun abstractMethod() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }

        // como pusimo any, as para que sepa que es del tipoo Interface1
        (anonymousObjectImplementingInterface as Interface1).methodWithImplementationByDefault()
        (anonymousObjectImplementingInterface as Interface1).abstractMethod()
    }

    private fun showCase2(){
        val aoii = object : interface2 {
            override val propertyAbstract: Int
                get() = 10
        }

        aoii.propertyAbstract
        aoii.propertyWithImplementation
        aoii.hello()

    }


    fun showCases(){
        showCase1()
        showCase2()
    }


    interface Interface1 {

        fun abstractMethod() // al no tener codigo, el ya sabe k es abstracto

        fun methodWithImplementationByDefault(){  // este no es abstarcto por las llaves

        }
    }

    interface interface2{

        val propertyAbstract: Int
        val propertyWithImplementation: String
            get() = "ByDefault"

        fun hello(){
            Log.w("INTERACE-2","Is it working $propertyWithImplementation?, YaY! $propertyAbstract :)")
        }
    }
}