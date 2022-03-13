package com.ahmedc2l.currencyfixer.app.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ahmedc2l.currencyfixer.R
import com.ahmedc2l.currencyfixer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainActivityEventsListener {
    private val myDialog: MyDialog by lazy { MyDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    override fun showLoading() {
        myDialog.showLoadingDialog()
    }

    override fun hideLoading() {
        myDialog.hideLoadingDialog()
    }

    override fun showErrorMessage(message: String) {
        myDialog.showErrorMessageDialog(message)
    }
}