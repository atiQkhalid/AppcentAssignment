package com.example.appcentassignment.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appcentassignment.R
import com.example.appcentassignment.base.BaseFragment
import com.example.appcentassignment.databinding.FragmentHomeBinding
import com.example.appcentassignment.extenssions.replaceFragmentInFragment
import com.example.appcentassignment.views.fragment.curiosity.CuriosityFragment
import com.example.appcentassignment.views.fragment.opportunity.OpportunityFragment
import com.example.appcentassignment.views.fragment.spirit.SpiritFragment


class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceFragmentInFragment(binding.flFragment, CuriosityFragment())
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.curiosityFragment -> {
                    replaceFragmentInFragment(binding.flFragment, CuriosityFragment(), true, null)
                }
                R.id.opportunityFragment -> {
                    replaceFragmentInFragment(binding.flFragment, OpportunityFragment(), true, null)
                }
                R.id.spiritFragment -> {
                    replaceFragmentInFragment(binding.flFragment, SpiritFragment(), true, null)
                }
            }
            true
        }
    }
}