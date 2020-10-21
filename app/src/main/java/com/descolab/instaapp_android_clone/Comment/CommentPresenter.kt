package com.descolab.instaapp_android_clone.Comment

import android.content.Context
import android.util.Log
import com.descolab.instaapp_android_clone.Model.Comment
import com.descolab.instaapp_android_clone.Service.ApiClient
import com.descolab.instaapp_android_clone.Service.ApiService
import com.descolab.instaapp_android_clone.Service.ResponseApiWithData
import com.descolab.instaapp_android_clone.Service.response.ErrorUtils
import com.descolab.instaapp_android_clone.base.BasePresenter
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class CommentPresenter (val context: Context, val mView: CommentContract.View)
    : BasePresenter(),CommentContract.UserActionListener{
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)

    override fun loadComment(idComment: String) {
        val params = HashMap<String, String>()
        params["id_feed"] = idComment
        val call = apiService.getComment(getHeaders(), params)
        mView.showProgressDialog(true)
        call.enqueue(object : Callback<ResponseApiWithData<ArrayList<Comment>>> {
            override fun onResponse(call: Call<ResponseApiWithData<ArrayList<Comment>>>, response: Response<ResponseApiWithData<ArrayList<Comment>>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (response.code() == 200 && resource?.getCode() ==200) {
                        resource.getData()?.let { mView.showComment(it) }
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

            override fun onFailure(call: Call<ResponseApiWithData<ArrayList<Comment>>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
                Log.e("onFailure", t.message)
            }
        })
    }

    override fun sendComment(
        feedId: String,
        myId: String,
        message: String,
        date: String
    ) {
        val params = java.util.HashMap<String, RequestBody>()
        params["id_feed"] = RequestBody.create(MediaType.parse("text/plain"), feedId)
        params["id_user"] = RequestBody.create(MediaType.parse("text/plain"), myId)
        params["message"] = RequestBody.create(MediaType.parse("text/plain"), message)
        params["date"] = RequestBody.create(MediaType.parse("text/plain"), date)
        val call = apiService.postComment(getHeaders(), params)
        call.enqueue(object : Callback<ResponseApiWithData<ArrayList<Comment>>> {
            override fun onResponse(call: Call<ResponseApiWithData<ArrayList<Comment>>>, response: Response<ResponseApiWithData<ArrayList<Comment>>>) {
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (response.code() == 200 && resource?.getCode() ==200) {
                         resource.getData()?.let { mView.backToActivity(it) }
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

            override fun onFailure(call: Call<ResponseApiWithData<ArrayList<Comment>>>, t: Throwable) {
                call.cancel()
                Log.e("onFailure", t.message)
            }
        })
    }

}