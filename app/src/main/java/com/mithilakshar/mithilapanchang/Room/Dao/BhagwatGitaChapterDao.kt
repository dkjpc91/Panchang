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
    fun readBhagwatGitaChapter(): LiveData<List<bhagwatGitaChapterItem>>



}