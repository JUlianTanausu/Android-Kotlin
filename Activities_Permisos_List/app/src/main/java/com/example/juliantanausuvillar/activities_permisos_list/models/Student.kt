package com.example.juliantanausuvillar.activities_permisos_list.models

import android.os.Parcel
import android.os.Parcelable


// Parcel es un contenedor para un mensaje
// Puede ser usada para mandar un objeto serializado a traves de intent
// Mejore rendimiento que Serializer
data class Student (var name: String, var lastName: String, var age: Int, var isDeveloper: Boolean = true) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(lastName)
        parcel.writeInt(age)
        parcel.writeByte(if (isDeveloper) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }


}