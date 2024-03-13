package com.mithilakshar.mithilapanchang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.FirebaseFirestore

class eclipse : AppCompatActivity() {
    var eclipseRecyclerView: RecyclerView? = null
    var data: ArrayList<eclipsedatamodel?>? = null
    var eclurl: ArrayList<SlideModel>? = null
    var eclipseimageSlider: ImageSlider? = null
    var db: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eclipse)
        eclipseimageSlider = findViewById(R.id.eclipseimageSlider)
        eclipseRecyclerView = findViewById(R.id.eclipseRecyclerView)

        data = ArrayList()
        eclurl = ArrayList()
        val eclipseadapter = eclipseadapter(data!!)

        db = FirebaseFirestore.getInstance()
        db!!.collection("eclipse").get().addOnSuccessListener { queryDocumentSnapshots ->
            val list = queryDocumentSnapshots.documents
            for (d in list) {
                val obj = d.toObject(eclipsedatamodel::class.java)
                data!!.add(obj)
            }
            eclipseadapter.notifyDataSetChanged()
        }
        db!!.collection("banner").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (queryDocumentSnapshot in task.result) {
                    eclurl!!.add(
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