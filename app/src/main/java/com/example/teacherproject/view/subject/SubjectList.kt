package com.example.teacherproject.view.subject

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
import com.example.teacherproject.viewModel.subject.SubjectListViewModel

class SubjectList : Fragment() {

    companion object {
        fun newInstance() = SubjectList()
    }

    private lateinit var _recyclerView: RecyclerView
    private lateinit var _viewManager: RecyclerView.LayoutManager
    private lateinit var _viewModel: SubjectListViewModel
    private lateinit var _adapter: SubjectListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tmpView = inflater.inflate(R.layout.subject_list_fragment, container, false)
        _viewModel = ViewModelProvider(this).get(SubjectListViewModel::class.java)
        _viewManager = LinearLayoutManager(requireContext())
        _adapter = SubjectListAdapter(_viewModel.listOfSubjects)
        _viewModel.listOfSubjects.observe(viewLifecycleOwner, Observer { _adapter.notifyDataSetChanged() })

        tmpView.findViewById<Button>(R.id.button_add_subject_from_list).setOnClickListener{
            val action = SubjectListDirections.actionSubjectListToAddSubject()
            findNavController().navigate(action)
        }
        return tmpView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rec_view_subject)
                .apply {
                    adapter = _adapter
                    layoutManager = _viewManager
                }
    }

}