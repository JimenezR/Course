package com.jimenez.course.data.local.repositories

import com.jimenez.course.data.local.dao.UserDao
import com.jimenez.course.data.local.entites.User

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) = userDao.insertUser(user)

}