package com.mithilakshar.mithilapanchang.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mithilakshar.mithilapanchang.R
import androidx.activity.addCallback
import com.mithilakshar.mithilapanchang.ViewModel.BhagwatGitaViewModel
import com.mithilakshar.mithilapanchang.databinding.FragmentBhagwatGitaVerseDetailsBinding
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [BhagwatGitaVerseDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BhagwatGitaVerseDetailsFragment : Fragment() {

    lateinit var binding:FragmentBhagwatGitaVerseDetailsBinding
    val viewmodel : BhagwatGitaViewModel by viewModels()
    val versenumber by navArgs<BhagwatGitaVerseDetailsFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentBhagwatGitaVerseDetailsBinding.inflate(layoutInflater,container,false)

        lifecycleScope.launch {





        }



        requireActivity().onBackPressedDispatcher.addCallback(this,true,{

            if (true){

                findNavController().navigate(R.id.action_bhagwatGitaVerseDetailsFragment_to_bhagwatGitaVersesFragment)

            }else{
                requireActivity().onBackPressed()
            }
        })


        return binding.root
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