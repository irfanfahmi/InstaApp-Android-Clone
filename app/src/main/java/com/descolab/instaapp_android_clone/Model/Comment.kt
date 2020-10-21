package com.descolab.instaapp_android_clone.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Comment {
    @SerializedName("id_comment")
    @Expose
    private var idComment: String? = null

    @SerializedName("pesan")
    @Expose
    private var pesan: String? = null

    @SerializedName("date")
    @Expose
    private var date: String? = null

    @SerializedName("id_feed")
    @Expose
    private var idFeed: String? = null

    @SerializedName("id_user")
    @Expose
    private var idUser: String? = null

    @SerializedName("nama_lengkap")
    @Expose
    private var namaLengkap: String? = null

    @SerializedName("nohp_user")
    @Expose
    private var nohpUser: String? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("username")
    @Expose
    private var username: String? = null

    @SerializedName("gambar_user")
    @Expose
    private var gambarUser: String? = null

    fun getIdComment(): String? {
        return idComment
    }

    fun setIdComment(idComment: String?) {
        this.idComment = idComment
    }

    fun getPesan(): String? {
        return pesan
    }

    fun setPesan(pesan: String?) {
        this.pesan = pesan
    }

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String?) {
        this.date = date
    }

    fun getIdFeed(): String? {
        return idFeed
    }

    fun setIdFeed(idFeed: String?) {
        this.idFeed = idFeed
    }

    fun getIdUser(): String? {
        return idUser
    }

    fun setIdUser(idUser: String?) {
        this.idUser = idUser
    }

    fun getNamaLengkap(): String? {
        return namaLengkap
    }

    fun setNamaLengkap(namaLengkap: String?) {
        this.namaLengkap = namaLengkap
    }

    fun getNohpUser(): String? {
        return nohpUser
    }

    fun setNohpUser(nohpUser: String?) {
        this.nohpUser = nohpUser
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getGambarUser(): String? {
        return gambarUser
    }

    fun setGambarUser(gambarUser: String?) {
        this.gambarUser = gambarUser
    }
}