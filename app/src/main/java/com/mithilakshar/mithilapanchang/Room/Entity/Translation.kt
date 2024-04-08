package com.mithilakshar.mithilapanchang.Room.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Translation(
    val author_name: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val language: String
)