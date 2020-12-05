package com.filochowski.smb_cw1.repository

import com.filochowski.smb_cw1.dao.ShoppingListItemDao
import com.filochowski.smb_cw1.entity.ShoppingListItem

class ShoppingListItemRepository(val shoppingListItemDao: ShoppingListItemDao) {

    fun getStudents() = shoppingListItemDao.getStudents()

    fun addStudent(shoppingListItem: ShoppingListItem) = shoppingListItemDao.addStudent(shoppingListItem)

    fun updateStudent(shoppingListItem: ShoppingListItem) = shoppingListItemDao.updateStudent(shoppingListItem)

    fun deleteStudent(shoppingListItem: ShoppingListItem) = shoppingListItemDao.deleteStudent(shoppingListItem)

    fun removeAll() = shoppingListItemDao.deleteAll()
}