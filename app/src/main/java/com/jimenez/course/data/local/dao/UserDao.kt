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
    suspend fun insertUser(user: User)

    @Query("UPDATE user SET first_name = :firstName WHERE id = :idUser ")
    suspend fun updateUserName(idUser: Int, firstName: String)

    @Query("DELETE FROM user")
    suspend fun deleteUser()

}