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
import com.example.teacherproject.R
import com.example.teacherproject.viewModel.subject.AddSubjectViewModel

class AddSubject : Fragment() {

    companion object {
        fun newInstance() = AddSubject()
    }
    private lateinit var _viewModel: AddSubjectViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_subject_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = ViewModelProvider(this).get(AddSubjectViewModel::class.java)

        view.findViewById<Button>(R.id.button_add_subject).setOnClickListener{
            _viewModel.addSubject(view.findViewById<TextView>(R.id.text_add_subject).text.toString())
            findNavController().popBackStack()

        }
    }
}