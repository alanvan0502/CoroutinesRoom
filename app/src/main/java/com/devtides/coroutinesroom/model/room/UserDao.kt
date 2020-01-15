package com.devtides.coroutinesroom.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devtides.coroutinesroom.model.data.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user_table WHERE user_name = :userName")
    suspend fun getUser(userName: String): User?

    @Query("DELETE FROM user_table WHERE id = :id")
    suspend fun deleteUser(id: Long): Int
}