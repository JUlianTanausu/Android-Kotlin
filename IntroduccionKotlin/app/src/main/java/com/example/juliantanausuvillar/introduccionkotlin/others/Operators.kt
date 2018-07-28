package com.example.juliantanausuvillar.introduccionkotlin.others

import android.util.Log


class Operators {

    private fun showCase1(){
        var a = 5;
        var b = 5;

        Log.w("OPERATORS-1", "Referentil equality. Es 'a' igual a 'b'? ${a === b}")
        Log.w("OPERATORS-1", "Structural equality. Es 'a' igual a 'b'? ${a == b}")
    }

    private fun showCase2(){
        val pn1 = PersonNormal();
        val pn2 = PersonNormal();


        Log.w("OPERATORS-2", "Referentil equality. Es 'a' igual a 'b'? ${pn1 === pn2}") // false
        Log.w("OPERATORS-2", "Structural equality. Es 'a' igual a 'b'? ${pn1 == pn2}") // false, xk no sobreescribe equals

    }

    private fun showCase3(){
        val pn1 = PersonEqualOverride();
        val pn2 = PersonEqualOverride();

        Log.w("OPERATORS-3", "Referentil equality. Es 'a' igual a 'b'? ${pn1 === pn2}") // false
        Log.w("OPERATORS-3", "Structural equality. Es 'a' igual a 'b'? ${pn1 == pn2}") // true

    }

    private fun showCase4(){
        //con data calss, compara todas als propiedades
        val pn1 = PersonDataClass(); // Alejandro , 27
        val pn2 = PersonDataClass();// Alejandro , 27
        val pn3 = PersonDataClass("Roberto");// Roberto , 27

        Log.w("OPERATORS-4", "Referentil equality. Es 'a' igual a 'b'? ${pn1 === pn2}") // false
        Log.w("OPERATORS-4", "Structural equality. Es 'a' igual a 'b'? ${pn1 == pn2}") // true
        Log.w("OPERATORS-4", "Structural equality. Es 'a' igual a 'b'? ${pn3 == pn3}") // false

    }

    fun showCases(){
        showCase1();
        showCase2();
        showCase3();
        showCase4();
    }




    class PersonNormal {
        val name = "Alejandro"
    }

    class PersonEqualOverride {
        val name = "Alejandro"
        override fun equals(other: Any?): Boolean {
            if (other === null) return false
            if(other is PersonEqualOverride){
                return other.name === this.name
            }
            return false
        }
    }

    data class PersonDataClass(val name: String = "Alejandro", val age: Int = 27)

}