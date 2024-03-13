package com.mithilakshar.mithilapanchang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.FirebaseFirestore

class mantra : AppCompatActivity() {
    var mantraimageSlider: ImageSlider? = null
    var urllist: ArrayList<SlideModel>? = null
    var db: FirebaseFirestore? = null
    var mantraRecyclerView: RecyclerView? = null
    var data: ArrayList<mantradatamodel?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mantra)
        mantraimageSlider = findViewById(R.id.mantraimageSlider)
        mantraRecyclerView = findViewById(R.id.mantraRecyclerView)
        urllist = ArrayList()
        data = ArrayList()

        val adapter = mantraadapter(data!!)

        db = FirebaseFirestore.getInstance()
        db!!.collection("mantra").get().addOnSuccessListener { queryDocumentSnapshots ->
            val list = queryDocumentSnapshots.documents
            for (d in list) {
                val obj = d.toObject(mantradatamodel::class.java)
                data!!.add(obj)
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

                }
            }
        }
    }
}