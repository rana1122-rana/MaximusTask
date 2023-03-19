package com.offneo.maximustask.responseModel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ResponseModel {

    @SerializedName("fact")
    @Expose
    private var fact: String? = null

    @SerializedName("length")
    @Expose
    private var length = 0

    fun getFact(): String? {
        return fact
    }

    fun setFact(fact: String?) {
        this.fact = fact
    }

    fun getLength(): Int {
        return length
    }

    fun setLength(length: Int) {
        this.length = length
    }

}