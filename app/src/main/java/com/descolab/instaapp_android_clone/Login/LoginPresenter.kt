package com.descolab.instaapp_android_clone.Login

import android.content.Context
import android.util.Log
import com.descolab.instaapp_android_clone.Model.User
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
import java.util.HashMap

class LoginPresenter(val context: Context,
                     val mView: LoginContract.View):
    BasePresenter(), LoginContract.UserActionListener {
    val apiService: ApiService = ApiClient.getClient().create(ApiService::class.java)

    override fun login(username: String, password: String, token: String) {
        val params = HashMap<String, RequestBody>()
        params["username"] = RequestBody.create(MediaType.parse("text/plain"), username)
        params["password"] = RequestBody.create(MediaType.parse("text/plain"), password)
        params["token"] = RequestBody.create(MediaType.parse("text/plain"), token)
        mView.showProgressDialog(true)
        val call = apiService.login(params)
        call.enqueue(object : Callback<ResponseApiWithData<User>> {
            override fun onResponse(call: Call<ResponseApiWithData<User>>, response: Response<ResponseApiWithData<User>>) {
                mView.showProgressDialog(false)
                val resource = response.body()
                if (response.isSuccessful) {
                    if (response.code() == 200 && resource?.getCode() ==200) {
                        resource.getData()?.let { mView.enterMainActivity(it) }
                    }else if (resource?.getCode() ==400){
                        showDialog(context, "email atau password salah!")
                    }
                } else if (resource?.getCode() == 400) {
                    showDialog(context, "email atau password salah!")
                }else if (resource?.getCode() == 500) {
                    showDialog(context, "Terjadi kesalahan pada server")
                } else {
                    Log.e("Error Code", response.code().toString())
                    Log.e("Error Body", response.errorBody()?.toString())
                    val error = ErrorUtils.parseError(response)
                    showDialog(context, error.getMessage().toString())
                }

            }

            override fun onFailure(call: Call<ResponseApiWithData<User>>, t: Throwable) {
                mView.showProgressDialog(false)
                call.cancel()
                Log.e("onFailure", t.message)
            }
        })
    }


}