package com.filochowski.smb_cw1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class StudentEntity(@PrimaryKey(autoGenerate = true) var id: Long = 0, var name: String, var surname: String, var graduated: Boolean) {

}