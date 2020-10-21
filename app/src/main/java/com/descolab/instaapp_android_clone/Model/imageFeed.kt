package com.descolab.instaapp_android_clone.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class imageFeed {
    @SerializedName("id_image")
    @Expose
    private var id_image: String? = null

    @SerializedName("image")
    @Expose
    private var image: String? = null
    @SerializedName("id_feed")
    @Expose
    private var id_feed: String? = null

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String) {
        this.image = image
    }
    fun getIdImage(): String? {
        return id_image
    }

    fun setIdImage(id_image: String) {
        this.id_image = id_image
    }


    fun getIdFeed(): String? {
        return id_feed
    }

    fun setIdFeed(id_feed: String) {
        this.id_feed = id_feed
    }



}