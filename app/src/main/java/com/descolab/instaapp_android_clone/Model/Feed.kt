package com.descolab.instaapp_android_clone.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Feed {
    @SerializedName("id_feed")
    @Expose
    private var id_feed: String? = null
    @SerializedName("deskripsi")
    @Expose
    private var deskripsi: String? = null
    @SerializedName("isi_gambar")
    @Expose
    private var isi_gambar: List<imageFeed>? = null
    @SerializedName("tgl_post")
    @Expose
    private var tgl_post: String? = null
    @SerializedName("lokasi")
    @Expose
    private var lokasi: String? = null
    @SerializedName("jml_like")
    @Expose
    private var jml_like: String? = null
    @SerializedName("jml_komentar")
    @Expose
    private var jml_komentar: String? = null

    //User
    @SerializedName("   idUser")
    @Expose
    private var idUser: String? = null
    @SerializedName("nama_lengkap")
    @Expose
    private var nama_lengkap: String? = null
    @SerializedName("nohp_user")
    @Expose
    private var nohp_user: String? = null
    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("username")
    @Expose
    private var username: String? = null
    @SerializedName("gambar_user")
    @Expose
    private var gambar_user: String? = null

    fun getIdFeed(): String? {
        return id_feed
    }

    fun setIdFeed(id_feed: String) {
        this.id_feed = id_feed
    }
    fun getDeskripsi(): String? {
        return deskripsi
    }

    fun setDeskripsi(deskripsi: String) {
        this.deskripsi = deskripsi
    }

    fun getIsiGambar(): List<imageFeed>? {
        return isi_gambar
    }
    fun setIsiGambar(isi_gambar: List<imageFeed>) {
        this.isi_gambar = isi_gambar
    }

    fun getTglPost(): String? {
        return tgl_post
    }
    fun setTglPost(tgl_post: String) {
        this.tgl_post = tgl_post
    }

    fun getLokasi(): String? {
        return lokasi
    }
    fun setLokasi(lokasi: String) {
        this.lokasi = lokasi
    }

    fun getJmlLike(): String? {
        return jml_like
    }
    fun setJmlLike(jml_like: String) {
        this.jml_like = jml_like
    }
    fun getJmlKomentar(): String? {
        return jml_komentar
    }
    fun setJmlKomentar(jml_komentar: String) {
        this.jml_komentar = jml_komentar
    }



    //USer
    fun getIdUser(): String? {
        return idUser
    }

    fun setIdUser(idUser: String) {
        this.idUser = idUser
    }

    fun getNama_lengkap(): String? {
        return nama_lengkap
    }


    fun setNama_lengkap(nama_lengkap: String?) {
        this.nama_lengkap = nama_lengkap
    }

    fun getNohp_User(): String? {
        return nohp_user
    }

    fun setNohp_User(nohp_user: String) {
        this.nohp_user = nohp_user
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


    fun getGambar_User(): String? {
        return gambar_user
    }

    fun setGambar_User(gambar_user: String) {
        this.gambar_user = gambar_user
    }


}