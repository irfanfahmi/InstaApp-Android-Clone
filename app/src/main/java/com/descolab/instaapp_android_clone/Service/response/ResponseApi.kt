package com.descolab.instaapp_android_clone.Service

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseApi {

    @SerializedName("code")
    @Expose
    private var code: Int? = null
    @SerializedName("message")
    @Expose
    private var message: String? = null

    fun getCode(): Int? {
        return code
    }

    fun setCode(code: Int?) {
        this.code = code
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }
}