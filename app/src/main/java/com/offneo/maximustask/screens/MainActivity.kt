package com.offneo.maximustask.screens

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.kaopiz.kprogresshud.KProgressHUD
import com.offneo.maximustask.databinding.ActivityMainBinding
import com.offneo.maximustask.responseModel.ResponseModel
import com.offneo.maximustask.viewmodel.VMFactsList


class MainActivity : AppCompatActivity() {

    private var vmFactsList: VMFactsList? = null
    var hud: KProgressHUD? = null
    //binding var
    private lateinit var mBinding: ActivityMainBinding


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        hud = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Please wait")
            .setDimAmount(0.5f)

        vmFactsList =
            VMFactsList(application)

        setObserver()

        mBinding.homeRecyclerSwipeLay.setOnRefreshListener(OnRefreshListener {
            setObserver()
            mBinding.homeRecyclerSwipeLay.isRefreshing = false
        })
    }

    private fun setObserver() {
        hud?.show()
        vmFactsList = ViewModelProvider(this)[VMFactsList::class.java]

        vmFactsList!!.getProductList(
            hud
        )?.observe(
            this as AppCompatActivity,
            Observer<Any?> { allDashboard ->
                allDashboard?.let {
                    hud?.dismiss()
                    setData(it as ResponseModel)
                }
            })
    }

    private fun setData(responseModel: ResponseModel) {

        mBinding.textFacts.text = responseModel.getFact()
        mBinding.textLength.text = responseModel.getLength().toString()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
            .setCancelable(true)
            .setMessage("Are you sure you want to close application?")
            .setPositiveButton("Yes", null)
            .setNegativeButton("No", null)
            .create()
        alertDialog.setOnShowListener {
            val yesButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val noButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            yesButton.setOnClickListener {
                alertDialog.dismiss()
                finish()
            }
            noButton.setOnClickListener { alertDialog.dismiss() }
        }
        alertDialog.show()
    }

}