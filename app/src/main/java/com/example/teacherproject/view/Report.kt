package com.example.teacherproject.view

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherproject.R
import com.example.teacherproject.viewModel.ReportViewModel
import java.time.LocalDate

class Report : Fragment() {

    companion object {
        fun newInstance() = Report()
    }
    private lateinit var _viewModel: ReportViewModel
    private lateinit var _viewManager: RecyclerView.LayoutManager
    private lateinit var _adapter: ReportAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewModel = ViewModelProvider(this).get(ReportViewModel::class.java)
        _viewManager = LinearLayoutManager(requireContext())
        _viewModel.createReport(LocalDate.now().toString())
        _adapter = ReportAdapter(_viewModel, viewLifecycleOwner)
        _viewModel.gradeList.observe(viewLifecycleOwner, Observer{ _adapter.notifyDataSetChanged()})
        return inflater.inflate(R.layout.raport_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rec_view_report)
                .apply {
                    adapter = _adapter
                    layoutManager = _viewManager
                }
    }
}