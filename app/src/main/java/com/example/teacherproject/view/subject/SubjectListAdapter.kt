package com.example.teacherproject.view.subject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.R
import com.example.teacherproject.model.SubjectModel

class SubjectListAdapter(var data: LiveData<List<SubjectModel>>): RecyclerView.Adapter<SubjectListAdapter.Holder>(){

    lateinit var context: Context
    class Holder(view: View, data: LiveData<List<SubjectModel>>): RecyclerView.ViewHolder(view){
        init{
            view.setOnClickListener{
                val position: Int = adapterPosition
                val action = SubjectListDirections.actionSubjectListToSubject(
                    data.value?.get(position)?.subjectID?:0)
                findNavController(view).navigate(action)
            }
            view.findViewById<ImageButton>(R.id.button_edit_subject).setOnClickListener{
                val action = SubjectListDirections.actionSubjectListToEditSubject(
                        data.value?.get(position)?.subjectID?:1)
                findNavController(view).navigate(action)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.row_subject,parent,false) as View

        return Holder(view, data)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val textSubjectName = holder.itemView.findViewById<TextView>(R.id.text_subject_name)
        textSubjectName.text = data.value?.get(position)?.Name?:""
    }

    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}