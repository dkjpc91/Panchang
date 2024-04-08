package com.mithilakshar.mithilapanchang.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepo {


    val db = FirebaseFirestore.getInstance()
    var templist:ArrayList<String> = arrayListOf()

    val bannerUrlList = MutableLiveData<ArrayList<String>>()

    fun getBannerurlList():LiveData<ArrayList<String>>{

        db.collection("banner").document("a").get()
            .addOnSuccessListener {

                if (it.exists()){
                    val data = it.data // Get all key-value pairs

                    if (data != null) {
                        for ((key,value) in data){
                            templist.add(value.toString())
                        }
                    }

                    bannerUrlList.value=templist
                }

                else {
                    templist.add("https://firebasestorage.googleapis.com/v0/b/mithila-panchang-6b042.appspot.com/o/shiva.png?alt=media&token=d7338f1c-87a5-4cdd-812b-68364f365373")
                    bannerUrlList.value=templist
                }

            }.addOnFailureListener {

                templist.add("https://firebasestorage.googleapis.com/v0/b/mithila-panchang-6b042.appspot.com/o/shiva.png?alt=media&token=d7338f1c-87a5-4cdd-812b-68364f365373")
                bannerUrlList.value=templist
            }

        return bannerUrlList

    }


}