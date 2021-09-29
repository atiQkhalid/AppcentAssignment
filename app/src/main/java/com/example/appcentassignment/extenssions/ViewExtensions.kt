package com.example.appcentassignment.extenssions

import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.appcentassignment.App
import com.example.appcentassignment.R

/**
 * Extension function to show toast message
 */
fun Any.showToastMsg(message: String) {
    Toast.makeText(App.getAppContext(), message, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String?) {
    url ?: return
    Glide.with(this).load(url).placeholder(R.drawable.ic_launcher_background).into(this)
}