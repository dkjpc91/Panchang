package com.mithilakshar.mithilapanchang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.FirebaseFirestore

class katha : AppCompatActivity() {
    var kathaimageSlider: ImageSlider? = null
    var urllist: ArrayList<SlideModel>? = null
    var db: FirebaseFirestore? = null
    var kathaRecyclerView: RecyclerView? = null
    var dataList: ArrayList<kathadatamodel?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_katha)
        kathaimageSlider = findViewById(R.id.kathaimageSlider)
        kathaRecyclerView = findViewById(R.id.kathaRecyclerView)
        urllist = ArrayList()
        dataList = ArrayList()
        kathaRecyclerView.setLayoutManager(LinearLayoutManager(applicationContext))
        val adapter = kathaapapter(dataList!!)
        kathaRecyclerView.setAdapter(adapter)
        db = FirebaseFirestore.getInstance()
        db!!.collection("katha").get().addOnSuccessListener { queryDocumentSnapshots ->
            val list = queryDocumentSnapshots.documents
            for (d in list) {
                val obj = d.toObject(kathadatamodel::class.java)
                dataList!!.add(obj)
            }
            adapter.notifyDataSetChanged()
        }
        db!!.collection("banner").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (queryDocumentSnapshot in task.result) {
                    urllist!!.add(
                        SlideModel(
                            queryDocumentSnapshot.getString("url"),
                            ScaleTypes.CENTER_INSIDE
                        )
                    )
                    kathaimageSlider.setImageList(urllist!!, ScaleTypes.FIT)
                }
            }
        }
    }
}