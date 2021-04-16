package com.example.teacherproject.viewModel.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherproject.model.MyDatabase
import com.example.teacherproject.model.PersonModel
import com.example.teacherproject.model.StudentSubjectModel
import com.example.teacherproject.model.SubjectModel
import com.example.teacherproject.model.repositories.PersonRepository
import com.example.teacherproject.model.repositories.SubjectRepository
import com.example.teacherproject.model.repositories.StudentSubjectRepository
import kotlinx.coroutines.launch

class AddPersonListViewModel(application: Application) : AndroidViewModel(application) {

    private val _personRepositry: PersonRepository
    private val _subjectRepository: SubjectRepository
    private lateinit var _subject: LiveData<SubjectModel>
    private lateinit var _person: LiveData<PersonModel>
    private val _studentSubjectRepository: StudentSubjectRepository
    private lateinit var _personList: LiveData<List<PersonModel>>
    private var _personID: Int = 1
    private var _subjectID: Int = 1

    init {
        _personRepositry = PersonRepository(MyDatabase.getDatabase(application).personModelDao())
        _subjectRepository = SubjectRepository(MyDatabase.getDatabase(application).subjectModelDao())
        _studentSubjectRepository = StudentSubjectRepository(MyDatabase.getDatabase(application).studentSubjectModelDao())
    }

    val allStudentsNotOnThisSubject : LiveData<List<PersonModel>>
        get()=_personList

    var PersonID: Int
        get() = _personID
        set(value) {
            _personID = value
        }

    var SubjectID: Int
        get() = _subjectID
        set(value) {
            _subjectID = value
        }

    fun setSubject(subjectID: Int): LiveData<SubjectModel> {
        _subjectID = subjectID
        _subject = _subjectRepository.findSubjectById((subjectID))
        return _subject
    }

    fun setPerson(personID: Int): LiveData<PersonModel> {
        _personID = personID
        _person = _personRepositry.findPersonById(personID)
        return _person
    }

    fun setCurrentState(personID: Int, subjectID: Int){
        setPerson(personID)
        setSubject(subjectID)
    }

    fun getList(subjectID: Int): LiveData<List<PersonModel>> {
        _personList = _studentSubjectRepository.findPeopleNotFromSubject(subjectID)
        return _personList
    }

    fun addPersonToSubject(studentID: Int, subjectID: Int) {
        viewModelScope.launch {

            val tmp = StudentSubjectModel(0, studentID, subjectID)
            _studentSubjectRepository.add(tmp)
        }
    }
}