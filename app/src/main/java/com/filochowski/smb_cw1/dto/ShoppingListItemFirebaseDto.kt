package com.filochowski.smb_cw1.dto

data class ShoppingListItemFirebaseDto(
     var id: String?,
     var name: String?,
     var quantity: Float?,
     var price: Float?,
     var bought: Boolean?
) {

     constructor(): this("null", "null", 0.0f, 0.0f, false){}
}


