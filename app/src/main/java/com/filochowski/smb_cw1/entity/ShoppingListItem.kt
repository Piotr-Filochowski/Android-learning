package com.filochowski.smb_cw1.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShoppingListItem")
data class ShoppingListItem(@PrimaryKey(autoGenerate = true) var id: Long = 0, var name: String, var quantity: Float, var price: Float, var bought: Boolean = false) {

}