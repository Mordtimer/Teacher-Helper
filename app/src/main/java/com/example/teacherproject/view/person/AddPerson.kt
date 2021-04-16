package com.example.teacherproject.view.person

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.teacherproject.R
import com.example.teacherproject.viewModel.person.AddPersonViewModel

class AddPerson : Fragment() {

    companion object {
        fun newInstance() = AddPerson()
    }

    private lateinit var _viewModel: AddPersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewModel = ViewModelProvider(this).get(AddPersonViewModel::class.java)
        return inflater.inflate(R.layout.add_person_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_add_new_student_confirm).setOnClickListener {
            val name = view.findViewById<EditText>(R.id.edit_text_add_student_name)?.text.toString()
            val surname = view.findViewById<EditText>(R.id.edit_text_add_student_surname)?.text.toString()
            val email = view.findViewById<EditText>(R.id.edit_text_add_student_email)?.text.toString()
            _viewModel.addPerson(name, surname, email)
            findNavController().popBackStack()
        }
    }
}