package com.mithilakshar.mithilapanchang.Repository

import androidx.lifecycle.LiveData
import com.mithilakshar.mithilapanchang.Models.bhagwatGitaChapterItem
import com.mithilakshar.mithilapanchang.Room.Dao.BhagwatGitaChapterDao
import com.mithilakshar.mithilapanchang.Room.Entity.BhagwatGitaChapter

class BhagwatGitaRoomRepo(val dao: BhagwatGitaChapterDao) {





     fun readBhagwatGitaChapter():LiveData<List<bhagwatGitaChapterItem>> {

        return dao.readBhagwatGitaChapter()
    }


}