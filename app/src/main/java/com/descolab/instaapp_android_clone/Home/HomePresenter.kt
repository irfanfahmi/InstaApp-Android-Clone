package com.descolab.instaapp_android_clone.Home

import android.content.Context
import android.util.Log
import com.descolab.instaapp_android_clone.Model.Feed
import com.descolab.instaapp_android_clone.Service.ApiClient
import com.descolab.instaapp_android_clone.Service.ApiService
import com.descolab.instaapp_android_clone.Service.ResponseApiWithData
import com.descolab.instaapp_android_clone.Service.response.ErrorUtils
import com.descolab.instaapp_android_clone.base.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import java.util.HashMap

class HomePresenter(val context: Context,
                    val mView: HomeContract.View)
    : BasePresenter(), HomeContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)

    override fun loadFeed() {
        val params = HashMap<String, String>()
        val call = apiService.getFeed(getHeaders(), params)
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseApiWithData<ArrayList<Feed>>> {
            override fun onResponse(call: Call<ResponseApiWithData<ArrayList<Feed>>>, response: Response<ResponseApiWithData<ArrayList<Feed>>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (response.code() == 200 && resource?.getCode() ==200) {
                        resource.getData()?.let { mView.showFeed(it) }

                    }
                } else if (response.code() == 401) {
                    autoLogout(context)
                }else if (response.code() == 500) {
                    showDialog(context, "Terjadi kesalahan pada server")
                } else {
                    Log.e("Error Code", response.code().toString())
                    Log.e("Error Body", response.errorBody()?.toString())
                    val error = ErrorUtils.parseError(response)
                    showDialog(context, error.getMessage().toString())
                }
            }

            override fun onFailure(call: Call<ResponseApiWithData<ArrayList<Feed>>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
                Log.e("onFailure", t.message)
            }
        })
    }
}