import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.mithilakshar.mithilapanchang.Room.Updates
import com.mithilakshar.mithilapanchang.Room.UpdatesDao
import com.mithilakshar.mithilapanchang.Utility.FirebaseFileDownloader
import com.mithilakshar.mithilapanchang.ViewModel.BhagwatGitaViewModel
import kotlinx.coroutines.launch
import java.io.File

class FileManager(
     // Assuming you have a FileDownloader class
    private val updatesDao: UpdatesDao, // Assuming you have an UpdatesDao class
    private val context: Context // Required for accessing resources and lifecycle
) {
    lateinit var fileDownloader: FirebaseFileDownloader



    fun downloadFile(storagePath: String, action: String, localFileName: String) {
        fileDownloader = FirebaseFileDownloader(context)
        if (::fileDownloader.isInitialized) {
            fileDownloader.retrieveURL(storagePath, action, localFileName) { downloadedFile ->
                if (downloadedFile != null) {

                    handleDownloadedFile(downloadedFile)
                } else {

                }
            }
        } else {

        }
    }

    private fun handleDownloadedFile(downloadedFile: File) {
        // Handle the downloaded file
    }

    fun checkFileExistence(fileName: String): LiveData<Boolean> {
        val fileExistsLiveData = MutableLiveData<Boolean>()
        val dbFolderPath = context.getExternalFilesDir(null)?.absolutePath + File.separator + "test"
        val dbFile = File(dbFolderPath, fileName)
        fileExistsLiveData.value = dbFile.exists()
        return fileExistsLiveData
    }

    fun observeFileExistence(month: String) {
        val fileExistenceLiveData = checkFileExistence("calander.db")
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("SQLdb")
        val documentRef = collectionRef.document("calander")

        fileExistenceLiveData.observe((context as LifecycleOwner)) { fileExists ->
            if (fileExists) {
                documentRef.get().addOnSuccessListener { document ->
                    if (document != null) {
                        val actions = document.getString("action") ?: "delete"
                        val fileName = "calander.db"

                        context.lifecycleScope.launch {
                            val updates = updatesDao.getfileupdate(fileName)
                            if (updates.get(0).uniqueString == actions) {


                            } else {
                                // Update the database and download new file
                                val holidayupdate = updatesDao.findById(3)
                                holidayupdate.let {
                                    it.uniqueString = actions
                                    updatesDao.update(it)
                                }

                                val storagePath = "SQLdb/calander"
                                downloadFile(storagePath, "delete", "calander.db")

                            }
                        }
                    } else {
                        // Handle document not found
                    }
                }
            } else {
                // File does not exist, download it
                val storagePath = "SQLdb/calander"
                downloadFile(storagePath, "delete", "calander.db")

                documentRef.get().addOnSuccessListener { document ->
                    if (document != null) {
                        val fileUrl = document.getString("test") ?: ""
                        val actions = document.getString("action") ?: "delete"

                        context.lifecycleScope.launch {
                            val calander = Updates(id = 3, fileName = "calander.db", uniqueString = "calander")
                            updatesDao.insert(calander)
                            val holidayupdate = updatesDao.findById(3)
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




}
