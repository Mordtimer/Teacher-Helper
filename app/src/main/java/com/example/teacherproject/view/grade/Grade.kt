package com.example.teacherproject.view.grade

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.R
import com.example.teacherproject.viewModel.grade.GradeViewModel


class Grade : Fragment() {

    lateinit var _viewManager: RecyclerView.LayoutManager
    lateinit var _viewModel: GradeViewModel
    val args: GradeArgs by navArgs()

    companion object {
        fun newInstance() = Grade()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tmpView = inflater.inflate(R.layout.grade_fragment, container, false)
        _viewModel = ViewModelProvider(this).get(GradeViewModel::class.java)
        _viewManager = LinearLayoutManager(requireContext())

        _viewModel.getGrade(args.gradeID).observe(viewLifecycleOwner, Observer{
            tmpView.findViewById<TextView>(R.id.text_interface_grade).text = it.value.toString()
            tmpView.findViewById<TextView>(R.id.text_interface_grade_weight).text = it.weight.toString()
            tmpView.findViewById<TextView>(R.id.grade_category_desc).text = it.category
            tmpView.findViewById<TextView>(R.id.text_interface_grade_comment).text = it.description
        })
        tmpView.findViewById<Button>(R.id.button_delete_grade).setOnClickListener{
            _viewModel.deleteGrade()
            findNavController().popBackStack()
        }
        tmpView.findViewById<Button>(R.id.button_edit_grade).setOnClickListener {
            val action =
                GradeDirections.actionGradeToEditGrade(args.gradeID, args.subjectID, args.studentID)
            findNavController().navigate(action)
        }
        return tmpView
    }
}