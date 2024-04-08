package com.mithilakshar.mithilapanchang.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mithilakshar.mithilapanchang.Repository.FirestoreRepo

class HomeViewModel :ViewModel() {


    val FirestoreRepo= FirestoreRepo()

     fun getBannerurlList(): LiveData<ArrayList<String>> {
        return FirestoreRepo.getBannerurlList()
    }


}