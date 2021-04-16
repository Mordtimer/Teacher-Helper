package com.example.teacherproject.viewModel.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherproject.model.*
import com.example.teacherproject.model.repositories.GradeRepository
import com.example.teacherproject.model.repositories.PersonRepository
import com.example.teacherproject.model.repositories.StudentSubjectRepository
import com.example.teacherproject.model.repositories.SubjectRepository
import kotlinx.coroutines.launch

class StudentAllViewModel(application: Application) : AndroidViewModel(application) {

    private var _mainList: LiveData<List<PersonModel>>
    private val _subjectRepository =
        SubjectRepository(MyDatabase.getDatabase(application).subjectModelDao())
    private val _personRepository =
        PersonRepository(MyDatabase.getDatabase(application).personModelDao())
    private val _gradeRepository =
        GradeRepository(MyDatabase.getDatabase(application).gradeModelDao())
    private val _studentSubjectRepository =
        StudentSubjectRepository(MyDatabase.getDatabase(application).studentSubjectModelDao())
    private lateinit var _currentPerson: LiveData<PersonModel>
    private var _currentPersonID: Int? = null

    private var _currentSubject: LiveData<SubjectModel>
    private lateinit var _uselessGrades: LiveData<List<GradeModel>>
    private lateinit var _uselessStudentSubjects: LiveData<List<StudentSubjectModel>>
    private lateinit var _personSubjects: LiveData<List<SubjectModel>>

    init {
        _mainList = _personRepository.readAll
        _currentSubject = _subjectRepository.findSubjectById(0)
    }

    var currentPersonID: Int
        get() = _currentPersonID ?: 0
        set(value) {
            _currentPersonID = value
        }

    val myList: LiveData<List<PersonModel>>
        get() = _mainList

    fun setCurrentSubject(id: Int) {
        _currentSubject = _subjectRepository.findSubjectById(id)
    }


    fun addPerson(person: PersonModel) {
        viewModelScope.launch {
            _personRepository.add(person)
        }
    }

    fun removePersonFromSubject(studentID: Int): LiveData<List<StudentSubjectModel>> {
        _uselessStudentSubjects = _studentSubjectRepository.findRelationsByStudentID(studentID)
        return _uselessStudentSubjects
    }

    fun removePersonFromGrade(studentID: Int): LiveData<List<GradeModel>> {
        _uselessGrades = _gradeRepository.readAllGradeForStudent(studentID)
        return _uselessGrades
    }

    fun killRelations(
        studentSubjectModel: LiveData<List<StudentSubjectModel>>,
        grades: LiveData<List<GradeModel>>
    ) {
        viewModelScope.launch {
            studentSubjectModel.value?.forEach {
                _studentSubjectRepository.delete(it)
            }
            grades.value?.forEach {
                _gradeRepository.delete(it)
            }
        }


    }

    fun killPerson(person: PersonModel) {
        viewModelScope.launch {

            _personRepository.delete(person)
        }
    }
}