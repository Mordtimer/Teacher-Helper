package com.example.teacherproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName="person_table")

class PersonModel (@PrimaryKey(autoGenerate = true) @ColumnInfo(name="id", index = true)  val personID: Int,
                   @ColumnInfo(name="name" )                                 var Name: String,
                   @ColumnInfo(name="surname" )                              var Surname: String,
                   @ColumnInfo(name="email")                                var Email: String,
                                                                            ) {
}