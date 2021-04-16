package com.example.teacherproject.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "student_subject_table",
        foreignKeys = [
            ForeignKey(
                entity = PersonModel::class,
                parentColumns = ["id"],
                childColumns = ["person_id"],
                onDelete = CASCADE
            ),
            ForeignKey(
                entity = SubjectModel::class,
                parentColumns = ["id"],
                childColumns = ["subject_id"],
                onDelete = CASCADE
            )
        ]
)
    class StudentSubjectModel(@PrimaryKey(autoGenerate = true)  val id:Int,
                                                                val person_id:Int,
                                                                val subject_id:Int
) {}