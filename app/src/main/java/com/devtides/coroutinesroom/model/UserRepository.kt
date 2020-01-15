package com.devtides.coroutinesroom.model

import android.util.Log
import com.devtides.coroutinesroom.model.data.User
import com.devtides.coroutinesroom.model.room.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun deleteUser(id: Long): Boolean {
        return userDao.deleteUser(id) > 0
    }

    suspend fun getUser(userName: String): User? {
        return userDao.getUser(userName)
    }
}