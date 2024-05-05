package com.mithilakshar.mithilapanchang.UI.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.models.SlideModel
import com.mithilakshar.mithilapanchang.Adapters.holidayadapter
import com.mithilakshar.mithilapanchang.Dialog.Networkdialog
import com.mithilakshar.mithilapanchang.databinding.ActivityHolidayBinding
import com.mithilakshar.mithilapanchang.Models.holidaydatamodel
import com.mithilakshar.mithilapanchang.Notification.NetworkManager
import com.mithilakshar.mithilapanchang.ViewModel.HomeViewModel
import kotlinx.coroutines.launch

class HolidayActivity : AppCompatActivity() {

    lateinit var binding: ActivityHolidayBinding
    var dataList: List<holidaydatamodel> = arrayListOf()

    val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    var bannerImageList: ArrayList<SlideModel> = arrayListOf()
    var bannerurls: ArrayList<String> = arrayListOf()


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


        viewModel.getBannerurlList("home").observe(this, {
            for (i in it) {
                bannerImageList.add(SlideModel(i))
                bannerurls.add(i)

                binding.holidayimageSlider.setImageList(bannerImageList)
            }
        })

        lifecycleScope.launch {

            dataList=viewModel.getHolidaylist()
            val templist= arrayListOf<holidaydatamodel>()
            dataList.forEach {
                templist.add(holidaydatamodel(it.holidayName,it.holidayDesc,it.imageUrl))
            }



            val holidayadapter=holidayadapter(templist)
            binding.holidayRecyclerView.adapter=holidayadapter

        }




    }
}