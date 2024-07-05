package com.mithilakshar.mithilapanchang.Room
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "files")
data class Updates(
@PrimaryKey(autoGenerate = true)
val id: Long = 0,
val fileName: String,
val uniqueString: String
)