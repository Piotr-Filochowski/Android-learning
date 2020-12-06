package com.filochowski.smb_cw1.repository

import com.filochowski.smb_cw1.dao.ShoppingListItemDao
import com.filochowski.smb_cw1.entity.ShoppingListItem

class ShoppingListItemRepository(val shoppingListItemDao: ShoppingListItemDao) {

    fun getShoppingItems() = shoppingListItemDao.getStudents()

    fun addShoppingItem(shoppingListItem: ShoppingListItem) = shoppingListItemDao.addStudent(shoppingListItem)

    fun updateShoppingListItem(shoppingListItem: ShoppingListItem) = shoppingListItemDao.updateStudent(shoppingListItem)

    fun deleteShoppingListItem(shoppingListItem: ShoppingListItem) = shoppingListItemDao.deleteStudent(shoppingListItem)

    fun removeAll() = shoppingListItemDao.deleteAll()
}