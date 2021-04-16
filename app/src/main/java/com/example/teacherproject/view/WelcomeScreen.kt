package com.example.teacherproject.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.teacherproject.R
import com.example.teacherproject.view.subject.SubjectArgs
import com.example.teacherproject.viewModel.WelcomeScreenViewModel

class WelcomeScreen : Fragment() {


    companion object {
        fun newInstance() = WelcomeScreen()
    }

    private lateinit var viewModel: WelcomeScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.welcome_screen_fragment, container, false)
        view.findViewById<Button>(R.id.button_show_report).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_welcomeScreen_to_report)}
        view.findViewById<Button>(R.id.button_show_subjects).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_welcomeScreen_to_subjectList)}
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_show_report).setOnClickListener {
            val action = WelcomeScreenDirections.actionWelcomeScreenToReport()
            findNavController().navigate(action)
        }
        view.findViewById<Button>(R.id.button_show_subjects).setOnClickListener {
            val action = WelcomeScreenDirections.actionWelcomeScreenToSubjectList()
            findNavController().navigate(action)
        }
        view.findViewById<Button>(R.id.button_all_students).setOnClickListener{
            val action = WelcomeScreenDirections.actionWelcomeScreenToStudentAll()
            findNavController().navigate(action)
        }
    }
}