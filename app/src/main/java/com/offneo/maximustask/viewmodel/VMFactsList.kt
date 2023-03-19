package com.offneo.maximustask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kaopiz.kprogresshud.KProgressHUD
import com.offneo.maximustask.repository.FactsListRepository
import com.offneo.maximustask.responseModel.ResponseModel

class VMFactsList(application: Application) : AndroidViewModel(application) {

    var responseModel: LiveData<ResponseModel?>? = null
    var factsListRepository = FactsListRepository(application)


    fun getProductList(
        kProgressHUD: KProgressHUD?
    ): LiveData<ResponseModel?>? {
        responseModel = kProgressHUD?.let {
            factsListRepository.getApiData(
                it
            )
        }
        return responseModel
    }
}