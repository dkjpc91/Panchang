package com.mithilakshar.mithilapanchang.Repository

import androidx.lifecycle.LiveData
import com.mithilakshar.mithilapanchang.Models.BhagwatGitaVerseItem
import com.mithilakshar.mithilapanchang.Models.bhagwatGitaChapterItem
import com.mithilakshar.mithilapanchang.Room.Dao.BhagwatGitaChapterDao
import com.mithilakshar.mithilapanchang.Room.Dao.BhagwatGitaVerseDao
import com.mithilakshar.mithilapanchang.Room.Entity.BhagwatGitaChapter

class BhagwatGitaRoomRepo(val dao: BhagwatGitaChapterDao,val verseDao: BhagwatGitaVerseDao) {





     suspend fun readBhagwatGitaChapter():List<bhagwatGitaChapterItem> {

        return dao.readBhagwatGitaChapter()
    }

    suspend fun readBhagwatGitaVerse(id:Int):List<BhagwatGitaVerseItem>{

        return verseDao.readBhagwatGitaVerse(id)
    }

    suspend fun readBhagwatgitaversewithid(ID:Int):List<BhagwatGitaVerseItem>{

        return verseDao.readBhagwatGitaVersewithId(ID)
    }

    suspend fun readBhagwatGitaChapter(id:Int): List<bhagwatGitaChapterItem>{

        return dao.readBhagwatGitaChapter(id)
    }


}