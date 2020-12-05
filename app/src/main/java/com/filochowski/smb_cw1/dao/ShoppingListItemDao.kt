package com.filochowski.smb_cw1.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.filochowski.smb_cw1.entity.ShoppingListItem

@Dao
interface ShoppingListItemDao {

    @Query("SELECT * FROM ShoppingListItem")
    fun getStudents(): LiveData<List<ShoppingListItem>>

    @Insert
    fun addStudent(shoppingListItem: ShoppingListItem)

    @Update
    fun updateStudent(shoppingListItem: ShoppingListItem)

    @Delete
    fun deleteStudent(shoppingListItem: ShoppingListItem)

    @Query("DELETE FROM ShoppingListItem")
    fun deleteAll()
}