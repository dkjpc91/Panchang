package com.mithilakshar.mithilapanchang.Room.Entity

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mithilakshar.mithilapanchang.Models.Commentary

class Converter {


    @TypeConverter
    fun fromString(value: String?): List<Commentary>? {
        if (value.isNullOrEmpty()) {
            return null
        }
        val gson = Gson()
        return try {
            gson.fromJson(value, object : TypeToken<List<Commentary>>() {}.type)
        } catch (e: JsonSyntaxException) {
            // Handle potential JSON parsing errors here (optional)
            e.printStackTrace()
            null
        }
    }

    @TypeConverter
    fun toString(list: List<Commentary>?) =
        Gson().toJson(list, object : TypeToken<List<Commentary>>() {}.type)

}