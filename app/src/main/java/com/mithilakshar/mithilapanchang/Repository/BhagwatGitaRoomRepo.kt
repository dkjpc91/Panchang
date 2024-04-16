package com.mithilakshar.mithilapanchang.Repository

import androidx.lifecycle.LiveData
import com.mithilakshar.mithilapanchang.Models.BhagwatGitaVerseItem
import com.mithilakshar.mithilapanchang.Models.bhagwatGitaChapterItem
import com.mithilakshar.mithilapanchang.Room.Dao.BhagwatGitaChapterDao
import com.mithilakshar.mithilapanchang.Room.Dao.BhagwatGitaVerseDao
import com.mithilakshar.mithilapanchang.Room.Entity.BhagwatGitaChapter

class BhagwatGitaRoomRepo(val dao: BhagwatGitaChapterDao,val verseDao: BhagwatGitaVerseDao) {





     fun readBhagwatGitaChapter():LiveData<List<bhagwatGitaChapterItem>> {

        return dao.readBhagwatGitaChapter()
    }

    fun readBhagwatGitaVerse():LiveData<List<BhagwatGitaVerseItem>> {

        return verseDao.readBhagwatGitaVerse()
    }

    suspend fun readBhagwatgitaversewithid(ID:Int):List<BhagwatGitaVerseItem>{

        return verseDao.readBhagwatGitaVersewithId(ID)
    }


}