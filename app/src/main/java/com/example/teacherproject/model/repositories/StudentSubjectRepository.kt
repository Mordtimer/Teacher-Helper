package com.example.teacherproject.model.repositories


import androidx.lifecycle.LiveData
import com.example.teacherproject.model.PersonModel
import com.example.teacherproject.model.StudentSubjectModel
import com.example.teacherproject.model.StudentSubjectModelDao
import com.example.teacherproject.model.SubjectModel

class StudentSubjectRepository(private val studentSubjectModelDao: StudentSubjectModelDao){

    suspend fun showStudentsFromSubject(
        subjectID: Int) = studentSubjectModelDao.findPeopleFromSubject(subjectID)

    fun findPeopleFromSubject(subjectID: Int): LiveData<List<PersonModel>> = studentSubjectModelDao.findPeopleFromSubject(subjectID)

    fun findSubjectsForStudent(studentId: Int): LiveData<List<SubjectModel>> = studentSubjectModelDao.findSubjectsForPerson(studentId)
    
    fun findPeopleNotFromSubject(subjectID: Int): LiveData<List<PersonModel>> = studentSubjectModelDao.findPeopleNotFromSubject(subjectID)

    fun findRelationsByStudentID(studentID: Int): LiveData<List<StudentSubjectModel>> = studentSubjectModelDao.findRelationsByStudentID(studentID)

    suspend fun add(studentSubjectModel: StudentSubjectModel) {
        studentSubjectModelDao.addStudentToSubject(studentSubjectModel)
    }
    suspend fun delete(studentSubjectModel: StudentSubjectModel)
        = studentSubjectModelDao.removeStudentFromSubject(studentSubjectModel)
}