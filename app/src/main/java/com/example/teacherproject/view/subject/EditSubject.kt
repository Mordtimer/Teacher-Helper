package com.example.teacherproject.view.subject

import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.teacherproject.viewModel.subject.EditSubjectViewModel
import com.example.teacherproject.R

class EditSubject : Fragment() {

    companion object {
        fun newInstance() = EditSubject()
    }

    private val args: EditSubjectArgs by navArgs()
    private lateinit var _viewModel: EditSubjectViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_subject_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = ViewModelProvider(this).get(EditSubjectViewModel::class.java)

        _viewModel.getSubject(args.subjectID).observe(viewLifecycleOwner, Observer{ z ->
            view.findViewById<TextView>(R.id.text_subject_name_edit).text = z.Name
        })

        view.findViewById<Button>(R.id.button_edit_subject_name).setOnClickListener{
            val action = EditSubjectDirections.actionEditSubjectToEditSubjectName(args.subjectID)
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.button_delete_subject).setOnClickListener{
            _viewModel.findAllUselessGrades(args.subjectID)
            _viewModel.deleteGrades()
            findNavController().popBackStack()
            _viewModel.deleteSubject(args.subjectID,_viewModel.getSubject(args.subjectID).value?.Name?:"")
        }
    }
}