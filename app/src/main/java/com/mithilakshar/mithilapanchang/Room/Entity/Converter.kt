package com.mithilakshar.mithilapanchang.Room.Entity

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mithilakshar.mithilapanchang.Models.Commentary
import com.mithilakshar.mithilapanchang.Models.Translation

class Converters {

    @TypeConverter
    fun fromString1(value: String?): MutableList<Translation>? {
        if (value.isNullOrEmpty()) {
            return null
        }
        val gson = Gson()
        return try {
            gson.fromJson(value, object : TypeToken<MutableList<Translation>>() {}.type)
        } catch (e: JsonSyntaxException) {
            // Handle potential JSON parsing errors here (optional)
            e.printStackTrace()
            null
        }
    }

    @TypeConverter
    fun toString1(list: MutableList<Translation>?) =
        Gson().toJson(list, object : TypeToken<MutableList<Translation>>() {}.type)

}