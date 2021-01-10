package com.filochowski.smb_cw1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.filochowski.smb_cw1.FirebaseQueryLiveData
import com.filochowski.smb_cw1.dto.ShoppingListItemFirebaseDto
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseCommonItemsViewModel(application: Application) : AndroidViewModel(application), FirebaseViewModel {

    val allItems: FirebaseQueryLiveData
    private val database: DatabaseReference

    init {
        database = FirebaseDatabase.getInstance().getReference("/public")
        allItems = FirebaseQueryLiveData(database)
    }


    val dataSnapshotLiveData: FirebaseQueryLiveData
        get() {
            return allItems
        }

    override fun getShoppingItems(): FirebaseQueryLiveData {
        return allItems
    }

    override fun addShoppingItem(item: ShoppingListItemFirebaseDto) {
        var idFirebase = database.push().key
        if (idFirebase != null) {
            item.id = idFirebase
            database.child(idFirebase).setValue(item)
        }
    }

    override fun updateShoppingListItem(item: ShoppingListItemFirebaseDto) {
        database.child(item.id!!).setValue(item)
    }

    override fun deleteShoppingListItem(item: ShoppingListItemFirebaseDto) {
        database.child(item.id!!).removeValue();
    }
}