package com.example.teacherproject.viewModel.grade

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherproject.model.GradeModel
import com.example.teacherproject.model.MyDatabase
import com.example.teacherproject.model.repositories.GradeRepository
import kotlinx.coroutines.launch

class EditGradeViewModel(application: Application) : AndroidViewModel(application) {

    val _gradeRepository: GradeRepository = GradeRepository(MyDatabase.getDatabase(application).gradeModelDao())
    private lateinit var _currentGrade: LiveData<GradeModel>


    fun update(grade: GradeModel) {
        viewModelScope.launch {
            _gradeRepository.update(grade)
        }
    }

    fun getGrade(gradeID: Int): LiveData<GradeModel>{
        _currentGrade = _gradeRepository.findGradeById(gradeID)
        return _currentGrade
    }


}