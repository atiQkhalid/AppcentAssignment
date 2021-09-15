package com.example.appcentassignment.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.appcentassignment.views.fragment.Curiosity.CuriosityFragment
import com.example.appcentassignment.views.fragment.Opportunity.OpportunityFragment
import com.example.appcentassignment.views.fragment.Spirit.SpiritFragment

internal class TabAdapter(fm: FragmentManager, private val fragmentCount: Int)
    : FragmentPagerAdapter(fm) {
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