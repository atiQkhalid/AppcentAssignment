package com.example.appcentassignment.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.appcentassignment.R
import com.example.appcentassignment.extenssions.loadImage
import com.example.appcentassignment.models.response.Photo
import com.kaopiz.kprogresshud.KProgressHUD

class DialogUtils {
    companion object {

        fun showProgressDialog(context: Context): KProgressHUD {
            return showProgressDialog(context, "")
        }

        fun showProgressDialog(
            context: Context,
            message: String = "please wait",
            cancelable: Boolean = false
        ): KProgressHUD {
            return KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(message)
                .setCancellable(cancelable)
                .setMaxProgress(100) as KProgressHUD
        }

        private lateinit var alertDialog: AlertDialog

        fun showPopup(photo: Photo, dialogView: View) {

            val image = dialogView.findViewById<ImageView>(R.id.photo)
            image.loadImage(photo.img_src)
            val dateOfPhoto = dialogView.findViewById<TextView>(R.id.dateOfPhoto)
            dateOfPhoto.text = photo.earth_date
            val carName = dialogView.findViewById<TextView>(R.id.carName)
            carName.text = photo.rover.name
            val missionStatus = dialogView.findViewById<TextView>(R.id.missionStatus)
            missionStatus.text = photo.rover.status
            val dateOfLaunch = dialogView.findViewById<TextView>(R.id.dateOfLaunch)
            dateOfLaunch.text = photo.rover.launch_date
            val camName = dialogView.findViewById<TextView>(R.id.camName)
            camName.text = photo.camera.full_name
            val landOfCar = dialogView.findViewById<TextView>(R.id.landofcar)
            landOfCar.text = photo.rover.landing_date

            val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(dialogView.context)
            dialogBuilder.setOnDismissListener { }
            (DialogInterface.OnDismissListener { })
            dialogBuilder.setView(dialogView)

            alertDialog = dialogBuilder.create()
            alertDialog.show()
        }
    }
}