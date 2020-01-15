package com.devtides.coroutinesroom.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "password_hash") val passwordHash: Int,
    @ColumnInfo(name = "other_info") val otherInfo: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}