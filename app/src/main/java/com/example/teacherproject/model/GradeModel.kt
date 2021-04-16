package com.example.teacherproject.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "grade_table",
    foreignKeys = [

        ForeignKey(
            entity = PersonModel::class,
            parentColumns = ["id"],
            childColumns = ["person_id"],
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = SubjectModel::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])

class GradeModel(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")   val gradeID: Int,
                 var value: Int,
                 var weight : Int,
                 var category: String,
                 var description: String,
                 val person_id: Int,
                 val subject_id: Int,
                 val date: String) {
}