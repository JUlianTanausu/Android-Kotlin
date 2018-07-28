package com.example.juliantanausuvillar.introduccionkotlin.others

import android.util.Log

class ControlFlow {

    private fun showCase1(){
        // If
        val num1 = 5;
        val num2 = 10

        if(num1 > num2){
            Log.w("CONTROL-FLOW-1", "NUM1 es mayor que NUM2")
        }

        if(num2 > num1){
            Log.w("CONTROL-FLOW-1", "NUM2 es mayor que NUM1")
        }
    }

    private fun showCase2() {
        // if else
        var num1 = 5;
        var num2 = 10

        if (num1 > num2) {
            Log.w("CONTROL-FLOW-2", "NUM1 es mayor que NUM2")

        } else {
            if (num2 > num1) {
                Log.w("CONTROL-FLOW-2", "NUM2 es mayor que NUM1")
            }


        }
    }

    private fun showCase3(){
        // When, lo que seria switvh en java
        val x = 1

        // con argumentos (x)
        when (x){
            1 -> Log.w("CONTROL-FLOW-3", "x === 1") // case 1
            2 -> Log.w("CONTROL-FLOW-3", "x === 2")
            else -> Log.w("CONTROL-FLOW-3", "x es otro numero")// case default, no es obligatorio
        }

        when(x) {
            0, 1 -> Log.w("CONTROL-FLOW-3", "x == 0 0 x == 1")// case 0 y 1

        }


        // sin argumento x
        when {
            (x % 2== 0) -> Log.w("CONTROL-FLOW-3", "el numero es par")
            (x % 2== 1) -> Log.w("CONTROL-FLOW-3", "el numero es impar")
        }

        // Sin argumentos y devolviendo un valor
        val esPar = when {
            (x % 2== 0) -> true
            else -> false
        }



    }



    private fun showCase4(){
        // bucles for
        val numbers = arrayOf(1,2,3,4,5)

        for (mynumber in numbers)
            Log.w("CONTROL-FLOW-4", mynumber.toString())


        // Especificando el tipo
        for (number: Int in numbers){
            Log.w("CONTROL-FLOW-4", "x == 0 0 x == 1")
        }


        // Con indices
        for((index, number) in numbers.withIndex()){
            Log.w("CONTROL-FLOW-4", "$index: $number")
        }
    }

    private fun showCase5(){
    // while y do while
        var x = 10;

        while (x > 10){
            Log.w("CONTROL-FLOW-5", x--.toString())
        }


        //----
        do{
            Log.w("CONTROL-FLOW-5", "Primera y unica iteracion")
        }while (x > 0)
    }


    fun showCases(){
        showCase1()
        showCase2()
        showCase3()
        showCase4()
        showCase5()
    }
}