package com.jimenez.course.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringConverter {

    @TypeConverter
    fun listToString(sameObjects: List<Int>?): String? {
        if (sameObjects == null) {
            return null
        }
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().toJson(sameObjects, listType)
    }

    @TypeConverter
    fun stringToList(data: String?): List<Int>? {
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(data,listType)
    }

}