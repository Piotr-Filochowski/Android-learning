package com.filochowski.smb_cw1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.filochowski.smb_cw1.db.ShoppingListItemDb

class ShoppingListItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: com.filochowski.smb_cw1.repository.ShoppingListItemRepository
    val allItems: LiveData<List<com.filochowski.smb_cw1.entity.ShoppingListItem>>

    init {
        repo = com.filochowski.smb_cw1.repository.ShoppingListItemRepository(
            ShoppingListItemDb.getDatabase(application)!!.getShoppingListItemDao()
        )
        allItems = repo.getShoppingItems()
    }
    
    fun getShoppingItems() = repo.getShoppingItems()

    fun addShoppingItem(shoppingListItem: com.filochowski.smb_cw1.entity.ShoppingListItem) = repo.addShoppingItem(shoppingListItem)

    fun updateShoppingListItem(shoppingListItem: com.filochowski.smb_cw1.entity.ShoppingListItem) = repo.updateShoppingListItem(shoppingListItem)

    fun deleteShoppingListItem(shoppingListItem: com.filochowski.smb_cw1.entity.ShoppingListItem) = repo.deleteShoppingListItem(shoppingListItem)

    fun removeAll() = repo.removeAll()
}