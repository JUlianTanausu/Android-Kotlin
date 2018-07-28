package com.example.juliantanausuvillar.introduccionkotlin.others


/*
En kotlin todo es un objeto
No hay tipos basicos, y no existe void. Si algo no devuleve nada, está devolviendo Unit object

Las variables pueden ser mutables(variables que pueden cambiar) o inmutables (variables final de java)
inmutables siempre que sea posible
mutables -> var
inmutables -> val

Los tipos son definidos despues del nombre d ela varaible, con dos puntos y espacio
 var nombre: tipo
 El tipo no es obligatorio

 */

class Variables {

    private var variable: Int = 1;
    private var variable0 = 1;
    private var variable1 = 1.toByte()
    private var variable2 = arrayOf(1,2,3,4,5);
    private var variable3 = arrayOfNulls<Int>(10)// aray de nulos, solo aceptaria al meterle enteros



}