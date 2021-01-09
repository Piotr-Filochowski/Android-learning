package com.filochowski.smb_cw1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ShoppingListItemViewModel(application: Application) : AndroidViewModel(application) {

//    private var database: DatabaseReference
//    val allItems: LiveData<List<com.filochowski.smb_cw1.dto.ShoppingListItemFirebaseDto>>
//
//    init {
//        database = FirebaseDatabase.getInstance().getReference("ShoppingListItem")
//        allItems = repo.getShoppingItems()
//    }
//
//    fun getShoppingItems() = repo.getShoppingItems()
//
//    fun addShoppingItem(shoppingListItem: com.filochowski.smb_cw1.dto.ShoppingListItemFirebaseDto) = repo.addShoppingItem(shoppingListItem)
//
//    fun updateShoppingListItem(shoppingListItem: com.filochowski.smb_cw1.dto.ShoppingListItemFirebaseDto) = repo.updateShoppingListItem(shoppingListItem)
//
//    fun deleteShoppingListItem(shoppingListItem: com.filochowski.smb_cw1.dto.ShoppingListItemFirebaseDto) = repo.deleteShoppingListItem(shoppingListItem)
//
//    fun removeAll() = repo.removeAll()
}