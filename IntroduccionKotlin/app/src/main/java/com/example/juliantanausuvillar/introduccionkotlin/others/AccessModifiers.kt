package com.example.juliantanausuvillar.introduccionkotlin.others

// Para hacer una clase "extensible" o "heredada" tenemos que marcarlo como open
// En java es open por defecto y en kotiln es final por defecto, asi que no puede ser extendida sin el uso de 'open
open class AccessModifiers {

    // private          -- solo visible dentro de la clase
    // protected        -- solo visible dentro de la clase y de sus subclases (herencia)
    // public           -- visible desde dentro de la clase, de sus subclases y desde fuera

    val prop1 = "Public by default"
    private val prop2 = "Marked as private"
    protected val prop3 = "Marked as protected"


    protected fun showcase(){
        val test = AccessModifiers()

        test.prop1

    }
}


class AccessModifiersChild : AccessModifiers(){

    private fun showcaseX(){
        super.showcase()
        prop1
        this.prop1

        //prop2 no podemos -> es privado
        this.prop3
    }
}



class AnotherClass {
    private fun showCase() {
        val test = AccessModifiers()

        test.prop1

    }
}