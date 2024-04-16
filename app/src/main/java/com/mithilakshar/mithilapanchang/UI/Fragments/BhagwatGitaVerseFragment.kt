package com.mithilakshar.mithilapanchang.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.databinding.FragmentBhagwatGitaVerseBinding

/**
 * A simple [Fragment] subclass.
 * Use the [BhagwatGitaVerseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BhagwatGitaVerseFragment : Fragment() {

    lateinit var binding:FragmentBhagwatGitaVerseBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentBhagwatGitaVerseBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}