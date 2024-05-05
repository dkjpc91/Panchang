package com.mithilakshar.mithilapanchang.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mithilakshar.mithilapanchang.Models.bhagwatGitaChapterItem

import com.mithilakshar.mithilapanchang.Room.Entity.BhagwatGitaChapter

@Dao
interface BhagwatGitaChapterDao {



    @Query("SELECT * FROM chapter")
    suspend fun readBhagwatGitaChapter(): List<bhagwatGitaChapterItem>

    @Query("SELECT * FROM chapter WHERE chapter_number= :id")
    suspend fun readBhagwatGitaChapter(id:Int): List<bhagwatGitaChapterItem>



}