package com.jimenez.course.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jimenez.course.data.local.dao.UserDao
import com.jimenez.course.data.local.entites.User

@Database(
    entities = [
        User::class
    ],
    version = 1
)
abstract class CourseRoomDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: CourseRoomDataBase? = null

        fun getDataBase(context: Context): CourseRoomDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CourseRoomDataBase::class.java,
                    "course_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

}
