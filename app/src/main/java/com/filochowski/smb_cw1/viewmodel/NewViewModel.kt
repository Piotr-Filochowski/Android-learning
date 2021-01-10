package com.filochowski.smb_cw1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.filochowski.smb_cw1.FirebaseQueryLiveData
import com.filochowski.smb_cw1.dto.ShoppingListItemFirebaseDto
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewViewModel(application: Application, uid: String) : AndroidViewModel(application) {

    val allItems: FirebaseQueryLiveData
    private val database: DatabaseReference

    init {
        database = FirebaseDatabase.getInstance().getReference("/users/$uid")
        allItems = FirebaseQueryLiveData(database)
    }


    val dataSnapshotLiveData: FirebaseQueryLiveData
        get() {
            return allItems
        }

//    companion object {
//        private val SHOPPING_LIST_ITEM_REF =
//            FirebaseDatabase.getInstance().getReference("/ShoppingListItem")
//    }

    fun getShoppingItems(): FirebaseQueryLiveData {
        return allItems
    }

    fun addShoppingItem(item: ShoppingListItemFirebaseDto) {
        var idFirebase = database.push().key
        if (idFirebase != null) {
            item.id = idFirebase
            database.child(idFirebase).setValue(item)
        }
    }

    fun updateShoppingListItem(item: ShoppingListItemFirebaseDto) {
        database.child(item.id!!).setValue(item)
    }

    fun deleteShoppingListItem(item: ShoppingListItemFirebaseDto) {
        database.child(item.id!!).removeValue();
    }

    fun removeAll() {
        // maybe not
    }
}