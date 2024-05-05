package com.mithilakshar.mithilapanchang.UI.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.FirebaseFirestore
import com.mithilakshar.mithilapanchang.Adapters.eclipseadapter
import com.mithilakshar.mithilapanchang.Adapters.holidayadapter
import com.mithilakshar.mithilapanchang.Adapters.mantraadapter
import com.mithilakshar.mithilapanchang.Dialog.Networkdialog
import com.mithilakshar.mithilapanchang.Models.eclipsedatamodel
import com.mithilakshar.mithilapanchang.Models.holidaydatamodel
import com.mithilakshar.mithilapanchang.Models.mantradatamodel
import com.mithilakshar.mithilapanchang.Notification.NetworkManager
import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.ViewModel.HomeViewModel
import com.mithilakshar.mithilapanchang.databinding.ActivityHolidayBinding
import com.mithilakshar.mithilapanchang.databinding.ActivityMantraBinding
import kotlinx.coroutines.launch

class MantraActivity : AppCompatActivity() {

    lateinit var binding: ActivityMantraBinding
    var dataList: List<mantradatamodel> = arrayListOf()

    val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    var bannerImageList: ArrayList<SlideModel> = arrayListOf()
    var bannerurls: ArrayList<String> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMantraBinding.inflate(layoutInflater)
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


        viewModel.getBannerurlList("home").observe(this, {
            for (i in it) {
                bannerImageList.add(SlideModel(i))
                bannerurls.add(i)

                binding.mantraImageSlider .setImageList(bannerImageList)
            }
        })

        lifecycleScope.launch {

            dataList=viewModel.getMantraList()

            val mantraadapter= mantraadapter(dataList)

            binding.mantraRecyclerView.adapter=mantraadapter

        }



    }
}