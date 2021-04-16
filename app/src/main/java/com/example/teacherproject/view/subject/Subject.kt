package com.example.teacherproject.view.subject

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.R
import com.example.teacherproject.viewModel.subject.SubjectViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.lifecycle.Observer

class Subject : Fragment() {

    companion object {
        fun newInstance() = Subject()
    }

    private val args: SubjectArgs by navArgs()
    private lateinit var _viewManager: RecyclerView.LayoutManager
    private lateinit var _viewModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.subject_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewManager = LinearLayoutManager(requireContext())
        _viewModel = ViewModelProvider(this).get(SubjectViewModel(requireActivity().application)::class.java)
        _viewModel.setCurrentSubject(args.subjectID)

        _viewModel.currentSubject.observe(viewLifecycleOwner, Observer{z ->
            view.findViewById<TextView>(R.id.text_subject_name).text = z.Name
        })



        view.findViewById<Button>(R.id.button_add_participants).setOnClickListener{
            val action = SubjectDirections.actionSubjectToAddPersonList(args.subjectID)
            findNavController().navigate(action)
        }
        view.findViewById<Button>(R.id.button_show_participants).setOnClickListener{
            val action = SubjectDirections.actionSubjectToPersonList(_viewModel.currentSubject.value?.subjectID?:0)
            findNavController().navigate(action)
        }
    }
}