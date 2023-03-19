package com.offneo.maximustask.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kaopiz.kprogresshud.KProgressHUD
import com.offneo.maximustask.networks.ApiClient
import com.offneo.maximustask.responseModel.ResponseModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class FactsListRepository(var mContext: Context?) {

    var responseModel = MutableLiveData<ResponseModel?>()
    var apiClient = mContext?.let { ApiClient.getInstance(it) }
    var apiInterface = apiClient!!.getApiData()


    private fun getApiCall(
        kProgressHUD: KProgressHUD
    ) {
        apiInterface?.getData(
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<Response<ResponseModel?>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(accountInfoModelResponse: Response<ResponseModel?>) {
                    if (accountInfoModelResponse.isSuccessful) {
                        kProgressHUD.dismiss()
                        if (accountInfoModelResponse.code() == 200 || accountInfoModelResponse.code() == 201) {
                            responseModel.postValue(accountInfoModelResponse.body())
                        }
                    } else {
                        kProgressHUD.dismiss()
                        if (accountInfoModelResponse.code() == 401) {
                            Toast.makeText(mContext, "Unauthorized", Toast.LENGTH_SHORT).show()
                        } else if (accountInfoModelResponse.code() == 404) {
                            Toast.makeText(mContext, "Not Found", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(mContext, "Server Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    kProgressHUD.dismiss()
                    responseModel.postValue(null)
                }

                override fun onComplete() {}
            })
    }

    fun getApiData(
        kProgressHUD: KProgressHUD
    ): LiveData<ResponseModel?> {
        getApiCall(
            kProgressHUD
        )
        return responseModel
    }
}