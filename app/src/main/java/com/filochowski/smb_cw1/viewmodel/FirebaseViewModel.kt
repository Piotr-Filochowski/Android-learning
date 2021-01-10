package com.filochowski.smb_cw1.viewmodel

import com.filochowski.smb_cw1.FirebaseQueryLiveData
import com.filochowski.smb_cw1.dto.ShoppingListItemFirebaseDto

interface FirebaseViewModel {

    fun getShoppingItems(): FirebaseQueryLiveData

    fun addShoppingItem(item: ShoppingListItemFirebaseDto)

    fun updateShoppingListItem(item: ShoppingListItemFirebaseDto)

    fun deleteShoppingListItem(item: ShoppingListItemFirebaseDto)
}