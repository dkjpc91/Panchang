package com.mithilakshar.mithilapanchang.Utility

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.mithilakshar.mithilapanchang.Room.Updates
import com.mithilakshar.mithilapanchang.Room.UpdatesDao
import com.mithilakshar.mithilapanchang.ViewModel.BhagwatGitaViewModel
import com.mithilakshar.mithilapanchang.databinding.ActivityHolidayBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File


class dbDownloader(
    private val updatesDao: UpdatesDao,
    firebaseFileDownloader: FirebaseFileDownloader,
) {


    private var firebaseFileDownloader: FirebaseFileDownloader = firebaseFileDownloader

     fun observeFileExistence(filename:String,lifecycleOwner: LifecycleOwner,coroutineScope: CoroutineScope) {
        var fileExistenceLiveData = checkFileExistence("$filename.db")
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("SQLdb")
        val documentRef = collectionRef.document(filename)
        fileExistenceLiveData.observe(lifecycleOwner) { fileExists ->
            if (fileExists) {


                documentRef.get().addOnSuccessListener {
                    if (it != null) {
                        val actions = it.getString("action") ?: "delete"
                        val fileName = "$filename.db"
                        coroutineScope.launch {
                            val updates = updatesDao.getfileupdate(fileName)
                            if (updates.get(0).uniqueString == actions) {
                                //readFileContent()
                                //binding.lottieAnimationView .visibility=View.GONE
                                //binding.loadingstatus.visibility=View.GONE


                            } else {
                                val holidayupdate = updatesDao.findById(2)
                                holidayupdate.let {
                                    it.uniqueString = actions
                                    updatesDao.update(it)
                                }


                                val storagePath = "SQLdb/$filename"
                                downloadFile(storagePath, "delete", "$filename.db")


                            }
                        }


                        // File exists, proceed with reading its content


                    } else {


                    }


                }

                // File does not exist, handle accordingly
            } else {

                val storagePath = "SQLdb/$filename"
                downloadFile(storagePath, "delete", "$filename.db")


                documentRef.get().addOnSuccessListener {
                    if (it != null) {
                        val fileUrl = it.getString("test") ?: ""
                        val actions = it.getString("action") ?: "delete"
                        val fileName = "$filename.db"
                        coroutineScope.launch {

                            val holiday = Updates(id = 2,fileName = "holiday.db", uniqueString = "holiday")
                            updatesDao.insert(holiday)

                            val holidayupdate = updatesDao.findById(2)
                            holidayupdate.let {
                                it.uniqueString = actions
                                updatesDao.update(it)
                            }

                        }

                    }


                }

            }
        }

    }



    private fun checkFileExistence(fileName: String): LiveData<Boolean> {
        val fileExistsLiveData = MutableLiveData<Boolean>()
        val dbFolderPath = File.separator + "test"
        val dbFile = File(dbFolderPath, fileName)
        fileExistsLiveData.value = dbFile.exists()
        return fileExistsLiveData
    }


    private fun downloadFile(storagePath: String, action: String, localFileName: String) {

            firebaseFileDownloader.retrieveURL(storagePath, action, localFileName) { downloadedFile ->
                if (downloadedFile != null) {
                    // File downloaded successfully, do something with the file if needed
                    Log.d(ContentValues.TAG, "File downloaded successfully: $downloadedFile")

                    // Notify UI or perform tasks with downloaded file
                } else {
                    // Handle the case where download failed
                    Log.d(ContentValues.TAG, "Download failed for file: $localFileName")
                }
            }
        }
    }

