package com.jimenez.course.data.local.repositories

import com.jimenez.course.data.local.dao.UserDao
import com.jimenez.course.data.local.entites.User

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User): Long = userDao.insertUser(user)

    suspend fun getLogin(email: String, password: String): User? {
        return userDao.getLogin(email, password)
    }

    suspend fun getEmail(email: String): User? {
        return userDao.getEmail(email)
    }

    suspend fun getPassword(password: String): User? {
        return userDao.getPassword(password)
    }

}