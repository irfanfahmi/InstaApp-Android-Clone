package com.descolab.instaapp_android_clone.Login

import com.descolab.instaapp_android_clone.Model.User

class LoginContract {
    interface View{
        fun showProgressDialog(show: Boolean)
        fun enterMainActivity(user: User)
    }

    interface UserActionListener{
        fun login(username: String, password: String, token: String)
    }
}