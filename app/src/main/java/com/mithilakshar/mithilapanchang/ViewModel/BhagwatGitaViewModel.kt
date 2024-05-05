package com.mithilakshar.mithilapanchang.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mithilakshar.mithilapanchang.Models.BhagwatGitaVerseItem
import com.mithilakshar.mithilapanchang.Models.bhagwatGitaChapterItem
import com.mithilakshar.mithilapanchang.Repository.BhagwatGitaRoomRepo
import com.mithilakshar.mithilapanchang.Room.Database.BhagwatGitaChapterDatabase
import com.mithilakshar.mithilapanchang.Room.Database.BhagwatGitaVerseDatabase

class BhagwatGitaViewModel(application: Application): AndroidViewModel(application) {




    val dao=BhagwatGitaChapterDatabase.getdbcopy(application.applicationContext).chapterdao()
    val versedao=BhagwatGitaVerseDatabase.getdbcopy(application.applicationContext).verseDao()


    val bhagwatGitaRoomRepo= BhagwatGitaRoomRepo(dao,versedao)

    suspend fun getBhagwatGitaChapterDetails():List<bhagwatGitaChapterItem>{
        return bhagwatGitaRoomRepo.readBhagwatGitaChapter()
    }

    suspend fun getBhagwatGitaVerseDetails(id: Int):List<BhagwatGitaVerseItem>{

        return bhagwatGitaRoomRepo.readBhagwatGitaVerse(id)
    }

    suspend fun readBhagwatgitaversewithid(ID:Int):List<BhagwatGitaVerseItem>{

        return bhagwatGitaRoomRepo.readBhagwatgitaversewithid(ID)
    }


    suspend fun readBhagwatGitaChaptername(id:Int): List<bhagwatGitaChapterItem>{

        return dao.readBhagwatGitaChapter(id)
    }





}