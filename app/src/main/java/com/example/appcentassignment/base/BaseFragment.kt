package com.example.appcentassignment.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.appcentassignment.prefrences.PrefManager
import com.example.appcentassignment.utils.Constants
import com.example.appcentassignment.utils.DialogUtils
import com.example.appcentassignment.views.activities.MainActivity
import com.kaopiz.kprogresshud.KProgressHUD
import org.koin.java.KoinJavaComponent

abstract class BaseFragment : Fragment() {

    protected lateinit var mainActivity: MainActivity
    private lateinit var progressDialog: KProgressHUD
    protected val prefManager: PrefManager by KoinJavaComponent.inject(PrefManager::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        progressDialog = DialogUtils.showProgressDialog(mainActivity, cancelable = false)
    }
}