package com.mithilakshar.mithilapanchang.UI.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mithilakshar.mithilapanchang.Models.BhagwatGitaVerseItem
import com.mithilakshar.mithilapanchang.R
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.models.SlideModel
import com.mithilakshar.mithilapanchang.Adapters.BhagwatGitaVerseAdapter
import com.mithilakshar.mithilapanchang.ViewModel.BhagwatGitaViewModel
import com.mithilakshar.mithilapanchang.ViewModel.HomeViewModel
import com.mithilakshar.mithilapanchang.databinding.FragmentBhagwatGitaVersesBinding
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [BhagwatGitaVersesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BhagwatGitaVersesFragment : Fragment() {

    lateinit var binding:FragmentBhagwatGitaVersesBinding
    val viewmodel : BhagwatGitaViewModel by viewModels()
    val chapternumberargs by navArgs<BhagwatGitaVersesFragmentArgs>()

    var list :MutableList<BhagwatGitaVerseItem> = mutableListOf()

    var bannerImageList: ArrayList<SlideModel> = arrayListOf()
    var bannerurls: ArrayList<String> = arrayListOf()
    val viewmodelhome : HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentBhagwatGitaVersesBinding.inflate(layoutInflater,container,false)



        //get Banner
        viewmodelhome.getBannerurlList("gita").observe(requireActivity(), {
            for (i in it) {
                bannerImageList.add(SlideModel(i))
                bannerurls.add(i)

                binding.imageSliderBhagwatgita.setImageList(bannerImageList)
            }
        })



        lifecycleScope.launch {

            viewmodel.getBhagwatGitaVerseDetails(chapternumberargs.chapternumber).forEach {

                list.add(it)
            }

            val bhagwatGitaVerseAdapter= BhagwatGitaVerseAdapter(list,requireContext())
            binding.BhagwatgitaVerseRecycler.adapter=bhagwatGitaVerseAdapter
        }


        requireActivity().onBackPressedDispatcher.addCallback(this,true,{

            if (true){

                findNavController().navigate(R.id.action_bhagwatGitaVersesFragment_to_bhagwatGitaChapterFragment)

            }else{
                requireActivity().onBackPressed()
            }
        })


        return binding.root
    }


}