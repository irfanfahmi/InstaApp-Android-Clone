package com.descolab.instaapp_android_clone.Service


import com.descolab.instaapp_android_clone.Model.Comment
import com.descolab.instaapp_android_clone.Model.Feed
import com.descolab.instaapp_android_clone.Model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface ApiService {

    @Multipart
    @POST(ApiURL.LOGIN)
    fun login(
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Call<ResponseApiWithData<User>>

    @GET(ApiURL.GET_FEED)
    fun getFeed(
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String>,
        @QueryMap params: Map<String, String>
    ): Call<ResponseApiWithData<ArrayList<Feed>>>

    @GET(ApiURL.GET_COMMENT)
    fun getComment(
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String>,
        @QueryMap params: Map<String, String>
    ): Call<ResponseApiWithData<ArrayList<Comment>>>

    @Multipart
    @POST(ApiURL.POST_COMMENT)
    fun postComment(
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String>,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Call<ResponseApiWithData<ArrayList<Comment>>>

    @Multipart
    @POST(ApiURL.POST_FEED)
    fun saveFeed(
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String>,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Call<ResponseApiWithData<User>>

    @Multipart
    @POST(ApiURL.POST_FEED)
    fun saveFeed(
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String>,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part gambar0: MultipartBody.Part
    ): Call<ResponseApiWithData<User>>
    @Multipart
    @POST(ApiURL.POST_FEED)
    fun saveFeed(
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String>,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part gambar0: MultipartBody.Part,
        @Part gambar1: MultipartBody.Part
    ): Call<ResponseApiWithData<User>>
    @Multipart
    @POST(ApiURL.POST_FEED)
    fun saveFeed(
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String>,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part gambar0: MultipartBody.Part,
        @Part gambar1: MultipartBody.Part,
        @Part gambar2: MultipartBody.Part
    ): Call<ResponseApiWithData<User>>

    @Multipart
    @POST(ApiURL.POST_REGISTER)
    fun postRegister(
        @HeaderMap headers: Map<String, @JvmSuppressWildcards String>,
        @PartMap params: Map<String, @JvmSuppressWildcards RequestBody>
    ): Call<ResponseApiWithData<User>>

}
