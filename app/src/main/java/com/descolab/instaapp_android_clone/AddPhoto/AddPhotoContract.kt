package com.descolab.instaapp_android_clone.AddPhoto

import com.descolab.instaapp_android_clone.Model.User
import java.io.File

class AddPhotoContract {
    interface View{
        fun showProgressDialog(show: Boolean)
        fun backToProfile(data: User)
    }

    interface UserActionListener{
        fun saveFeed(
            deskripsi: String,
            lokasi: String,
            id_user: String,
            tgl_post: String,
            filePicture1: File?,
            filePicture2: File?,
            filePicture3: File?
        )
    }
}