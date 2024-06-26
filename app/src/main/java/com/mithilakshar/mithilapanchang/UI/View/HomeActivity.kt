package com.mithilakshar.mithilapanchang.UI.View


import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.view.View

import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.isFlexibleUpdateAllowed
import com.google.android.play.core.ktx.isImmediateUpdateAllowed
import com.google.firebase.firestore.FirebaseFirestore
import com.mithilakshar.mithilapanchang.Dialog.Networkdialog

import com.mithilakshar.mithilapanchang.Notification.NetworkManager
import com.mithilakshar.mithilapanchang.R

import com.mithilakshar.mithilapanchang.Repository.FirestoreRepo

import com.mithilakshar.mithilapanchang.Utility.FirebaseFileDownloader
import com.mithilakshar.mithilapanchang.Utility.dbHelper
import com.mithilakshar.mithilapanchang.ViewModel.BhagwatGitaViewModel
import com.mithilakshar.mithilapanchang.ViewModel.HomeViewModel
import com.mithilakshar.mithilapanchang.databinding.ActivityHomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Locale
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

import android.content.ContentValues.TAG

import android.util.Log
import androidx.lifecycle.LiveData


import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class HomeActivity : AppCompatActivity(), TextToSpeech.OnInitListener {


    lateinit var binding: ActivityHomeBinding
    private lateinit var appUpdateManager: AppUpdateManager
    private val updateType = AppUpdateType.IMMEDIATE

    private val firestoreRepo = FirestoreRepo()

    val mediaPlayer = MediaPlayer()
    var currentPlaybackPosition: Int = 0

    var appbarbannerurls: List<String> = arrayListOf()

    var bannerurls: ArrayList<String> = arrayListOf()
    var bannerImageList: ArrayList<SlideModel> = arrayListOf()

    val handler = Handler(Looper.getMainLooper())

    private var isFabClicked = false

    private var textToSpeech: TextToSpeech? = null
    var speak: String? = null
    var homeBroadcast: String = ""

    val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private lateinit var fileDownloader: FirebaseFileDownloader
    private lateinit var bhagwatgitaviewmodel: BhagwatGitaViewModel

    private var storagePaths: MutableList<String> = mutableListOf()
    private var localFileNames: MutableList<String> = mutableListOf()
    private var actions: MutableList<String> = mutableListOf()

    companion object {
        private const val REQUEST_WRITE_STORAGE = 1
    }

    var G1 = ""
    var G2 = ""

    private lateinit var fileExistenceLiveData: LiveData<Boolean>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        if (updateType == AppUpdateType.FLEXIBLE) {
            appUpdateManager.registerListener(installStateUpdatedListener)
        }




        checkForAppUpdate()

        val networkdialog = Networkdialog(this)
        val networkManager = NetworkManager(this)
        networkManager.observe(this, {
            if (!it) {
                if (!networkdialog.isShowing) {
                    networkdialog.show()
                }

            } else {
                if (networkdialog.isShowing) {
                    networkdialog.dismiss()
                }

            }
        })


        fileDownloader = FirebaseFileDownloader(this)

        val factory = BhagwatGitaViewModel.factory(fileDownloader)
        bhagwatgitaviewmodel =
            ViewModelProvider(this, factory).get(BhagwatGitaViewModel::class.java)
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("SQLdb")
        collectionRef.get().addOnSuccessListener {
            if (it != null) {
                for (document in it) {
                    val documentId = document.id
                    val storagePath = "SQLdb/$documentId"
                    val localFileName = "$documentId.db"
                    val action = document.getString("action") ?: "delete"
                    storagePaths.add(storagePath)
                    localFileNames.add(localFileName)
                    actions.add(action)

                }

                startFileDownloads()

            } else {
                Log.d(TAG, "No documents found in collection.")
            }
        }.addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }

        bhagwatgitaviewmodel.downloadProgressLiveData.observe(this, {

            if (it >=100){

                binding.HomeBoard.visibility=View.VISIBLE
            }

        })

        observeFileExistence()




        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA) // Set usage type (e.g., music, alarm)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC) // Set content type
            .build()

        mediaPlayer.setAudioAttributes(audioAttributes)

        //get Banner for slider
        viewModel.getBannerurlList("home").observe(this, {
            for (i in it) {
                bannerImageList.add(SlideModel(i))
                bannerurls.add(i)
                binding.imageSlider.setImageList(bannerImageList)
            }
        })



        //appbar banner
        lifecycleScope.launch {

            appbarbannerurls = viewModel.getappbarImagelist("appbar")
            homeBroadcast = viewModel.gethomeBroadcast()

            if (homeBroadcast.isNullOrEmpty()) {
                binding.floatingActionButton.visibility = View.GONE
            } else {
                // Perform tasks if homeBroadcast has a value
                binding.floatingActionButton.visibility = View.VISIBLE
            }

            if (appbarbannerurls.size != 0) {
                val random = Random.nextInt(appbarbannerurls.size)
                Glide.with(this@HomeActivity).load(appbarbannerurls.get(random))
                    .into(binding.homeBanner)
            }
            //announce auto
            delayedTask(1000)


        }


        //date

        val currentDate = LocalDate.now()

        val hindiMonth = translateToHindi(currentDate.month.toString())
        val hindiDay = translateToHindiday(currentDate.dayOfWeek.toString())
        val hindidate = translateToHindidate(currentDate.dayOfMonth.toString())


        //text speak auto data.

        firestoreRepo.getspeaktext(currentDate.dayOfMonth.toString().padStart(2, '0'),
            currentDate.month.toString().lowercase(Locale.getDefault())
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }) {

            if (it != null) {
                speak = it

            }

        }

        textToSpeech = TextToSpeech(this, this)

        //text speak broadcast
        binding.floatingActionButton.setOnClickListener {
            isFabClicked = !isFabClicked
            if (isFabClicked) {


                switchFabColor(binding.floatingActionButton)

                delayedBroadcast(500)


            } else {

                stopAudio()
                switchFabColor(binding.floatingActionButton)

            }

        }






        binding.apply {

            textViewDate.text = hindidate
            textViewDay.text = hindiDay
            textViewMonth.text = hindiMonth

        }












        binding.HomeBoard.setOnClickListener {
            val i = Intent(this, BoardDetailActivity::class.java)
            i.putExtra("g1",G1)
            i.putExtra("g2",G2)

            startActivity(i)
            stopAudio()
        }

        binding.alarm.setOnClickListener {
            val i = Intent(this, AlarmActivity::class.java)

            startActivity(i)
            stopAudio()
        }

        binding.calendar.setOnClickListener {
            val i = Intent(this, CalendarActivity::class.java)

            startActivity(i)
            stopAudio()
        }

        binding.holiday.setOnClickListener {
            val i = Intent(this, HolidayActivity::class.java)

            startActivity(i)
            stopAudio()
        }

        binding.eclipse.setOnClickListener {
            val i = Intent(this, EclipseActivity::class.java)

            startActivity(i)
            stopAudio()
        }

        binding.mantra.setOnClickListener {
            val i = Intent(this, MantraActivity::class.java)

            startActivity(i)
            stopAudio()
        }

        binding.katha.setOnClickListener {
            val i = Intent(this, KathaActivity::class.java)

            startActivity(i)
            stopAudio()
        }


    }


    private fun switchFabColor(fab: FloatingActionButton) {
        if (isFabClicked) {
            // Set the original color if it's switched
            fab.backgroundTintList = ContextCompat.getColorStateList(this, R.color.fabColorOriginal)

        } else {
            // Set the switched color
            fab.backgroundTintList = ContextCompat.getColorStateList(this, R.color.fabColorSwitched)
        }
    }


    private val installStateUpdatedListener = InstallStateUpdatedListener {
        if (it.installStatus() == InstallStatus.DOWNLOADED) {

            Toast.makeText(this, "Download Completed", Toast.LENGTH_LONG).show()
            lifecycleScope.launch {
                delay(5.seconds)
                appUpdateManager.completeUpdate()
            }
        }
    }


    private fun checkForAppUpdate() {

        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            val isUpdateAvailable = it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
            val isUpdateAllowed = when (updateType) {

                AppUpdateType.IMMEDIATE -> {
                    it.isImmediateUpdateAllowed
                }

                AppUpdateType.FLEXIBLE -> {
                    it.isFlexibleUpdateAllowed
                }

                else -> false

            }

            if (isUpdateAvailable && isUpdateAllowed) {
                appUpdateManager.startUpdateFlowForResult(
                    it, updateType, this, 113
                )
            }
        }
    }


    override fun onResume() {
        super.onResume()

        if (updateType == AppUpdateType.IMMEDIATE) {
            appUpdateManager.appUpdateInfo.addOnSuccessListener {
                if (it.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                    appUpdateManager.startUpdateFlowForResult(
                        it, updateType, this, 113
                    )

                }
            }
        }

        super.onResume()


        if (currentPlaybackPosition > 0) {
            mediaPlayer.seekTo(currentPlaybackPosition)
            mediaPlayer.start()
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        if (updateType == AppUpdateType.FLEXIBLE) {
            appUpdateManager.unregisterListener(installStateUpdatedListener)
        }

        textToSpeech!!.stop()
        textToSpeech!!.shutdown()
        stopAudio()


    }

    override fun onPause() {
        super.onPause()
        textToSpeech!!.stop()
        textToSpeech!!.shutdown()

        pauseAudio()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 113) {
            if (resultCode != RESULT_OK) {
                println("Something went wrong updating")
            }
        }
    }


    private fun translateToHindi(currentMonth: String): String? {
        // Manually create a mapping for English to Hindi month names
        val monthTranslation: MutableMap<String, String> = HashMap()
        monthTranslation["JANUARY"] = "जनवरी"
        monthTranslation["FEBRUARY"] = "फ़रवरी"
        monthTranslation["MARCH"] = "मार्च"
        monthTranslation["APRIL"] = "अप्रैल"
        monthTranslation["MAY"] = "मई"
        monthTranslation["JUNE"] = "जून"
        monthTranslation["JULY"] = "जुलाई"
        monthTranslation["AUGUST"] = "अगस्त"
        monthTranslation["SEPTEMBER"] = "सितंबर"
        monthTranslation["OCTOBER"] = "अक्टूबर"
        monthTranslation["NOVEMBER"] = "नवंबर"
        monthTranslation["DECEMBER"] = "दिसंबर"
        // Return the translated month name
        return monthTranslation[currentMonth]
    }


    private fun translateToHindiday(currentDay: String): String? {
        // Manually create a mapping for English to Hindi month names
        val monthTranslation: MutableMap<String, String> = HashMap()
        monthTranslation["MONDAY"] = "सोमवार"
        monthTranslation["TUESDAY"] = "मंगलवार"
        monthTranslation["WEDNESDAY"] = "बुधवार"
        monthTranslation["THURSDAY"] = "गुरुवार"
        monthTranslation["FRIDAY"] = "शुक्रवार"
        monthTranslation["SATURDAY"] = "शनिवार"
        monthTranslation["SUNDAY"] = "रविवार"
        // Return the translated month name
        return monthTranslation[currentDay]
    }

    private fun translateToHindidate(date: String): String? {
        // Manually create a mapping for English to Hindi month names
        val nmap: MutableMap<String, String> = HashMap()
        nmap["1"] = "१"
        nmap["2"] = "२"
        nmap["3"] = "३"
        nmap["4"] = "४"
        nmap["5"] = "५"
        nmap["6"] = "६"
        nmap["7"] = "७"
        nmap["8"] = "८"
        nmap["9"] = "९"
        nmap["10"] = "१०"
        nmap["11"] = "११"
        nmap["12"] = "१२"
        nmap["13"] = "१३"
        nmap["14"] = "१४"
        nmap["15"] = "१५"
        nmap["16"] = "१६"
        nmap["17"] = "१७"
        nmap["18"] = "१८"
        nmap["19"] = "१९"
        nmap["20"] = "२०"
        nmap["21"] = "२१"
        nmap["22"] = "२२"
        nmap["23"] = "२३"
        nmap["24"] = "२४"
        nmap["25"] = "२५"
        nmap["26"] = "२६"
        nmap["27"] = "२७"
        nmap["28"] = "२८"
        nmap["29"] = "२९"
        nmap["30"] = "३०"
        nmap["31"] = "३१"
        // Return the translated month name
        return nmap[date]
    }

    override fun onInit(p0: Int) {
        if (p0 == TextToSpeech.SUCCESS) {

            // Set language

        } else {
            // Handle error
        }
    }

    private fun delayedTask(delayMillis: Int) {
        handler.postDelayed({ // Your code to be executed after the delay
            textToSpeech!!.setLanguage(Locale.forLanguageTag("hi"))

            // Speak text
            textToSpeech!!.setPitch(1f)
            textToSpeech!!.setSpeechRate(0.6f)
            textToSpeech!!.speak(speak, TextToSpeech.QUEUE_FLUSH, null, null)
        }, delayMillis.toLong())
    }


    private fun delayedBroadcast(delayMillis: Int) {
        handler.postDelayed({ // Your code to be executed after the delay

            stopAudio()
            playAudio(homeBroadcast)

        }, delayMillis.toLong())
    }


    fun pauseAudio() {
        if (mediaPlayer.isPlaying) {
            currentPlaybackPosition = mediaPlayer.currentPosition
            mediaPlayer.pause()
        }
    }

    fun stopAudio() {

        mediaPlayer.stop()

        mediaPlayer.reset()
        // Reset the media player before preparing a new audio source // Release resources after stopping playback


    }

    fun playAudio(audioURL: String) {


        try {
            // Set the data source for the MediaPlayer
            mediaPlayer.setDataSource(audioURL)

            // Prepare the MediaPlayer asynchronously
            mediaPlayer.prepareAsync()

            // Set a listener to handle when the MediaPlayer is prepared
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
            }

            mediaPlayer.setOnCompletionListener {
                Toast.makeText(this, "audio completed", Toast.LENGTH_SHORT).show()
                isFabClicked = !isFabClicked
            }

        } catch (e: Exception) {
            // Handle error (e.g., show a Toast message)
            e.printStackTrace()
        }


    }


    private fun startFileDownloads() {
        // Start downloading files based on stored paths and filenames
        for (i in storagePaths.indices) {
            downloadFile(storagePaths[i], actions[i], localFileNames[i])
        }
    }


    private fun downloadFile(storagePath: String, action: String, localFileName: String) {
        if (::fileDownloader.isInitialized) {
            fileDownloader.retrieveURL(storagePath, action, localFileName) { downloadedFile ->
                if (downloadedFile != null) {
                    // File downloaded successfully, do something with the file if needed
                    Log.d(TAG, "File downloaded successfully: $downloadedFile")

                    // Notify UI or perform tasks with downloaded file
                    handleDownloadedFile(downloadedFile)
                } else {
                    // Handle the case where download failed
                    Log.d(TAG, "Download failed for file: $localFileName")
                }
            }
        } else {
            Log.e(TAG, "fileDownloader is not initialized.")
        }
    }

    private fun handleDownloadedFile(downloadedFile: File) {

        readFileContent()

    }

    private fun observeFileExistence() {
        fileExistenceLiveData = fileDownloader.checkFileExistence("Gita.db")

        // Observe changes in file existence
        fileExistenceLiveData.observe(this) { fileExists ->
            if (fileExists) {
                // File exists, proceed with reading its content
                readFileContent()
            } else {
                // File does not exist, handle accordingly
            }
        }
    }

    private fun readFileContent() {
        val dbHelper = dbHelper(applicationContext, "Gita.db")
        val av = dbHelper.getRowCount("Gita")

        if (av > 0) {
            val avt = dbHelper.getRowValues("Gita", Random.nextInt(av))
            if (avt != null) {

                 G1 = avt[1].toString()
                 G2 = avt[2].toString()


                if (G1 != null){
                    val formattedText  = G1
                    runOnUiThread {
                        binding.bannerVerse.text = formattedText
                        binding.bannerVerse.invalidate()
                    }

                }else{

                    binding.homeBanner.visibility=View.GONE
                }

            }
        } else {
            // Handle case when file exists but is empty (if applicable)
            binding.homeBanner.visibility=View.GONE

        }
    }


    private fun recreateActivity() {
        val intent = intent // Get the current intent to pass extras if needed

        finish() // Finish the current activity
        startActivity(intent) // Start the activity again
    }

}