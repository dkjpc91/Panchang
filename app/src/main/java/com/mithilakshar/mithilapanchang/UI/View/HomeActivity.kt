package com.mithilakshar.mithilapanchang.UI.View

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Update
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.isFlexibleUpdateAllowed
import com.google.android.play.core.ktx.isImmediateUpdateAllowed
import com.google.android.play.core.ktx.startUpdateFlowForResult
import com.mithilakshar.mithilapanchang.Api.BhagwatGitaRetrofitInstance
import com.mithilakshar.mithilapanchang.BhagwatGitaVerseFragment
import com.mithilakshar.mithilapanchang.Models.BhagwatGitaVerseItem
import com.mithilakshar.mithilapanchang.Models.Commentary
import com.mithilakshar.mithilapanchang.Models.Translation
import com.mithilakshar.mithilapanchang.Models.bhagwatGitaChapterItem
import com.mithilakshar.mithilapanchang.Repository.BhagwatGitaApiRepo
import com.mithilakshar.mithilapanchang.Repository.BhagwatGitaRoomRepo
import com.mithilakshar.mithilapanchang.Room.Dao.BhagwatGitaChapterDao
import com.mithilakshar.mithilapanchang.Room.Dao.BhagwatGitaVerseDao
import com.mithilakshar.mithilapanchang.Room.Database.BhagwatGitaChapterDatabase
import com.mithilakshar.mithilapanchang.Room.Database.BhagwatGitaVerseDatabase
import com.mithilakshar.mithilapanchang.Room.Entity.BhagwatGitaChapter
import com.mithilakshar.mithilapanchang.Room.Entity.BhagwatGitaVerse

import com.mithilakshar.mithilapanchang.ViewModel.HomeViewModel
import com.mithilakshar.mithilapanchang.databinding.ActivityHomeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.time.Duration.Companion.seconds


class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private lateinit var appUpdateManager: AppUpdateManager
    private val updateType=AppUpdateType.IMMEDIATE

    val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    var bannerImageList: ArrayList<SlideModel> = arrayListOf()
    var bannerurls: ArrayList<String> = arrayListOf()
    var bhagwatGitaChapterlist: ArrayList<bhagwatGitaChapterItem> = arrayListOf()



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appUpdateManager=AppUpdateManagerFactory.create(applicationContext)
        if (updateType==AppUpdateType.FLEXIBLE){
            appUpdateManager.registerListener(installStateUpdatedListener)
        }
        checkForAppUpdate()



        var dao=BhagwatGitaChapterDatabase.getdbcopy(this).chapterdao()
        var bhagwatGitaRoomRepo = BhagwatGitaRoomRepo(dao)


        //get Banner
        viewModel.getBannerurlList().observe(this, {
            for (i in it) {
                bannerImageList.add(SlideModel(i))
                bannerurls.add(i)

                binding.imageSlider.setImageList(bannerImageList)
            }
        })


        bhagwatGitaRoomRepo.readBhagwatGitaChapter().observe(this,{


            binding.shlok.text=it[0].name
        })

        //date

        val currentDate = LocalDate.now()

        val hindiMonth = translateToHindi(currentDate.month.toString())
        val hindiDay = translateToHindiday (currentDate.dayOfWeek.toString())
        val hindidate = translateToHindidate (currentDate.dayOfMonth.toString())

        binding.apply {

            textViewDate.text=hindidate
            textViewDay.text=hindiDay
            textViewMonth.text=  hindiMonth

        }

        binding.Gita.setOnClickListener {
            val i =Intent(this,BhagwatGita::class.java)
            startActivity(i)
        }
        binding.HomeBoard.setOnClickListener {
            val i =Intent(this,BoardDetail::class.java)
            startActivity(i)
        }





















    }







    private val installStateUpdatedListener=InstallStateUpdatedListener{
        if (it.installStatus()==InstallStatus.DOWNLOADED){

            Toast.makeText(this,"Download Completed",Toast.LENGTH_LONG).show()
            lifecycleScope.launch {
                delay(5.seconds)
                appUpdateManager.completeUpdate()
            }
        }
    }





    private fun checkForAppUpdate(){

        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            val isUpdateAvailable=it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
            val isUpdateAllowed=when(updateType){

                AppUpdateType.IMMEDIATE->{it.isImmediateUpdateAllowed}
                AppUpdateType.FLEXIBLE->{it.isFlexibleUpdateAllowed}
                else->false

            }

            if (isUpdateAvailable && isUpdateAllowed){
                appUpdateManager.startUpdateFlowForResult(
                    it,updateType,this,113
                )
            }
        }
    }




    override fun onResume() {
        super.onResume()

        if (updateType==AppUpdateType.IMMEDIATE){
            appUpdateManager.appUpdateInfo.addOnSuccessListener {
                if (it.updateAvailability()==UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS)
                {
                    appUpdateManager.startUpdateFlowForResult(
                        it,updateType,this,113
                    )

                }
            }
        }


    }





    override fun onDestroy() {
        super.onDestroy()
        if (updateType==AppUpdateType.FLEXIBLE){
            appUpdateManager.unregisterListener(installStateUpdatedListener)
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==113){
            if (resultCode!= RESULT_OK){
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









}
