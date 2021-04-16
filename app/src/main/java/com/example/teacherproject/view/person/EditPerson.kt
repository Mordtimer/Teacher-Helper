package com.example.teacherproject.view.person

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
import com.example.teacherproject.R
import com.example.teacherproject.viewModel.person.EditPersonViewModel

class EditPerson : Fragment() {

    companion object {
        fun newInstance() = EditPerson()
    }
    private val args: EditPersonArgs by navArgs()
    private lateinit var _viewModel: EditPersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_person_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = ViewModelProvider(this).get(EditPersonViewModel::class.java)

        _viewModel.getPerson(args.personID).observe(viewLifecycleOwner, Observer{
            view.findViewById<EditText>(R.id.edit_text_person_name).setText(it.Name)
            view.findViewById<EditText>(R.id.edit_text_person_surname).setText(it.Surname)
            view.findViewById<EditText>(R.id.edit_text_person_email).setText(it.Email)
        })

        view.findViewById<Button>(R.id.button_save_edited_person).setOnClickListener {
            _viewModel.update(args.personID,
            view.findViewById<EditText>(R.id.edit_text_person_name).text.toString(),
            view.findViewById<EditText>(R.id.edit_text_person_surname).text.toString(),
            view.findViewById<EditText>(R.id.edit_text_person_email).text.toString())
            findNavController().popBackStack();
        }
    }

}