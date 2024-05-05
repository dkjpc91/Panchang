package com.mithilakshar.mithilapanchang.UI.View

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.denzcoskun.imageslider.models.SlideModel
import com.mithilakshar.mithilapanchang.Dialog.Networkdialog
import com.mithilakshar.mithilapanchang.Notification.NetworkManager

import com.mithilakshar.mithilapanchang.R

import com.mithilakshar.mithilapanchang.ViewModel.HomeViewModel
import com.mithilakshar.mithilapanchang.databinding.ActivityCalendarBinding
import com.mithilakshar.mithilapanchang.UI.Fragments.mayfragment
import java.time.LocalDate

class CalendarActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalendarBinding

    val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }


    var bannerImageList: ArrayList<SlideModel> = arrayListOf()
    var bannerurls: ArrayList<String> = arrayListOf()



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCalendarBinding.inflate(layoutInflater)
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



        //get Banner
        viewModel.getBannerurlList("home").observe(this, {
            for (i in it) {
                bannerImageList.add(SlideModel(i))
                bannerurls.add(i)

                binding.calendarimageSlider.setImageList(bannerImageList)
            }
        })

        replaceFragment(mayfragment())





    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager // Get FragmentManager from Activity
        val transaction = fragmentManager.beginTransaction() // Begin transaction
        transaction.replace(R.id.fragmentContainer, fragment) // Replace container with fragment
        transaction.commit() // Commit transaction
    }





}