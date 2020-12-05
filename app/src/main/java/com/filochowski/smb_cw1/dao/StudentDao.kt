package com.filochowski.smb_cw1.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.filochowski.smb_cw1.entity.StudentEntity

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