package com.filochowski.smb_cw1

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {

    @Query("SELECT * FROM STUDENT")
    fun getStudents(): LiveData<List<StudentEntity>>

    @Insert
    fun addStudent(studentEntity: StudentEntity)

    @Update
    fun updateStudent(studentEntity: StudentEntity)

    @Delete
    fun deleteStudent(studentEntity: StudentEntity)

    @Query("DELETE FROM STUDENT")
    fun deleteAll()
}