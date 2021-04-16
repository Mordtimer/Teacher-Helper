package com.example.teacherproject.viewModel.subject

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.teacherproject.model.MyDatabase
import com.example.teacherproject.model.SubjectModel
import com.example.teacherproject.model.repositories.SubjectRepository
import kotlinx.coroutines.launch

class SubjectViewModel(application: Application) : AndroidViewModel(application) {

    private val _subjectRepository: SubjectRepository = SubjectRepository(MyDatabase.getDatabase(application).subjectModelDao())
    private var _subjectID: Int = 1
    private var _subject: LiveData<SubjectModel>

    init {
        _subject = _subjectRepository.findSubjectById(_subjectID)
    }

    fun setCurrentSubject(id :Int){
        _subjectID = id
        _subject = _subjectRepository.findSubjectById(_subjectID)
    }

    fun getSubject(subjectID: Int): LiveData<SubjectModel>{
        _subjectID = subjectID
        _subject = _subjectRepository.findSubjectById(_subjectID)
        return _subject
    }

    val currentSubject: LiveData<SubjectModel>
        get() = _subject
}