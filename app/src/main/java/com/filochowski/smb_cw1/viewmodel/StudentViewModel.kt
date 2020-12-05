package com.filochowski.smb_cw1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.filochowski.smb_cw1.db.StudentDb
import com.filochowski.smb_cw1.entity.StudentEntity
import com.filochowski.smb_cw1.repository.StudentRepository

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: StudentRepository
    val allStudens: LiveData<List<StudentEntity>>

    init {
        repo = StudentRepository(
            StudentDb.getDatabase(application)!!.getStudentDao()
        )
        allStudens = repo.getStudents()
    }
    
    fun getStudents() = repo.getStudents()

    fun addStudent(studentEntity: StudentEntity) = repo.addStudent(studentEntity)

    fun updateStudent(studentEntity: StudentEntity) = repo.updateStudent(studentEntity)

    fun deleteStudent(studentEntity: StudentEntity) = repo.deleteStudent(studentEntity)

    fun removeAll() = repo.removeAll()
}