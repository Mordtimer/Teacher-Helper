package com.example.teacherproject.view.grade

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.teacherproject.R
import com.example.teacherproject.model.GradeModel
import com.example.teacherproject.viewModel.grade.AddGradeViewModel
import java.time.LocalDate

class AddGrade : Fragment() {

    companion object {
        fun newInstance() = AddGrade()
    }

    val args: AddGradeArgs by navArgs()
    private lateinit var _viewModel: AddGradeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_grade_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _viewModel = ViewModelProvider(this).get(AddGradeViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_add_new_grade).setOnClickListener {
            val value = view.findViewById<EditText>(R.id.edit_text_interface_add_grade).text.toString().toInt()
            val weight = view.findViewById<EditText>(R.id.edit_text_interface_add_grade_weight).text.toString().toInt()
            val category = view.findViewById<EditText>(R.id.edit_text_interface_add_grade_category).text.toString()
            val description = view.findViewById<EditText>(R.id.text_interface_add_grade_comment).text.toString()
            val grade = GradeModel(0, value, weight, category, description, args.studentId, args.subjectId,LocalDate.now().toString())
            _viewModel.addGrade(grade)
            findNavController().popBackStack()
        }

    }
}