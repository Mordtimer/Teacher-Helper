package com.example.teacherproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject_table")
class SubjectModel(@PrimaryKey(autoGenerate = true) @ColumnInfo(name="id", index=true)  var subjectID:  Int,
                                                                            var Name: String) {

}