package com.example.teacherproject.view.person

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.viewModel.person.PersonListViewModel
import com.example.teacherproject.R

class PersonList : Fragment() {

    companion object {
        fun newInstance() = PersonList()
    }

    val passedArgs: PersonListArgs by navArgs()
    private lateinit var _viewModel: PersonListViewModel
    private lateinit var _viewManager: RecyclerView.LayoutManager
    private lateinit var _adapter: PersonListAdapter
    private lateinit var _recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val tmpView = inflater.inflate(R.layout.person_list_fragment, container, false)
        _viewModel = ViewModelProvider(this).get(PersonListViewModel::class.java)
        _viewModel.setSubject(passedArgs.subjectID)
        _viewManager = LinearLayoutManager(requireContext())
        _adapter = PersonListAdapter(_viewModel.allStudentsFromSubject, passedArgs.subjectID)
        _viewModel.allStudentsFromSubject.observe(viewLifecycleOwner, Observer { _adapter.notifyDataSetChanged() })
        return tmpView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rec_view_person)
                .apply {
                    adapter = _adapter
                    layoutManager = _viewManager
                }
    }

}
