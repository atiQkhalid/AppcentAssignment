package com.example.appcentassignment.extenssions

import android.os.Bundle
import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.appcentassignment.R

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


fun Fragment.replaceFragmentInFragment(
    container: View,
    fragment: Fragment,
    addToBackStack: Boolean = true,
    bundle: Bundle? = null
) {
    val transaction =
        this.parentFragmentManager.beginTransaction()
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.replace(container.id, fragment, fragment.javaClass.name)
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    fragment.arguments = bundle
    transaction.commit()
}