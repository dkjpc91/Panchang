package com.mithilakshar.mithilapanchang.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.mithilakshar.mithilapanchang.R
import com.mithilakshar.mithilapanchang.databinding.FragmentBhagwatGitaChapterBinding

/**
 * A simple [Fragment] subclass.
 * Use the [BhagwatGitaChapterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BhagwatGitaChapterDetailFragment : Fragment() {


    lateinit var binding:FragmentBhagwatGitaChapterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentBhagwatGitaChapterBinding.inflate(layoutInflater,container,false)
        return binding.root


}}