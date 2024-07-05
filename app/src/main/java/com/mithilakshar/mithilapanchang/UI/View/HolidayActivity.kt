package com.mithilakshar.mithilapanchang.UI.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import com.mithilakshar.mithilapanchang.Dialog.Networkdialog
import com.mithilakshar.mithilapanchang.databinding.ActivityHolidayBinding
import com.mithilakshar.mithilapanchang.Notification.NetworkManager
import com.mithilakshar.mithilapanchang.Utility.FileDownloaderProgress
import java.io.File


class HolidayActivity : AppCompatActivity() {

    lateinit var binding: ActivityHolidayBinding
    private lateinit var fileExistenceLiveData: LiveData<Boolean>
    private lateinit var fileDownloaderProgress: FileDownloaderProgress


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHolidayBinding.inflate(layoutInflater)
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

        fileDownloaderProgress = FileDownloaderProgress(this)
        observeFileExistence("january")



        binding.jan .setOnClickListener {
            val intent = Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "जनवरी ")
            intent.putExtra("monthEng", "january")
            intent.putExtra("intValue", 1)
            startActivity(intent)

        }

        binding.feb .setOnClickListener {
            val intent = Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "फरवरी ")
            intent.putExtra("monthEng", "february")
            intent.putExtra("intValue", 2)
            startActivity(intent)

        }
        binding.mar .setOnClickListener {
            val intent = Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "मार्च ")
            intent.putExtra("monthEng", "march")
            intent.putExtra("intValue", 3)
            startActivity(intent)

        }

        binding.apr .setOnClickListener {
            val intent = Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "अप्रैल ")
            intent.putExtra("monthEng", "april")
            intent.putExtra("intValue", 4)
            startActivity(intent)

        }
        binding.may .setOnClickListener {
            val intent = Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "मई ")
            intent.putExtra("monthEng", "may")
            intent.putExtra("intValue", 5)
            startActivity(intent)

        }

        binding.jun .setOnClickListener {
            val intent = Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "जून ")
            intent.putExtra("monthEng", "june")
            intent.putExtra("intValue", 6)
            startActivity(intent)

        }
        binding.jul .setOnClickListener {
            val intent = Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "जुलाई ")
            intent.putExtra("monthEng", "july")
            intent.putExtra("intValue", 7)
            startActivity(intent)

        }

        binding.aug .setOnClickListener {
            val intent = Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "अगस्त ")
            intent.putExtra("monthEng", "august")
            intent.putExtra("intValue", 8)
            startActivity(intent)

        }
        binding.sep .setOnClickListener {
            val intent= Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "सितंबर ")
            intent.putExtra("monthEng", "september")
            intent.putExtra("intValue", 9)
            startActivity(intent)

        }

        binding.oct .setOnClickListener {
            val intent = Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "अक्टूबर ")
            intent.putExtra("monthEng", "october")
            intent.putExtra("intValue", 10)
            startActivity(intent)

        }
        binding.nov .setOnClickListener {
            val intent = Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "नवंबर ")
            intent.putExtra("monthEng", "november")
            intent.putExtra("intValue", 11)
            startActivity(intent)

        }

        binding.dec .setOnClickListener {
            val intent= Intent(this, HolidayListActivity::class.java)
            intent.putExtra("month", "दिसंबर ")
            intent.putExtra("monthEng", "december")
            intent.putExtra("intValue", 12)
            startActivity(intent)

        }



    }

    private fun observeFileExistence(month:String) {
        fileExistenceLiveData = checkFileExistence("holiday.db")

        // Observe changes in file existence
        fileExistenceLiveData.observe(this) { fileExists ->
            if (fileExists) {
                // File exists, proceed with reading its content
                binding.lottieAnimationView.visibility = View.GONE
                binding.loadingstatus.text="महीना के चुनाव करू"

            } else {

                val db = FirebaseFirestore.getInstance()
                val collectionRef = db.collection("SQLdb")
                val documentRef = collectionRef.document("holiday")
                documentRef.get().addOnSuccessListener {
                    if (it != null) {
                        val fileUrl =it.getString("test") ?: ""
                        val actions = it.getString("action") ?: "delete"
                        fileDownloaderProgress.downloadFile(fileUrl, "holiday.db", actions)
                        fileDownloaderProgress.getDownloadStatus().observe(this,{

                            if (it){binding.lottieAnimationView.visibility = View.GONE
                            binding.loadingstatus.text="लोडिंग पूर्ण भ गेल आगू के चुनाव करू"}else{}

                        })
                    }
                    else {
                        Log.d("Firestore", "No such document")
                    }

                }

                // File does not exist, handle accordingly
            }
        }
    }

    fun checkFileExistence(fileName: String): LiveData<Boolean> {
        val fileExistsLiveData = MutableLiveData<Boolean>()
        val dbFolderPath = this.getExternalFilesDir(null)?.absolutePath + File.separator + "test"
        val dbFile = File(dbFolderPath, fileName)
        fileExistsLiveData.value = dbFile.exists()
        return fileExistsLiveData
    }




}