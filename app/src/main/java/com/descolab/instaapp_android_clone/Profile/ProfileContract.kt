package com.descolab.instaapp_android_clone.Profile

import com.descolab.instaapp_android_clone.Model.User
import java.io.File

class ProfileContract {
    interface View {
        fun showProgressDialog(show: Boolean)
        fun backToProfile(data: User)
    }
    interface UserActionListener {
//        fun updateProfil(
//            id_user: String,
//            nama: String,
//            alamat: String,
//            ttl: String,
//            nohp: String,
//            email: String,
//            password: String,
//            foto: File?)
    }
}