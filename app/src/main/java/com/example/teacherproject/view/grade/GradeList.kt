package com.example.teacherproject.view.grade

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.R
import com.example.teacherproject.viewModel.grade.GradeListViewModel

class GradeList : Fragment() {

    companion object {
        fun newInstance() = GradeList()
    }

    val args: GradeListArgs by navArgs()
    private lateinit var _viewManager: RecyclerView.LayoutManager
    private lateinit var _viewModel: GradeListViewModel
    private lateinit var _adapter: GradeListAdapter


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _viewModel = ViewModelProvider(requireActivity()).get(GradeListViewModel::class.java)
        _viewManager = LinearLayoutManager(requireContext())

        _viewModel.refreshCurrentState(args.studentID, args.subjectID)
        _adapter = GradeListAdapter(_viewModel.listOfGrades, args.studentID, args.subjectID)

        _viewModel.listOfGrades.observe(viewLifecycleOwner, Observer { _adapter.notifyDataSetChanged() })

        return  inflater.inflate(R.layout.grade_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<RecyclerView>(R.id.rec_view_grade)
                .apply {
                    adapter = _adapter
                    layoutManager = _viewManager
                }
    }
}