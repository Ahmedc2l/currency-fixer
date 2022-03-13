package com.ahmedc2l.currencyfixer.app.main

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ahmedc2l.currencyfixer.R

class MyDialog(private val context: Context) {
    private val dialogLoading: Dialog
    private val dialogError: Dialog

    init {
        dialogLoading = getDialogInstance(R.layout._dialog_loading, Gravity.CENTER, false)
        dialogError = getDialogInstance(R.layout._dialog_error, Gravity.CENTER, true)
    }

    fun showLoadingDialog(){
        dialogLoading.show()
    }
    fun hideLoadingDialog(){
        dialogLoading.dismiss()
    }

    fun showErrorMessageDialog(message: String){
        dialogError.findViewById<TextView>(R.id.tv_error_msg).text = message
        dialogError.show()
    }

    private fun getDialogInstance(layout: Int, gravity: Int, isCancelable: Boolean) = Dialog(context).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(isCancelable)
        setContentView(layout)
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
            setGravity(gravity)
        }
    }
}