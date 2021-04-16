package com.example.teacherproject.viewModel.grade

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.example.teacherproject.model.GradeModel
import com.example.teacherproject.model.MyDatabase
import com.example.teacherproject.model.repositories.GradeRepository
import com.example.teacherproject.model.repositories.PersonRepository
import com.example.teacherproject.model.repositories.SubjectRepository
import com.example.teacherproject.model.repositories.StudentSubjectRepository


class GradeListViewModel(application: Application) : AndroidViewModel(application) {
    private var _mainList: LiveData<List<GradeModel>>
    private val _StudentSubjectRepository: StudentSubjectRepository
    private val _subjectRepository: SubjectRepository
    private val _personRepository: PersonRepository
    private val _gradeRepository: GradeRepository = GradeRepository(MyDatabase.getDatabase(application).gradeModelDao())

    init {
        _StudentSubjectRepository = StudentSubjectRepository(MyDatabase.getDatabase(application).studentSubjectModelDao())
        _mainList = _gradeRepository.readAllGradeForStudent(1,1)
        _personRepository = PersonRepository(MyDatabase.getDatabase(application).personModelDao())
        _subjectRepository = SubjectRepository(MyDatabase.getDatabase(application).subjectModelDao())
    }

    val listOfGrades: LiveData<List<GradeModel>>
        get() = _mainList

    fun refreshCurrentState(studentID: Int, subjectID: Int){
        _mainList = _gradeRepository.readAllGradeForStudent(studentID,subjectID)
    }
}