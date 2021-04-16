package com.example.teacherproject.viewModel.grade

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherproject.model.GradeModel
import com.example.teacherproject.model.MyDatabase
import com.example.teacherproject.model.repositories.GradeRepository
import kotlinx.coroutines.launch

class AddGradeViewModel(application: Application) : AndroidViewModel(application) {

    val gradeRepository: GradeRepository


    init{
        gradeRepository = GradeRepository(MyDatabase.getDatabase(application).gradeModelDao())
    }

    fun addGrade(grade : GradeModel){
    viewModelScope.launch{
            gradeRepository.add(grade)
        }
    }
}