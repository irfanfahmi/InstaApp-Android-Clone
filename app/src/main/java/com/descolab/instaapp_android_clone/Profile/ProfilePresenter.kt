package com.descolab.instaapp_android_clone.Profile

import android.content.Context
import com.descolab.instaapp_android_clone.Service.ApiClient
import com.descolab.instaapp_android_clone.Service.ApiService
import com.descolab.instaapp_android_clone.base.BasePresenter

class ProfilePresenter (val context: Context,
                        val mView: ProfileContract.View)
    : BasePresenter(), ProfileContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)

}