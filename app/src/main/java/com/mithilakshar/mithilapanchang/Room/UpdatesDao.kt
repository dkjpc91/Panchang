package com.mithilakshar.mithilapanchang.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface  UpdatesDao  {
    @Insert
    suspend fun insert(Updates: Updates)

    @Query("SELECT * FROM files")
    suspend fun getallupdates(): List<Updates>

    @Query("SELECT * FROM files WHERE fileName = :fileName")
    suspend fun getfileupdate(fileName: String): List<Updates>

    @Query("UPDATE files SET uniqueString = :newUniqueString WHERE fileName = :fileName")
    suspend fun updateUniqueString(fileName: String, newUniqueString: String)
}