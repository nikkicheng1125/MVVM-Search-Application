package com.apolis.gm_assignment.ui

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.apolis.gm_assignment.R

open class BaseActivity: AppCompatActivity() {

    fun showMessage(msg: String, buttonTitleId: Int) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.dialog_title_error)
            .setMessage(msg)
            .setPositiveButton(R.string.btn_ok) {
                    dialog, which ->
                dialog.dismiss()
            }.create()
        dialog.show()
    }
}