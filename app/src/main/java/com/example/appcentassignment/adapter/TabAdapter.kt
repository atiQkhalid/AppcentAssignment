package com.example.appcentassignment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.appcentassignment.views.fragment.curiosity.CuriosityFragment
import com.example.appcentassignment.views.fragment.opportunity.OpportunityFragment
import com.example.appcentassignment.views.fragment.spirit.SpiritFragment

@Suppress("DEPRECATION")
internal class TabAdapter(
    fm: FragmentManager,
    var fragmentCount: Int
) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CuriosityFragment()
            }
            1 -> {
                OpportunityFragment()
            }
            2 -> {
                SpiritFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return fragmentCount
    }
}