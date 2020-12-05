package com.filochowski.smb_cw1.repository

import com.filochowski.smb_cw1.dao.StudentDao
import com.filochowski.smb_cw1.entity.StudentEntity

class StudentRepository(val studentDao: StudentDao) {

    fun getStudents() = studentDao.getStudents()

    fun addStudent(studentEntity: StudentEntity) = studentDao.addStudent(studentEntity)

    fun updateStudent(studentEntity: StudentEntity) = studentDao.updateStudent(studentEntity)

    fun deleteStudent(studentEntity: StudentEntity) = studentDao.deleteStudent(studentEntity)

    fun removeAll() = studentDao.deleteAll()
}