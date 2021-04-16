package com.example.teacherproject.view.person

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.viewModel.person.PersonViewModel
import com.example.teacherproject.R

class Person : Fragment() {

    companion object {
        fun
                newInstance() = Person()
    }

    val args: PersonArgs by navArgs()
    lateinit var _viewManager: RecyclerView.LayoutManager
    lateinit var _viewModel: PersonViewModel
    lateinit var viewModel: PersonViewModel
    lateinit var _spinner: Spinner
    var selectedSubjectId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.person_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewManager = LinearLayoutManager(requireContext())
        _viewModel = ViewModelProvider(this).get(PersonViewModel(requireActivity().application)::class.java)
        _viewModel.setCurrentState(args.personID, args.subjectID)
        val _spinner: Spinner = view.findViewById(R.id.spinner_subjects)
        var inputSubjectPosition = 0

        _viewModel.personSubjects.observe(viewLifecycleOwner, Observer { spinnerData ->
            val list = arrayListOf<String>()
            spinnerData.forEach { x ->
                list.add(x.Name)
                if(x.subjectID == args.subjectID)
                    inputSubjectPosition = list.indexOf(x.Name)
            }
            val spinnerAdapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_spinner_item, list
            )
            _spinner.adapter = spinnerAdapter
            _spinner.setSelection(inputSubjectPosition)
        })

        _viewModel.currentPerson.observe(viewLifecycleOwner, Observer{z ->
            view.findViewById<TextView>(R.id.text_subject_name_edit).text = "${z.Name} ${z.Surname}"
            view.findViewById<TextView>(R.id.text_person_mail_interface).text = "${z.Email}"
        })
        view.findViewById<Button>(R.id.button_add_grade_from_interface).setOnClickListener{

            _viewModel.personSubjects.value?.forEach {
                if (it.Name ==  _spinner.selectedItem.toString()) {
                    Log.d("If checkout","worked ${selectedSubjectId}")
                    selectedSubjectId = it.subjectID
                }
            }
            Log.d("SelectedItem", "${_spinner.selectedItem.toString()}  ${selectedSubjectId}")
            val action = PersonDirections.actionPersonToAddGrade(
                    args.personID,
                    selectedSubjectId)
            findNavController().navigate(action)
        }
        view.findViewById<Button>(R.id.button_show_person_grade_interface).setOnClickListener{
            _viewModel.personSubjects.value?.forEach {
                if (it.Name ==  _spinner.selectedItem.toString()) {
                    Log.d("If checkout","worked ${selectedSubjectId}")
                    selectedSubjectId = it.subjectID
                }
            }
            Log.d("SelectedItem", "${_spinner.selectedItem.toString()}  ${selectedSubjectId}")
            val action = PersonDirections.actionPersonToGradeList(args.personID, selectedSubjectId)
            findNavController().navigate(action)
        }
    }
}