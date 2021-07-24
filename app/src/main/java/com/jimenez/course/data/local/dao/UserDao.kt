package com.jimenez.course.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jimenez.course.data.local.entites.User

@Dao
interface UserDao {

    @Query("SELECT * from user")
    suspend fun getUsers(): List<User>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("UPDATE user SET first_name = :firstName WHERE id = :idUser ")
    suspend fun updateUserName(idUser: Int, firstName: String)

    @Query("DELETE FROM user")
    suspend fun deleteUser()

    @Query("SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getLogin(email: String, password: String): User?

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    suspend fun getEmail(email: String): User?

    @Query("SELECT * FROM user WHERE password = :password LIMIT 1")
    suspend fun getPassword(password: String): User?

}