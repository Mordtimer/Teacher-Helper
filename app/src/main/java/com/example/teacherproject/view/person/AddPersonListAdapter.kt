package com.example.teacherproject.view.person

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.R
import com.example.teacherproject.model.PersonModel
import com.example.teacherproject.model.repositories.StudentSubjectRepository
import com.example.teacherproject.viewModel.person.AddPersonListViewModel

class AddPersonListAdapter(var data: LiveData<List<PersonModel>>,
                           var viewModel: AddPersonListViewModel): RecyclerView.Adapter<AddPersonListAdapter.Holder>() {

    lateinit var context: Context

    class Holder(view: View, data: LiveData<List<PersonModel>>, viewModel: AddPersonListViewModel) : RecyclerView.ViewHolder(view) {
        init {
            /*
            view.setOnClickListener {
                val position: Int = adapterPosition

                        data.value?.get(position)?.personID ?: 0,
                        viewModel.SubjectID)
                findNavController(view).navigate(action)
            }*/

            view.findViewById<Button>(R.id.button_add_student_from_list).setOnClickListener {
                viewModel.addPersonToSubject(data.value?.get(adapterPosition)?.personID?:0,viewModel.SubjectID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.row_add_student, parent, false) as View
        return Holder(view, data, viewModel)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val textName = holder.itemView.findViewById<TextView>((R.id.text_person_add_name))
        val textSurname = holder.itemView.findViewById<TextView>((R.id.text_person_add_surname))
        textName.text = data.value?.get(position)?.Name
        textSurname.text = data.value?.get(position)?.Surname
    }

    override fun getItemCount(): Int {
        return data.value?.size ?: 0
    }
}