package com.example.juliantanausuvillar.activities_permisos_list.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.juliantanausuvillar.activities_permisos_list.R
import com.example.juliantanausuvillar.activities_permisos_list.adapters.PersonAdapter
import com.example.juliantanausuvillar.activities_permisos_list.models.Person
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    private lateinit var adapter: PersonAdapter
    private lateinit var personList: List<Person>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)


        personList = getPersons()
        adapter = PersonAdapter(this, R.layout.list_view_person, personList)
        listView.adapter = adapter


    }


    private fun getPersons(): List<Person> {
         return listOf(
                Person("Julian","Tanausu",29),
                Person("Nidia","Sanchez",28),
                Person("Alicia","Gomez",19),
                Person("Santiago","Compostela",22)
        )

    }
}
