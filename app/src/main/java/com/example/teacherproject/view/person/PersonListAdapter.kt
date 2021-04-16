package com.example.teacherproject.view.person

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.R
import com.example.teacherproject.model.PersonModel

class PersonListAdapter(var data: LiveData<List<PersonModel>>, val subjectID: Int): RecyclerView.Adapter<PersonListAdapter.Holder>() {

    companion object {
        fun newInstance() = AddPersonList()
    }

    lateinit var context: Context

    class Holder(view: View, data: LiveData<List<PersonModel>>, subjectID: Int) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                val position: Int = adapterPosition
                val action = PersonListDirections.actionPersonListToPerson(
                        data.value?.get(position)?.personID ?: 0,
                        subjectID)
                findNavController(view).navigate(action)
            }
            view.findViewById<Button>(R.id.button_add_grade_from_list).setOnClickListener {
                val action = PersonListDirections.actionPersonListToAddGrade(
                        data.value?.get(position)?.personID ?: 1, subjectID)
                findNavController(view).navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.row_student, parent, false) as View
        return Holder(view, data, subjectID)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val textName = holder.itemView.findViewById<TextView>((R.id.text_person_name))
        val textSurname = holder.itemView.findViewById<TextView>((R.id.text_person_surname))
        textName.text = data.value?.get(position)?.Name
        textSurname.text = data.value?.get(position)?.Surname
    }

    override fun getItemCount(): Int {
        return data.value?.size ?: 0
    }
}