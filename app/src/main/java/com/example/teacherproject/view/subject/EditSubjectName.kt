package com.example.teacherproject.view.subject

import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.teacherproject.viewModel.subject.EditSubjectViewModel
import com.example.teacherproject.R
import com.example.teacherproject.viewModel.subject.EditSubjectNameViewModel

class EditSubjectName : Fragment() {

    companion object {
        fun newInstance() = EditSubjectName()
    }

    private val args: EditSubjectNameArgs by navArgs()
    private lateinit var _viewModel: EditSubjectNameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.edit_subject_name_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = ViewModelProvider(this).get(EditSubjectNameViewModel::class.java)

        _viewModel.getSubject(args.subjectID).observe(viewLifecycleOwner, Observer{
            view.findViewById<EditText>(R.id.text_subject_name_edit).setText(it.Name)
        })

        view.findViewById<Button>(R.id.button_save_subject_name).setOnClickListener{
            _viewModel.update(  args.subjectID,
                                view.findViewById<TextView>(R.id.text_subject_name_edit).text.toString())
            findNavController().popBackStack()
        }
    }
}