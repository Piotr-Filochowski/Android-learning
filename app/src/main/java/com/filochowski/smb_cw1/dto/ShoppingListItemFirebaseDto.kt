package com.filochowski.smb_cw1.dto

data class ShoppingListItemFirebaseDto(
     var id: String,
     var localDatabaseId: Long,
     var name: String,
     var quantity: Float,
     var price: Float,
     var bought: Boolean = false
) {}


