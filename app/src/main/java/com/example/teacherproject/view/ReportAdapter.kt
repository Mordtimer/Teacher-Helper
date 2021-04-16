package com.example.teacherproject.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.R
import com.example.teacherproject.model.GradeModel
import com.example.teacherproject.viewModel.ReportViewModel

class  ReportAdapter(private val viewModel: ReportViewModel, val viewLifecycleOwner: LifecycleOwner): RecyclerView.Adapter<ReportAdapter.Holder>()  {

    lateinit var context: Context

    class Holder(view: View, viewModel: ReportViewModel): RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                val gradeID: Int = viewModel.gradeList.value?.get(adapterPosition)?.gradeID ?: 1
                val personID: Int = viewModel.gradeList.value?.get(adapterPosition)?.person_id ?: 1
                val subjectID: Int =
                    viewModel.gradeList.value?.get(adapterPosition)?.subject_id ?: 1
                val action = ReportDirections.actionReportToGrade(
                    gradeID, subjectID, personID)

                findNavController(view).navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.row_grade_extended,parent,false) as View

        return Holder(view, viewModel)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val gradeID = viewModel.gradeList.value?.get(position)?.gradeID?:1
        val textGradeValue = holder.itemView.findViewById<TextView>(R.id.text_report_grade_value)
        val textGradeWeight = holder.itemView.findViewById<TextView>(R.id.text_report_grade_weight)
        val textGradeCategory = holder.itemView.findViewById<TextView>(R.id.text_report_grade_category)
        val textName = holder.itemView.findViewById<TextView>(R.id.text_report_name)
        val textGradeDate = holder.itemView.findViewById<TextView>(R.id.text_report_grade_date)
        val textSubject = holder.itemView.findViewById<TextView>(R.id.text_report_subject)

        textGradeCategory.text = viewModel.gradeList.value?.get(position)?.category?:""
        textGradeValue.text = viewModel.gradeList.value?.get(position)?.value?.toString()?:""
        textGradeWeight.text = viewModel.gradeList.value?.get(position)?.weight?.toString()?:""
        textGradeDate.text = viewModel.gradeList.value?.get(position)?.date?:""
        textName.text = viewModel.findGradeOwnerByID(gradeID).value?.Surname
        textSubject.text = viewModel.findGradeSubjectByID(gradeID).value?.Name

        viewModel.findGradeOwnerByID(gradeID).observe(viewLifecycleOwner, Observer{
            textName.text = it.Surname
        })
        viewModel.findGradeSubjectByID(gradeID).observe(viewLifecycleOwner, Observer{
            textSubject.text = it.Name
        })
    }

    override fun getItemCount(): Int {
        return viewModel.gradeList.value?.size?:0
    }
}