package com.example.appcentassignment.extenssions

import android.os.Bundle
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.appcentassignment.R

/**
 * Extension function to replace Fragment Safely from AppCompatActivity
 * @param fragment destination Fragment
 * @param tag as String default value is fragment.javaClass.name
 * @param containerViewId as frame layout id default value is R.id.container
 * @param enterAnimation as starting animation
 * @param exitAnimation ending animation
 * @param popEnterAnimation if any
 * @param popExitAnimation if any
 * @return void
 * @author Dawar Malik.
 */
fun AppCompatActivity.replaceFragmentSafely(
    fragment: Fragment,
    tag: String = fragment.javaClass.name,
    allowStateLoss: Boolean = true,
    addToBackStack: Boolean = true,
    @IdRes containerViewId: Int = R.id.container,
    @AnimRes enterAnimation: Int = 0,
    @AnimRes exitAnimation: Int = 0,
    @AnimRes popEnterAnimation: Int = 0,
    @AnimRes popExitAnimation: Int = 0
) {
    val ft = supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        .replace(containerViewId, fragment, tag)
    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}

/**
 * Extension function to replace Fragment Safely from a Fragment
 * @param fragment destination Fragment
 * @param addToBackStack as Boolean default value is true
 * @param bundle as Bundle could be null
 * @return void
 * @author Dawar Malik.
 */
fun Fragment.replaceFragment(
    fragment: Fragment,
    addToBackStack: Boolean = true,
    bundle: Bundle? = null
) {
    val transaction =
        this.parentFragmentManager.beginTransaction()
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.replace(R.id.container, fragment, fragment.javaClass.name)
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    fragment.arguments = bundle
    transaction.commit()
}

/**
 * Extension function to pop back stack
 * @return void
 */
fun AppCompatActivity.backPress() {
    val fragmentManager: FragmentManager = this.supportFragmentManager
    if (fragmentManager.backStackEntryCount >= 1) {
        fragmentManager.popBackStack()
    } else {
        this.finish()
    }
}

//exit on double click

private const val TIME_INTERVAL =
    2000 // # milliseconds, desired time passed between two back presses.
private var backPressed: Long = 0
fun AppCompatActivity.onDoubleBackPressed() {
    if (backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
        this.backPress()
        return
    } else {
        showToastMsg("Tap back button in order to exit")
    }
    backPressed = System.currentTimeMillis()
}