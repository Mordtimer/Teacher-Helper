package com.example.teacherproject.model

import android.app.Person
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface SubjectModelDao {

    @Query("SELECT * FROM subject_table")
    fun allSubjects(): LiveData<List<SubjectModel>>

    @Query("SELECT * FROM subject_table WHERE id =:id")
    fun findSubjectById(id : Int): LiveData<SubjectModel>

    @Query("SELECT * FROM subject_table WHERE id IN (SELECT subject_id FROM grade_table WHERE id =:gradeID)")
    fun findSubjectByGradeID(gradeID: Int): LiveData<SubjectModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun addSubject(subject: SubjectModel)

    @Delete
    suspend fun removeSubject(subject: SubjectModel)

    @Update
    suspend fun update(subject: SubjectModel)
}