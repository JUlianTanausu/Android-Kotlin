package com.example.juliantanausuvillar.introduccionkotlin.others

class Nullable {


/*
    Operadores relacionados con la nulabilidad:
        - only-safe (?)
        - non-null asserted (!!)

     Sistema de tipado de kotlin está pensado para eliminar de nuestro codigo el NullPointerException.
     Los 4 únicos posibles casos para Null Pointer Exception son:

        1) Una llamada explícita al error NullPointerException()
        2) Uso del operador !!
        3) Código externo Java
        4) Alguna inconsistencia de datos en relación a la inicializacion
 */

    private lateinit var variable1: String; // lateinit, para decirle k si k no esta inicializado y k se caye
    private var variable2: String? = null;

    private fun showCase1(){
        throw NullPointerException();
    }

    private fun showCase2(var1: String?){ // de tipo string pero k puede ser nulo
        var1.toString(); //si la variable es null, devulve null
        var1!!.toString(); // si es nulo, lanza NullPointerException
    }

    private fun showCase3(){
        Other.getNullPointerException();// devuleve Null pointer exception
    }

    private fun showCase4(){
        variable1.length // Devuelbe null pointer xk variable no ha sido inicailida mas tarde
        variable2?.length
        variable2!!.length
    }

    fun showCases(){
        showCase1();
        showCase2("hola");
        showCase3();
        showCase4();
    }
}