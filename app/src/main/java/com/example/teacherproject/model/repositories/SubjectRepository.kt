package com.example.teacherproject.model.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.teacherproject.model.StudentSubjectModelDao
import com.example.teacherproject.model.SubjectModel
import com.example.teacherproject.model.SubjectModelDao

class SubjectRepository (private val subjectModelDao: SubjectModelDao) {

    val readAll: LiveData<List<SubjectModel>> = subjectModelDao.allSubjects()

    fun findSubjectById(id: Int): LiveData<SubjectModel> = subjectModelDao.findSubjectById(id)

    fun findSubjectByGradeId(gradeID: Int): LiveData<SubjectModel> = subjectModelDao.findSubjectByGradeID(gradeID)

    suspend fun delete(subject:SubjectModel) = subjectModelDao.removeSubject(subject)

    suspend fun update(subject: SubjectModel) = subjectModelDao.update(subject)

    suspend fun add(subject:SubjectModel) {
        subjectModelDao.addSubject(subject)
    }

}