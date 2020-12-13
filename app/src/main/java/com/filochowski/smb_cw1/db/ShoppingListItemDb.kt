package com.filochowski.smb_cw1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.filochowski.smb_cw1.dao.ShoppingListItemDao
import com.filochowski.smb_cw1.entity.ShoppingListItem

@Database(entities = [ShoppingListItem::class], version = 1)
abstract class ShoppingListItemDb : RoomDatabase() {

    abstract fun getShoppingListItemDao(): ShoppingListItemDao

    companion object {
        private var instance: ShoppingListItemDb? = null

        fun getDatabase(context: Context): ShoppingListItemDb? {
            if (instance != null)
                return instance
            instance = Room.databaseBuilder(
                context,
                ShoppingListItemDb::class.java,
                "shoppingListItemsDb"
            ).allowMainThreadQueries().build()
            return instance
        }
    }
}