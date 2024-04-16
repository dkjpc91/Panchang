package com.mithilakshar.mithilapanchang.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.databinding.FragmentBhagwatGitaHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [BhagwatGitaHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BhagwatGitaHomeFragment : Fragment() {

    lateinit var binding:FragmentBhagwatGitaHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentBhagwatGitaHomeBinding.inflate(layoutInflater,container,false)
        return binding.root


    }


}