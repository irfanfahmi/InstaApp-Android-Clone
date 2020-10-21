package com.descolab.instaapp_android_clone.Register

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

class RegisterPresenter(val context: Context,
                        val mView: RegisterContract.View)
    : BasePresenter(), RegisterContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)

    override fun saveRegister(nama: String, phone: String,address: String,username: String, email: String, password: String) {
        val params = HashMap<String, RequestBody>()
        params["nama"] = RequestBody.create(MediaType.parse("text/plain"), nama)
        params["phone"] = RequestBody.create(MediaType.parse("text/plain"), phone)
        params["address"] = RequestBody.create(MediaType.parse("text/plain"), address)
        params["username"] = RequestBody.create(MediaType.parse("text/plain"), username)
        params["email"] = RequestBody.create(MediaType.parse("text/plain"), email)
        params["password"] = RequestBody.create(MediaType.parse("text/plain"), password)

        mView.showProgressDialog(true)
        val call = apiService.postRegister(getHeaders(), params)
        call.enqueue(object : Callback<ResponseApiWithData<User>> {
            override fun onResponse(call: Call<ResponseApiWithData<User>>, response: Response<ResponseApiWithData<User>>) {
                mView.showProgressDialog(false)
                val resource = response.body()
                if (response.isSuccessful) {
                    Log.d("response Code", response.code().toString())
                    Log.d("resource Code", resource?.getCode().toString())
                    if (response.code() == 200 && resource?.getCode() ==200) {
                        mView.backToLogin()
                    }else if (response.code() == 200 && resource?.getCode() ==500){
                        showDialog(context, "Terjadi kesalahan pada server")
                    }
                } else if (response.code() == 401) {
                    autoLogout(context)
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