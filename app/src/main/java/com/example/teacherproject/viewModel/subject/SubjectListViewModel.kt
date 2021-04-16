package com.example.teacherproject.viewModel.subject

import android.app.Application
import androidx.lifecycle.*
import com.example.teacherproject.model.MyDatabase
import com.example.teacherproject.model.SubjectModel
import com.example.teacherproject.model.repositories.SubjectRepository
import kotlinx.coroutines.launch

class SubjectListViewModel(application: Application) : AndroidViewModel(application) {
    private val _myList = MyDatabase.getDatabase(application).subjectModelDao().allSubjects()
    private val subjectRepository = SubjectRepository(MyDatabase.getDatabase(application).subjectModelDao())
    private var _subjectID: Int
    private var _subject: LiveData<SubjectModel>

    init {
        _subjectID = 1
        _subject = subjectRepository.findSubjectById(_subjectID)
    }

    fun setCurrentSubject(id :Int){
        _subjectID = id
        _subject = subjectRepository.findSubjectById(_subjectID)
    }

    val currentSubject: LiveData<SubjectModel>
        get() = _subject

    val listOfSubjects: LiveData<List<SubjectModel>>
        get() = _myList

    fun addSubject(name: String){
        viewModelScope.launch{
            subjectRepository.add(SubjectModel(0, name))
        }
    }
}