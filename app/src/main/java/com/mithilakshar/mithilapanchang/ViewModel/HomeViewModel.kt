package com.mithilakshar.mithilapanchang.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.mithilakshar.mithilapanchang.Models.calendardatamodel
import com.mithilakshar.mithilapanchang.Models.eclipsedatamodel
import com.mithilakshar.mithilapanchang.Models.holidaydatamodel
import com.mithilakshar.mithilapanchang.Models.kathadatamodel
import com.mithilakshar.mithilapanchang.Models.mantradatamodel
import com.mithilakshar.mithilapanchang.Repository.FirestoreRepo
import kotlinx.coroutines.tasks.await

class HomeViewModel :ViewModel() {


    val FirestoreRepo= FirestoreRepo()

     fun getBannerurlList(path:String): LiveData<ArrayList<String>> {
        return FirestoreRepo.getBannerurlList(path)
    }

    suspend fun getHolidaylist(): List<holidaydatamodel> {

        return FirestoreRepo.getHolidaylist()
    }

    suspend fun getEclipseList(): List<eclipsedatamodel> {
        return FirestoreRepo.getEclipseList()
    }

    suspend fun getMantraList(): List<mantradatamodel> {
        return FirestoreRepo.getMantraList()
    }

    suspend fun getKathaList(): List<kathadatamodel> {

        return FirestoreRepo.getKathaList()
    }

    suspend fun getCalendarList(path: String): List<calendardatamodel> {
        return FirestoreRepo.getCalendarList(path)
    }




}