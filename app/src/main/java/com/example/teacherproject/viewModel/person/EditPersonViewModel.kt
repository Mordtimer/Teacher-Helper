package com.example.teacherproject.viewModel.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherproject.model.MyDatabase
import com.example.teacherproject.model.PersonModel
import com.example.teacherproject.model.repositories.GradeRepository
import com.example.teacherproject.model.repositories.PersonRepository
import kotlinx.coroutines.launch

class EditPersonViewModel(application: Application) : AndroidViewModel(application) {
    private val _personRepository: PersonRepository = PersonRepository(MyDatabase.getDatabase(application).personModelDao())
    private lateinit var _person: LiveData<PersonModel>
    private var _personID: Int = 1
    private val _gradeRepository: GradeRepository = GradeRepository(MyDatabase.getDatabase(application).gradeModelDao())

    fun getPerson(personID: Int): LiveData<PersonModel> {
        _personID = personID
        _person = _personRepository.findPersonById(_personID)
        return _person
    }

    fun update(personID: Int, name: String, surname: String, email: String){
        viewModelScope.launch {
            val tmp = PersonModel(personID, name, surname, email)
            _personRepository.update(tmp)
        }
    }
}