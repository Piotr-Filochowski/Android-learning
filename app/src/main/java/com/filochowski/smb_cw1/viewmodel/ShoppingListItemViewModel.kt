package com.filochowski.smb_cw1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.filochowski.smb_cw1.db.ShoppingListItemDb

class ShoppingListItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: com.filochowski.smb_cw1.repository.ShoppingListItemRepository
    val allStudens: LiveData<List<com.filochowski.smb_cw1.entity.ShoppingListItem>>

    init {
        repo = com.filochowski.smb_cw1.repository.ShoppingListItemRepository(
            ShoppingListItemDb.getDatabase(application)!!.getStudentDao()
        )
        allStudens = repo.getStudents()
    }
    
    fun getStudents() = repo.getStudents()

    fun addStudent(shoppingListItem: com.filochowski.smb_cw1.entity.ShoppingListItem) = repo.addStudent(shoppingListItem)

    fun updateStudent(shoppingListItem: com.filochowski.smb_cw1.entity.ShoppingListItem) = repo.updateStudent(shoppingListItem)

    fun deleteStudent(shoppingListItem: com.filochowski.smb_cw1.entity.ShoppingListItem) = repo.deleteStudent(shoppingListItem)

    fun removeAll() = repo.removeAll()
}