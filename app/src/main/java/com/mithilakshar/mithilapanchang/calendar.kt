package com.mithilakshar.mithilapanchang

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.FirebaseFirestore

class calendar : AppCompatActivity() {
    var calendarimageSlider: ImageSlider? = null
    var monthName: TextView? = null
    var backfragment: ImageView? = null
    var forwardfragment: ImageView? = null
    var calurl: ArrayList<SlideModel>? = null
    var db: FirebaseFirestore? = null
    var fragmentindex = arrayOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )
    var fragmentindexnumber = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        calendarimageSlider = findViewById(R.id.calendarimageSlider)
        monthName = findViewById(R.id.monthName)
        forwardfragment = findViewById(R.id.forwardfragment)
        backfragment = findViewById(R.id.backfragment)
        val intent = intent
        val currentMonth = intent.getStringExtra("currentMonth")
        val currentDate = intent.getStringExtra("currentDate")
        val hindiMonth = translateToHindi(currentMonth)
        monthName.setText(hindiMonth)
        calurl = ArrayList()
        db = FirebaseFirestore.getInstance()
        db!!.collection("banner").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (queryDocumentSnapshot in task.result) {
                    calurl!!.add(
                        SlideModel(
                            queryDocumentSnapshot.getString("url"),
                            ScaleTypes.CENTER_INSIDE
                        )
                    )
                    calendarimageSlider.setImageList(calurl!!, ScaleTypes.FIT)
                }
            }
        }
        loadfragment(currentMonth)
        for (i in fragmentindex.indices) {
            if (fragmentindex[i].indexOf(currentMonth!!) != -1) {
                fragmentindexnumber = i
                break
            }
        }
        backfragment.setOnClickListener(View.OnClickListener {
            if (fragmentindexnumber == 0) {
                fragmentindexnumber = fragmentindex.size - 1
                loadfragment(fragmentindex[fragmentindexnumber])
                val hindiMonth = translateToHindi(fragmentindex[fragmentindexnumber])
                monthName.setText(hindiMonth)
            } else {
                fragmentindexnumber = fragmentindexnumber - 1
                loadfragment(fragmentindex[fragmentindexnumber])
                val hindiMonth = translateToHindi(fragmentindex[fragmentindexnumber])
                monthName.setText(hindiMonth)
            }
        })
        forwardfragment.setOnClickListener(View.OnClickListener {
            fragmentindexnumber = (fragmentindexnumber + 1) % fragmentindex.size
            loadfragment(fragmentindex[fragmentindexnumber])
            val hindiMonth = translateToHindi(fragmentindex[fragmentindexnumber])
            monthName.setText(hindiMonth)
        })
    }

    private fun translateToHindi(currentMonth: String?): String? {
        // Manually create a mapping for English to Hindi month names
        val monthTranslation: MutableMap<String?, String> = HashMap()
        monthTranslation["January"] = "जनवरी"
        monthTranslation["February"] = "फ़रवरी"
        monthTranslation["March"] = "मार्च"
        monthTranslation["April"] = "अप्रैल"
        monthTranslation["May"] = "मई"
        monthTranslation["June"] = "जून"
        monthTranslation["July"] = "जुलाई"
        monthTranslation["August"] = "अगस्त"
        monthTranslation["September"] = "सितंबर"
        monthTranslation["October"] = "अक्टूबर"
        monthTranslation["November"] = "नवंबर"
        monthTranslation["December"] = "दिसंबर"
        // Return the translated month name
        return monthTranslation[currentMonth]
    }

    private fun loadfragment(currentMonth: String?) {
        val fragmentManager = supportFragmentManager

        // Begin a new FragmentTransaction
        val fragmentTransaction = fragmentManager.beginTransaction()
        when (currentMonth) {
            "December" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val df = Decemberfragment()
                fragmentTransaction.replace(R.id.fragmentContainer, df)
                fragmentTransaction.commit()
            }

            "January" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val jf = Januaryfragment()
                fragmentTransaction.replace(R.id.fragmentContainer, jf)
                fragmentTransaction.commit()
            }

            "February" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val ff = februaryfragment()
                fragmentTransaction.replace(R.id.fragmentContainer, ff)
                fragmentTransaction.commit()
            }

            "March" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val mf = marchfragment()
                fragmentTransaction.replace(R.id.fragmentContainer, mf)
                fragmentTransaction.commit()
            }

            "April" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val af = aprilfragment()
                fragmentTransaction.replace(R.id.fragmentContainer, af)
                fragmentTransaction.commit()
            }

            "May" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val myf = mayfragment()
                fragmentTransaction.replace(R.id.fragmentContainer, myf)
                fragmentTransaction.commit()
            }

            "June" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val jef = junefragment()
                fragmentTransaction.replace(R.id.fragmentContainer, jef)
                fragmentTransaction.commit()
            }

            "July" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val jyf = julyfragment()
                fragmentTransaction.replace(R.id.fragmentContainer, jyf)
                fragmentTransaction.commit()
            }

            "August" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val atf = augustfragment()
                fragmentTransaction.replace(R.id.fragmentContainer, atf)
                fragmentTransaction.commit()
            }

            "September" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val sf = septemberfragment()
                fragmentTransaction.replace(R.id.fragmentContainer, sf)
                fragmentTransaction.commit()
            }

            "October" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val of = octoberfragment()
                fragmentTransaction.replace(R.id.fragmentContainer, of)
                fragmentTransaction.commit()
            }

            "November" -> {
                // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
                val nf = novemberfragment()
                fragmentTransaction.replace(R.id.fragmentContainer, nf)
                fragmentTransaction.commit()
            }
        }
    }
}