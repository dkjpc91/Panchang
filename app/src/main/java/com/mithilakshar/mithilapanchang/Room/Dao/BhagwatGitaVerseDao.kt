package com.mithilakshar.mithilapanchang.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mithilakshar.mithilapanchang.Room.Entity.BhagwatGitaVerse

@Dao
interface BhagwatGitaVerseDao {





    @Insert
    suspend fun insertVerse(BhagwatGitaVerse:BhagwatGitaVerse)



}