package com.example.appcentassignment.extenssions

import android.view.View
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

/**
 * An Extension to make view Visible
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * An Extension to make view Gone
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * An Extension to LoadImage
 * @return void
 */
fun ImageView.loadImage(url: String?) {
    url ?: return
    Glide.with(this).load(url).placeholder(R.drawable.ic_launcher_background).into(this)
}