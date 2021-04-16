package com.example.teacherproject.viewModel.person

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherproject.model.MyDatabase
import com.example.teacherproject.model.PersonModel
import com.example.teacherproject.model.repositories.PersonRepository
import kotlinx.coroutines.launch

class AddPersonViewModel(application: Application) : AndroidViewModel(application) {
    private val _personRepository = PersonRepository(MyDatabase.getDatabase(application).personModelDao())
   /* private val _personList: LiveData<List<PersonModel>>

    init{
        _personList = _personRepository.
    }*/

    fun addPerson(name:String, surname: String, email: String){
        viewModelScope.launch{
            val tmp = PersonModel(0,name,surname,email)
            _personRepository.add(tmp)
        }
    }
}