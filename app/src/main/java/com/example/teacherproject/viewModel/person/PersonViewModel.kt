package com.example.teacherproject.viewModel.person

import android.app.Application
import androidx.lifecycle.*
import com.example.teacherproject.model.MyDatabase
import com.example.teacherproject.model.PersonModel
import com.example.teacherproject.model.SubjectModel
import com.example.teacherproject.model.repositories.PersonRepository
import com.example.teacherproject.model.repositories.SubjectRepository
import com.example.teacherproject.model.repositories.StudentSubjectRepository

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    private val _subjectRepository: SubjectRepository = SubjectRepository(MyDatabase.getDatabase(application).subjectModelDao())
    private val _personRepository: PersonRepository = PersonRepository(MyDatabase.getDatabase(application).personModelDao())
    private val _studentSubjectRepository: StudentSubjectRepository = StudentSubjectRepository(MyDatabase.getDatabase(application).studentSubjectModelDao())
    private lateinit var _currentSubject: LiveData<SubjectModel>
    private lateinit var _currentPerson: LiveData<PersonModel>
    private lateinit var _personSubjects: LiveData<List<SubjectModel>>
    init {
        /*
        _currentPerson = _personRepository.findPersonById(1)
        _currentSubject = _subjectRepository.findSubjectById(1)
        _personSubjects = _studentSubjectRepository.findSubjectsForStudent(1)
         */
    }

    val currentSubject: LiveData<SubjectModel>
        get() = _currentSubject

    val currentPerson: LiveData<PersonModel>
        get() = _currentPerson

    val personSubjects: LiveData<List<SubjectModel>>
        get() = _personSubjects

    fun setCurrentState(personId: Int, subjectId: Int){
        _currentPerson = _personRepository.findPersonById(personId)
        _currentSubject = _subjectRepository.findSubjectById(subjectId)
        _personSubjects = _studentSubjectRepository.findSubjectsForStudent(personId)
    }

}