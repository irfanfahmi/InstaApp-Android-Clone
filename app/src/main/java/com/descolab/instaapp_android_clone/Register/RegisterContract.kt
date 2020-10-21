package com.descolab.instaapp_android_clone.Register

class RegisterContract {
    interface View{
        fun showProgressDialog(show: Boolean)
        fun backToLogin()
    }

    interface UserActionListener{
        fun saveRegister(nama: String,phone: String, address: String,username: String,email: String, password: String)
    }
}