package com.mithilakshar.mithilapanchang.UI.View


import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.firebase.firestore.FirebaseFirestore
import com.mithilakshar.mithilapanchang.Adapters.holidayadapter
import com.mithilakshar.mithilapanchang.Dialog.Networkdialog
import com.mithilakshar.mithilapanchang.Notification.NetworkManager
import com.mithilakshar.mithilapanchang.Utility.FileDownloaderProgress
import com.mithilakshar.mithilapanchang.Utility.dbHelper

import com.mithilakshar.mithilapanchang.databinding.ActivityHolidaylistBinding
import java.io.File


class HolidayListActivity : AppCompatActivity() {

    lateinit var binding:ActivityHolidaylistBinding
    private lateinit var fileExistenceLiveData: LiveData<Boolean>

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: holidayadapter
    private var holidays: MutableList<Map<String, String>> = mutableListOf()


    private lateinit var fileDownloaderProgress: FileDownloaderProgress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityHolidaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkdialog = Networkdialog(this)
        val networkManager= NetworkManager(this)
        networkManager.observe(this, {
            if (!it){
                if (!networkdialog.isShowing){networkdialog.show()}

            }else{
                if (networkdialog.isShowing){networkdialog.dismiss()}

            }
        })

        recyclerView = binding.holidayrecycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = holidayadapter(this, holidays)
        recyclerView.adapter = adapter


        // Notify adapter that data set has changed
        adapter.notifyDataSetChanged()

        val intent = intent
        val month = intent.getStringExtra("month").toString()
        val monthEng = intent.getStringExtra("monthEng").toString()
        val intValue = intent.getIntExtra("intValue", 1)





        fileDownloaderProgress = FileDownloaderProgress(this)




        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("SQLdb")
        val documentRef = collectionRef.document("holiday")
        documentRef.get().addOnSuccessListener {
            if (it != null) {
                val fileUrl =it.getString("test") ?: ""
                val actions = it.getString("action") ?: "delete"
                fileDownloaderProgress.downloadFile(fileUrl, "holiday.db", actions)
            }
            else {
                Log.d("Firestore", "No such document")
            }
        }






        fileDownloaderProgress.getDownloadStatus().observe(this, Observer { isSuccess ->
           //binding.monthname.text = if (isSuccess) "Download Successful" else "Download Failed"
        })

        fileDownloaderProgress.getDownloadProgress().observe(this, Observer { progress ->


            if (progress == 100) {
                binding.lottieAnimationView.visibility = View.GONE
                observeFileExistence(monthEng)
                adapter.notifyDataSetChanged()
            } else {
                binding.lottieAnimationView.visibility = View.VISIBLE
            }

        })



        binding.monthname.text="$month"+"त्यौहार"





    }



    private fun observeFileExistence(month:String) {
        fileExistenceLiveData = checkFileExistence("holiday.db")

        // Observe changes in file existence
        fileExistenceLiveData.observe(this) { fileExists ->
            if (fileExists) {
                // File exists, proceed with reading its content
                readFileContent(month)
            } else {
                // File does not exist, handle accordingly
            }
        }
    }


    private fun readFileContent(month:String) {
        val dbHelper = dbHelper(applicationContext, "holiday.db")
        val av = dbHelper.getHolidaysByMonth(month)
        holidays.addAll(av)
        adapter.notifyDataSetChanged()

    }

    fun checkFileExistence(fileName: String): LiveData<Boolean> {
        val fileExistsLiveData = MutableLiveData<Boolean>()
        val dbFolderPath = this.getExternalFilesDir(null)?.absolutePath + File.separator + "test"
        val dbFile = File(dbFolderPath, fileName)
        fileExistsLiveData.value = dbFile.exists()
        return fileExistsLiveData
    }




}