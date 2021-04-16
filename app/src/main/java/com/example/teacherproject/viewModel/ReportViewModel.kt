package com.example.teacherproject.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.teacherproject.model.GradeModel
import com.example.teacherproject.model.MyDatabase
import com.example.teacherproject.model.PersonModel
import com.example.teacherproject.model.SubjectModel
import com.example.teacherproject.model.repositories.GradeRepository
import com.example.teacherproject.model.repositories.PersonRepository
import com.example.teacherproject.model.repositories.SubjectRepository

class ReportViewModel(application: Application) : AndroidViewModel(application) {

    private val _gradeRepository = GradeRepository(MyDatabase.getDatabase(application).gradeModelDao())
    private val _personRepository = PersonRepository(MyDatabase.getDatabase(application).personModelDao())
    private val _subjectRepository = SubjectRepository(MyDatabase.getDatabase(application).subjectModelDao())
    lateinit var currentOwner: LiveData<PersonModel>
    lateinit var currentSubject: LiveData<SubjectModel>
    private lateinit var _myList: LiveData<List<GradeModel>>

    val gradeList: LiveData<List<GradeModel>>
    get()=_myList

    fun createReport(date: String): LiveData<List<GradeModel>>{
        _myList = _gradeRepository.findGradesByDate(date)
        return _myList
    }

    fun findGradeOwnerByID(gradeID: Int): LiveData<PersonModel>{
        val tmp =  _personRepository.findPersonByGrade(gradeID)
        currentOwner = tmp
        return tmp
    }

    fun findGradeSubjectByID(gradeID: Int): LiveData<SubjectModel>{
        val tmp = _subjectRepository.findSubjectByGradeId(gradeID)
        currentSubject = tmp
        return tmp
    }

}