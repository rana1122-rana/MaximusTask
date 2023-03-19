package com.offneo.maximustask.networks

import com.offneo.maximustask.responseModel.ResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiInterface {

    @GET("fact")
    fun getData(
    ): Observable<Response<ResponseModel?>>?

}