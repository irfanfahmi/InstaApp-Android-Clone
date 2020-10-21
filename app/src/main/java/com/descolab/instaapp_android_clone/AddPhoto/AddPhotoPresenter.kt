package com.descolab.instaapp_android_clone.AddPhoto

import android.content.Context
import android.util.Log
import com.descolab.instaapp_android_clone.Model.User
import com.descolab.instaapp_android_clone.Service.ApiClient
import com.descolab.instaapp_android_clone.Service.ApiService
import com.descolab.instaapp_android_clone.Service.ResponseApiWithData
import com.descolab.instaapp_android_clone.Service.response.ErrorUtils
import com.descolab.instaapp_android_clone.base.BasePresenter
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AddPhotoPresenter(val context: Context,
                        val mView: AddPhotoContract.View)
    : BasePresenter(), AddPhotoContract.UserActionListener {
    private val apiService : ApiService = ApiClient.getClient().create(ApiService::class.java)
    override fun saveFeed(
        deskripsi: String,
        lokasi: String,
        id_user: String,
        tgl_post: String,
        filePicture1: File?,
        filePicture2: File?,
        filePicture3: File?
    ) {
        val params = HashMap<String, RequestBody>()
        params["deskripsi"] = RequestBody.create(MediaType.parse("text/plain"), deskripsi)
        params["lokasi"] = RequestBody.create(MediaType.parse("text/plain"), lokasi)
        params["id_user"] = RequestBody.create(MediaType.parse("text/plain"), id_user)
        params["tgl_post"] = RequestBody.create(MediaType.parse("text/plain"), tgl_post)

        var call: Call<ResponseApiWithData<User>>? = null
        if(filePicture1!=null){
            if (filePicture1!==null && filePicture2!==null && filePicture3==null){
                // isi gambar 1 dan 2
                val pathFolder0 = RequestBody.create(MediaType.parse("image/*"), filePicture1)
                val pathFolder1 = RequestBody.create(MediaType.parse("image/*"), filePicture2)
                val gambar0 = MultipartBody.Part.createFormData("gambar0", "image", pathFolder0)
                val gambar1 = MultipartBody.Part.createFormData("gambar1", "image", pathFolder1)
                Log.d("Test", "Masuk Sini file" +filePicture1)
                Log.d("Test", "Masuk Sini gambar0" +gambar0)
                call = apiService.saveFeed(getHeaders(),params,gambar0,gambar1)
            }else if (filePicture1!==null && filePicture2==null && filePicture3==null){
                // isi gambar 1
                val pathFolder0 = RequestBody.create(MediaType.parse("image/*"), filePicture1)
                val gambar0 = MultipartBody.Part.createFormData("gambar0", "image", pathFolder0)
                Log.d("Test", "Masuk Sini file" +filePicture1)
                Log.d("Test", "Masuk Sini gambar0" +gambar0)
                call = apiService.saveFeed(getHeaders(),params,gambar0)
            }else if (filePicture1!==null && filePicture2!==null && filePicture3!==null){
                val pathFolder0 = RequestBody.create(MediaType.parse("image/*"), filePicture1)
                val pathFolder1 = RequestBody.create(MediaType.parse("image/*"), filePicture2)
                val pathFolder2 = RequestBody.create(MediaType.parse("image/*"), filePicture3)
                val gambar0 = MultipartBody.Part.createFormData("gambar0", "image", pathFolder0)
                val gambar1 = MultipartBody.Part.createFormData("gambar1", "image", pathFolder1)
                val gambar2 = MultipartBody.Part.createFormData("gambar2", "image", pathFolder2)
                Log.d("Test", "Masuk Sini file" +filePicture1)
                Log.d("Test", "Masuk Sini gambar0" +gambar0)
                call = apiService.saveFeed(getHeaders(),params,gambar0,gambar1,gambar2)
            }
        }else{
            call = apiService.saveFeed(getHeaders(),params)
        }

        mView.showProgressDialog(true)
        call?.enqueue(object : Callback<ResponseApiWithData<User>> {
            override fun onResponse(call: Call<ResponseApiWithData<User>>, response: Response<ResponseApiWithData<User>>) {
                mView.showProgressDialog(false)
                if (response.isSuccessful) {
                    val resource = response.body()
                    if (response.code() == 200 && resource?.getCode()==200) {
                        resource.getData()?.let { mView.backToProfile(it) }
                    } else {
                        showDialog(context, "Gagal di tambahkan!")
                    }
                } else if (response.code() == 500) {
                    showDialog(context, "Terjadi kesalahan pada server")
                } else {
                    Log.e("Error Code", response.code().toString())
                    Log.e("Error Body", response.errorBody()!!.toString())
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