package com.example.appcentassignment.views.activities

import android.os.Bundle
import com.example.appcentassignment.base.BaseActivity
import com.example.appcentassignment.databinding.ActivityMainBinding
import com.example.appcentassignment.extenssions.replaceFragmentSafely
import com.example.appcentassignment.views.fragment.HomeFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        replaceFragmentSafely(HomeFragment())

    }
}