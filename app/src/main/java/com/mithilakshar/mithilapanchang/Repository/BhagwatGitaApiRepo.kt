package com.mithilakshar.mithilapanchang.Repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mithilakshar.mithilapanchang.Api.BhagwatGitaRetrofitInstance
import com.mithilakshar.mithilapanchang.Models.BhagwatGitaVerseItem
import com.mithilakshar.mithilapanchang.Models.bhagwatGitaChapterItem
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

class BhagwatGitaApiRepo {

    suspend fun getBhagwatGitaverse(): List<bhagwatGitaChapterItem> {
     val response = try {
            BhagwatGitaRetrofitInstance.api.getchapter()
        } catch (e: IOException) {
        }

        return response as List<bhagwatGitaChapterItem>

    }


}