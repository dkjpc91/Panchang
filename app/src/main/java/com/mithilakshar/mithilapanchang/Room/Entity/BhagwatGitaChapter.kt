package com.mithilakshar.mithilapanchang.Room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="chapter")
data class BhagwatGitaChapter(
    @PrimaryKey(autoGenerate = true)
    val chapter_number: Int,
    val chapter_summary: String,
    val id: Int,
    val name: String,
    val name_meaning: String,
    val name_translated: String,
    val name_transliterated: String,
    val verses_count: Int,

)