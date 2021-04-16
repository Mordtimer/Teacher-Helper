package com.example.teacherproject.view.grade

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.R
import com.example.teacherproject.model.GradeModel

class GradeListAdapter(var data: LiveData<List<GradeModel>>, studentID: Int, subjectID: Int): RecyclerView.Adapter<GradeListAdapter.Holder>()  {

    lateinit var context: Context
    val studentID = studentID
    val subjectID = subjectID

    class Holder(view: View, data: LiveData<List<GradeModel>>, studentID: Int, subjectID: Int): RecyclerView.ViewHolder(view){
        init {
            view.setOnClickListener {
                val position: Int = adapterPosition
                val action = GradeListDirections.actionGradeListToGrade(
                        data.value?.get(position)?.gradeID ?: 0,
                        studentID, subjectID
                )
                findNavController(view).navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.row_grade_simple,parent,false) as View

        return Holder(view, data, studentID, subjectID)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val textGradeValue = holder.itemView.findViewById<TextView>(R.id.text_grade_value)
        val textGradeWeight = holder.itemView.findViewById<TextView>(R.id.text_grade_weight)
        val textGradeCategory = holder.itemView.findViewById<TextView>(R.id.text_grade_category)

        textGradeCategory.text = data.value?.get(position)?.category?:""
        textGradeValue.text = data.value?.get(position)?.value?.toString()?:""
        textGradeWeight.text = data.value?.get(position)?.weight?.toString()?:""
    }

    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}