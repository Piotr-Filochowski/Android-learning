package com.filochowski.smb_cw1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [StudentEntity::class], version = 1)
abstract class StudentDb : RoomDatabase() {

    abstract fun getStudentDao(): StudentDao

    companion object {
        private var instance: StudentDb? = null

        fun getDatabase(context: Context): StudentDb? {
            if (instance != null)
                return instance
            instance = Room.databaseBuilder(
                context,
                StudentDb::class.java,
                "studentDb"
            ).allowMainThreadQueries().build()
            return instance
        }
    }
}