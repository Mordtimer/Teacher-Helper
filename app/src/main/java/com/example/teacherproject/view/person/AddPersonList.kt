package com.example.teacherproject.view.person

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
import com.example.teacherproject.viewModel.person.AddPersonListViewModel

class AddPersonList : Fragment() {

    companion object {
        fun newInstance() = AddPersonList()
    }

    private lateinit var _viewManager: LinearLayoutManager
    private lateinit var _adapter: AddPersonListAdapter
    private val args: AddPersonListArgs by navArgs()

    private lateinit var _viewModel: AddPersonListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewModel = ViewModelProvider(this).get(AddPersonListViewModel::class.java)
        _viewModel.setSubject(args.SubjectID)
        _viewManager = LinearLayoutManager(requireContext())
        _adapter = AddPersonListAdapter(_viewModel.getList(args.SubjectID), _viewModel)
        _viewModel.allStudentsNotOnThisSubject.observe(viewLifecycleOwner, Observer{_adapter.notifyDataSetChanged()
        })
        return inflater.inflate(R.layout.add_student_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rec_view_person_add_from_list)
                .apply {
                    adapter = _adapter
                    layoutManager = _viewManager
                }
    }
}