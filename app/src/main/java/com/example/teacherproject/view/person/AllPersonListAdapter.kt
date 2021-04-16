package com.example.teacherproject.view.person

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.R
import com.example.teacherproject.model.PersonModel
import com.example.teacherproject.viewModel.person.StudentAllViewModel

class AllPersonListAdapter(var data: LiveData<List<PersonModel>>,
                           var viewModel: StudentAllViewModel
): RecyclerView.Adapter<AllPersonListAdapter.Holder>() {

    lateinit var context: Context

    class Holder(view: View, data: LiveData<List<PersonModel>>, viewModel: StudentAllViewModel) : RecyclerView.ViewHolder(view) {
        init {

            view.setOnClickListener{
                /*val position: Int = adapterPosition
                val action = StudentAllDirections.actionStudentAllToPerson(viewModel.myList.value?.get(position)?.personID?:0,)
                findNavController(view).navigate(action)*/
            }

            view.findViewById<ImageButton>(R.id.edit_student_from_list).setOnClickListener{
                val position = adapterPosition
                viewModel.currentPersonID = viewModel.myList.value?.get(position)?.personID?:0
                val action = StudentAllDirections.actionStudentAllToEditPerson(viewModel.currentPersonID)
                findNavController(view).navigate(action)
                }

            view.findViewById<Button>(R.id.remove_student_from_list).setOnClickListener {
                val position = adapterPosition
                viewModel.currentPersonID = viewModel.myList.value?.get(position)?.personID?:0
                var uselessSubjectRelations = viewModel.removePersonFromSubject(viewModel.currentPersonID)
                var uselessGradeRelations = viewModel.removePersonFromGrade(viewModel.currentPersonID)
                viewModel.killRelations(uselessSubjectRelations,uselessGradeRelations)
                viewModel.killPerson(viewModel.myList.value!![position])

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.row_student_delete, parent, false) as View
        return Holder(view, data, viewModel)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val textName = holder.itemView.findViewById<TextView>((R.id.text_person_name_delete))
        val textSurname = holder.itemView.findViewById<TextView>((R.id.text_person_surname_delete))
        textName.text = data.value?.get(position)?.Name?:"nie wyszlo"
        textSurname.text = data.value?.get(position)?.Surname?:"nie wyszlo"
    }

    override fun getItemCount(): Int {
        return data.value?.size ?: 0
    }
}