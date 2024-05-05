package com.mithilakshar.mithilapanchang.UI.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.models.SlideModel
import com.mithilakshar.mithilapanchang.Adapters.BhagwatGitaChapterAdapter
import com.mithilakshar.mithilapanchang.Models.bhagwatGitaChapterItem
import com.mithilakshar.mithilapanchang.Repository.FirestoreRepo

import com.mithilakshar.mithilapanchang.ViewModel.BhagwatGitaViewModel
import com.mithilakshar.mithilapanchang.ViewModel.HomeViewModel
import com.mithilakshar.mithilapanchang.databinding.FragmentBhagwatGitaChapterBinding
import kotlinx.coroutines.launch



/**
 * A simple [Fragment] subclass.
 * Use the [BhagwatGitaChapterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BhagwatGitaChapterFragment : Fragment() {

    lateinit var binding:FragmentBhagwatGitaChapterBinding


    val viewmodel : BhagwatGitaViewModel by viewModels()
    val viewmodelhome : HomeViewModel by viewModels()


    var list :MutableList<bhagwatGitaChapterItem> = mutableListOf()

    var bannerImageList: ArrayList<SlideModel> = arrayListOf()
    var bannerurls: ArrayList<String> = arrayListOf()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentBhagwatGitaChapterBinding.inflate(layoutInflater,container,false)


        //get Banner
        viewmodelhome.getBannerurlList("gita").observe(requireActivity(), {
            for (i in it) {
                bannerImageList.add(SlideModel(i))
                bannerurls.add(i)

                binding.imageSliderBhagwatgita.setImageList(bannerImageList)
            }
        })



        lifecycleScope.launch {



            viewmodel.getBhagwatGitaChapterDetails().forEach {

                list.add(it)
            }

            val bhagwatGitaChapterAdapter= BhagwatGitaChapterAdapter(list,requireContext())

            binding.BhagwatgitaChapterRecycler.adapter=bhagwatGitaChapterAdapter
        }



        return binding.root
    }


}