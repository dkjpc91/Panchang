package com.mithilakshar.mithilapanchang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.FirebaseFirestore

class holiday : AppCompatActivity() {
    var holidayRecyclerView: RecyclerView? = null
    var dataList: ArrayList<holidaydatamodel?>? = null
    var db: FirebaseFirestore? = null
    var holidayimageSlider: ImageSlider? = null
    var urllist: ArrayList<SlideModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holiday)
        holidayimageSlider = findViewById(R.id.holidayimageSlider)
        urllist = ArrayList()
        holidayRecyclerView = findViewById(R.id.holidayRecyclerView)
        holidayRecyclerView.setLayoutManager(LinearLayoutManager(applicationContext))
        dataList = ArrayList()
        val adapter = holidayadapter(dataList!!)
        holidayRecyclerView.setAdapter(adapter)
        db = FirebaseFirestore.getInstance()
        db!!.collection("holiday").get().addOnSuccessListener { queryDocumentSnapshots ->
            val list = queryDocumentSnapshots.documents
            for (d in list) {
                val obj = d.toObject(holidaydatamodel::class.java)
                dataList!!.add(obj)
            }
            adapter.notifyDataSetChanged()
        }
        db = FirebaseFirestore.getInstance()
        db!!.collection("banner").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (queryDocumentSnapshot in task.result) {
                    urllist!!.add(
                        SlideModel(
                            queryDocumentSnapshot.getString("url"),
                            ScaleTypes.CENTER_INSIDE
                        )
                    )
                    holidayimageSlider.setImageList(urllist!!, ScaleTypes.FIT)
                }
            }
        }
    }
}