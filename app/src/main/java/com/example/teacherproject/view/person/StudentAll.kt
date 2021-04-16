package com.example.teacherproject.view.person

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.R
import com.example.teacherproject.viewModel.person.StudentAllViewModel

class StudentAll : Fragment() {

    companion object {
        fun newInstance() = StudentAll()
    }

    private lateinit var _viewModel: StudentAllViewModel
    private lateinit var _viewManager: LinearLayoutManager
    private lateinit var _adapter: AllPersonListAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _viewModel = ViewModelProvider(this).get(StudentAllViewModel::class.java)
        _viewManager = LinearLayoutManager(requireContext())
        _adapter = AllPersonListAdapter(_viewModel.myList, _viewModel);
        _viewModel.myList.observe(viewLifecycleOwner, Observer { _adapter.notifyDataSetChanged() })
        return inflater.inflate(R.layout.all_students_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.rec_view_all_students)
            .apply {
                adapter = _adapter
                layoutManager = _viewManager
            }
        view.findViewById<Button>(R.id.button_add_new_student).setOnClickListener{
            val action = StudentAllDirections.actionStudentAllToAddPerson()
            findNavController().navigate(action)
        }
    }
}