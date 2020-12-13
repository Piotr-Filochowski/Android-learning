package com.filochowski.smb_cw1.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.filochowski.smb_cw1.entity.ShoppingListItem

@Dao
interface ShoppingListItemDao {

    @Query("SELECT * FROM ShoppingListItem")
    fun getAllShoppingListItems(): LiveData<List<ShoppingListItem>>

    @Insert
    fun addShoppingListItem(shoppingListItem: ShoppingListItem)

    @Update
    fun updateShoppingListItem(shoppingListItem: ShoppingListItem)

    @Delete
    fun deleteShoppingListItem(shoppingListItem: ShoppingListItem)

    @Query("DELETE FROM ShoppingListItem")
    fun deleteAll()
}