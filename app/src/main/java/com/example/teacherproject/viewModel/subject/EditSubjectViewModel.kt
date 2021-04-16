package com.example.teacherproject.viewModel.subject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherproject.model.GradeModel
import com.example.teacherproject.model.MyDatabase
import com.example.teacherproject.model.PersonModel
import com.example.teacherproject.model.SubjectModel
import com.example.teacherproject.model.repositories.GradeRepository
import com.example.teacherproject.model.repositories.PersonRepository
import com.example.teacherproject.model.repositories.StudentSubjectRepository
import com.example.teacherproject.model.repositories.SubjectRepository
import kotlinx.coroutines.launch

class EditSubjectViewModel(application: Application) : AndroidViewModel(application) {

    private val _subjectRepository: SubjectRepository = SubjectRepository(MyDatabase.getDatabase(application).subjectModelDao())
    private lateinit var _subject: LiveData<SubjectModel>
    private var _subjectID: Int = 1
    lateinit var _myList: LiveData<List<GradeModel>>
    private val _gradeRepository: GradeRepository = GradeRepository(MyDatabase.getDatabase(application).gradeModelDao())

    fun getSubject(subjectID: Int): LiveData<SubjectModel>{
        _subjectID = subjectID
        _subject = _subjectRepository.findSubjectById(_subjectID)
        return _subject
    }

    fun deleteSubject(subjectID: Int, name: String){
        viewModelScope.launch {
            val tmp = SubjectModel(subjectID, name)
            _subjectRepository.delete(tmp)
        }
    }

    fun findAllUselessGrades(subjectID: Int):LiveData<List<GradeModel>>{
        _myList = _gradeRepository.findGradesBySubject(subjectID)
        return _myList
    }

    fun deleteGrades(){
        viewModelScope.launch {
            _myList.value?.forEach { el ->
                _gradeRepository.delete(el)
            }
        }
    }
}