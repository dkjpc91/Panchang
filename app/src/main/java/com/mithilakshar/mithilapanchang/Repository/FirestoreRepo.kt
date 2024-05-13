package com.mithilakshar.mithilapanchang.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.mithilakshar.mithilapanchang.Models.calendardatamodel
import com.mithilakshar.mithilapanchang.Models.eclipsedatamodel
import com.mithilakshar.mithilapanchang.Models.holidaydatamodel
import com.mithilakshar.mithilapanchang.Models.kathadatamodel
import com.mithilakshar.mithilapanchang.Models.mantradatamodel
import kotlinx.coroutines.tasks.await

class FirestoreRepo {




    val db = FirebaseFirestore.getInstance()
    var templist: ArrayList<String> = arrayListOf()


    val bannerUrlList = MutableLiveData<ArrayList<String>>()

    fun getBannerurlList(path:String): LiveData<ArrayList<String>> {

        db.collection("banner").document(path).get()
            .addOnSuccessListener {

                if (it.exists()) {
                    val data = it.data // Get all key-value pairs

                    if (data != null) {
                        for ((key, value) in data) {
                            templist.add(value.toString())
                        }
                    }

                    bannerUrlList.value = templist
                } else {
                    templist.add("https://firebasestorage.googleapis.com/v0/b/mithila-panchang-6b042.appspot.com/o/shiva.png?alt=media&token=d7338f1c-87a5-4cdd-812b-68364f365373")
                    bannerUrlList.value = templist
                }

            }.addOnFailureListener {

                templist.add("https://firebasestorage.googleapis.com/v0/b/mithila-panchang-6b042.appspot.com/o/shiva.png?alt=media&token=d7338f1c-87a5-4cdd-812b-68364f365373")
                bannerUrlList.value = templist
            }

        return bannerUrlList

    }

    fun getspeaktext(date: String, month:String, callback: (String?) -> Unit){

        var speak: String? = null
        val collectionRef = db.collection(month)
        val query = collectionRef.whereEqualTo("date", date)

        query.get().addOnCompleteListener {

            if (it.isSuccessful){

                for (i in it.result){

                    speak= i.get("speak").toString()
                }

                callback (speak)
            }
        }

    }


    suspend fun getHolidaylist(): List<holidaydatamodel> {
        val db = FirebaseFirestore.getInstance()
        val querySnapshot = db.collection("holiday").get().await()
        return querySnapshot.toObjects(holidaydatamodel::class.java)
    }

    suspend fun getEclipseList(): List<eclipsedatamodel> {
        val db = FirebaseFirestore.getInstance()
        val querySnapshot = db.collection("eclipse").get().await()
        return querySnapshot.toObjects(eclipsedatamodel::class.java)
    }

    suspend fun getMantraList(): List<mantradatamodel> {
        val db = FirebaseFirestore.getInstance()
        val querySnapshot = db.collection("mantra").get().await()
        return querySnapshot.toObjects(mantradatamodel::class.java)
    }

    suspend fun getKathaList(): List<kathadatamodel> {
        val db = FirebaseFirestore.getInstance()
        val querySnapshot = db.collection("katha").get().await()
        return querySnapshot.toObjects(kathadatamodel::class.java)
    }


    suspend fun getCalendarList(path: String): List<calendardatamodel> {
        val db = FirebaseFirestore.getInstance()
        val snapshot = db.collection(path).get().await()
        return snapshot.toObjects(calendardatamodel::class.java)
    }


    suspend fun gethomeBroadcast(): String {
        val db = FirebaseFirestore.getInstance()
        val querySnapshot = db.collection("homeBroadcast").document("homeBroadcast").get().await()

        return querySnapshot.get("speak").toString()
    }










}
