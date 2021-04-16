package com.example.teacherproject.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonModel::class,GradeModel::class,SubjectModel::class, StudentSubjectModel::class], version = 1, exportSchema = false)
abstract class MyDatabase: RoomDatabase() {

    abstract fun personModelDao():  PersonModelDao
    abstract fun gradeModelDao(): GradeModelDao
    abstract fun subjectModelDao(): SubjectModelDao
    abstract fun studentSubjectModelDao(): StudentSubjectModelDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null)
                return tempInstance
            else
                synchronized(this)
                {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "my_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
        }
    }
}
