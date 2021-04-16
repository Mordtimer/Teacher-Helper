package com.example.teacherproject.model

import android.app.Person
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.teacherproject.view.grade.Grade

@Dao
interface GradeModelDao {
    @Query("SELECT * FROM grade_table WHERE person_id =:personID AND subject_id =:subjectID")
    fun allStudentGrades(personID: Int, subjectID: Int): LiveData<List<GradeModel>>

    @Query("SELECT * FROM grade_table WHERE person_id =:personID")
    fun completelyAllStudentGrades(personID: Int): LiveData<List<GradeModel>>

    @Query("Select * from grade_table where id = :gradeID")
    fun findGradeById(gradeID: Int): LiveData<GradeModel>

    @Query("SELECT * FROM grade_table WHERE date =:gradeDate")
    fun findGradesByDate(gradeDate: String): LiveData<List<GradeModel>>

    @Query("SELECT * FROM grade_table WHERE subject_id =:subjectID")
    fun findGradesBySubject(subjectID: Int): LiveData<List<GradeModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGrade(grade: GradeModel)

    @Delete
    suspend fun deleteGrade(grade: GradeModel)

    @Update
    suspend fun update(grade: GradeModel)
}