package com.descolab.instaapp_android_clone.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id_user")
    @Expose
    private var id_user: String? = null
    @SerializedName("nama_lengkap")
    @Expose
    private var nama_lengkap: String? = null
    @SerializedName("alamat")
    @Expose
    private var alamat: String? = null
    @SerializedName("no_hp")
    @Expose
    private var no_hp: String? = null
    @SerializedName("username")
    @Expose
    private var username: String? = null
    @SerializedName("email")
    @Expose
    private var email: String? = null
    @SerializedName("password")
    @Expose
    private var password: String? = null
    @SerializedName("ttl")
    @Expose
    private var ttl: String? = null
    @SerializedName("gambar")
    @Expose
    private var gambar: String? = null

    @SerializedName("token")
    @Expose
    private var token: String? = null
    @SerializedName("status")
    @Expose
    private var status: String? = null
    @SerializedName("jml_post")
    @Expose
    private var jml_post: String? = null
    fun getId_user(): String? {
        return id_user
    }

    fun setId_user(id_user: String?) {
        this.id_user = id_user
    }

    fun getNama_lengkap(): String? {
        return nama_lengkap
    }

    fun setNama_lengkap(nama_lengkap: String?) {
        this.nama_lengkap = nama_lengkap
    }

    fun getAlamat(): String? {
        return alamat
    }

    fun setAlamat(alamat: String?) {
        this.alamat = alamat
    }

    fun getNo_hp(): String? {
        return no_hp
    }

    fun setNo_hp(no_hp: String?) {
        this.no_hp = no_hp
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }
    fun getTtl(): String? {
        return ttl
    }

    fun setTtl(ttl: String?) {
        this.ttl = ttl
    }

    fun getGambar(): String? {
        return gambar
    }

    fun setGambar(gambar: String?) {
        this.gambar = gambar
    }


    fun getToken(): String? {
        return token
    }

    fun setToken(token: String?) {
        this.token = token
    }


    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getJmlPost(): String? {
        return jml_post
    }

    fun setJmlPost(jml_post: String?) {
        this.jml_post = jml_post
    }
}