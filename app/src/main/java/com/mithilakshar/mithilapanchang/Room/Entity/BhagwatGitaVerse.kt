package com.mithilakshar.mithilapanchang.Room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mithilakshar.mithilapanchang.Models.Commentary
import com.mithilakshar.mithilapanchang.Models.Translation


@Entity(tableName = "verse")
data class BhagwatGitaVerse(
    val chapter_number: Int,
    @TypeConverters(Converter::class)
    val commentaries: ArrayList<Commentary>?,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    @TypeConverters(Converter::class)
    val translations: ArrayList<Commentary>?,
    val verse_number: Int,
    val maithili_verse: String
)