package com.mithilakshar.mithilapanchang.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mithilakshar.mithilapanchang.Models.BhagwatGitaVerseItem
import com.mithilakshar.mithilapanchang.Models.bhagwatGitaChapterItem
import com.mithilakshar.mithilapanchang.Room.Entity.BhagwatGitaVerse

@Dao
interface BhagwatGitaVerseDao {



    @Query("SELECT * FROM verse WHERE chapter_number= :id")
    suspend fun readBhagwatGitaVerse(id:Int): List<BhagwatGitaVerseItem>

    @Query("SELECT * FROM verse WHERE id= :ID")
    suspend fun readBhagwatGitaVersewithId (ID : Int ): List<BhagwatGitaVerseItem>


}