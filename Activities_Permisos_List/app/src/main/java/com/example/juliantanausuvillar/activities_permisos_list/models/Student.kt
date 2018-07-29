package com.example.juliantanausuvillar.activities_permisos_list.models

import android.os.Parcel
import android.os.Parcelable


// Parcel es un contenedor para un mensaje
// Puede ser usada para mandar un objeto serializado a traves de intent
// Mejore rendimiento que Serializer


/* PArcelable se usa para poder pasar un pbjeto a otro activity */
data class Student (var name: String, var lastName: String, var age: Int, var isDeveloper: Boolean = true) : Parcelable{

    // leer en el mismo orden que declaramos las propiedades
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // tenemos k escribir los valores en el mismo orden en el que leemos el constructor
        parcel.writeString(name)
        parcel.writeString(lastName)
        parcel.writeInt(age)
        parcel.writeByte(if (isDeveloper) 1 else 0)
    }

    // Describe el tipo de contenido del objeto parselabñre
    // 0 siempre se usa, el otro valor es 1 o CONTENTS_FILE_DESCRIPTOR para tipos de objetos que implementan un FILE Descriptor
    override fun describeContents()= 0


    companion object CREATOR : Parcelable.Creator<Student>{

        //Esto creara el objeto desde el parcel llamando al constructor que hemos creado
        override fun createFromParcel(parcel: Parcel) = Student(parcel)

        // Esto ayudara a serializar arrays de objetos del mismo tipo que esta clase student
        override fun newArray(size: Int) = arrayOfNulls<Student?>(size)

    }


}