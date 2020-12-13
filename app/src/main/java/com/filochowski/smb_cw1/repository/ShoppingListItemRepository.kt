package com.filochowski.smb_cw1.repository

import com.filochowski.smb_cw1.dao.ShoppingListItemDao
import com.filochowski.smb_cw1.entity.ShoppingListItem

class ShoppingListItemRepository(val shoppingListItemDao: ShoppingListItemDao) {

    fun getShoppingItems() = shoppingListItemDao.getAllShoppingListItems()

    fun addShoppingItem(shoppingListItem: ShoppingListItem) = shoppingListItemDao.addShoppingListItem(shoppingListItem)

    fun updateShoppingListItem(shoppingListItem: ShoppingListItem) = shoppingListItemDao.updateShoppingListItem(shoppingListItem)

    fun deleteShoppingListItem(shoppingListItem: ShoppingListItem) = shoppingListItemDao.deleteShoppingListItem(shoppingListItem)

    fun removeAll() = shoppingListItemDao.deleteAll()
}