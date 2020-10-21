package com.descolab.instaapp_android_clone.Helper

import android.content.SharedPreferences

class DataConfig {
    companion object {
        val USER_ID = "id_user"
        val NAME = "nama_lengkap"
        val EMAIL = "email"
        val USERNAME = "username"
        val PASSWORD = "password"
        val BIRTHDAY = "ttl"
        val PHONE_NUMBER = "no_hp"
        val AVATAR = "gambar"
        val ADDRESS = "alamat"
        val STATUS = "status"
        val IS_LOGIN = "is_login"
        val TOKEN = "token"
        val ROLE_ID = "role_id"
        val TOTAL_POST = "jml_post"




        var mSecurePrefs: SharedPreferences? = null

        fun setString(key: String, value: String) {
            mSecurePrefs!!.edit().putString(key, value).apply()
        }

        fun getString(key: String): String {
            if(mSecurePrefs!=null){
                return mSecurePrefs?.getString(key, "")!!
            }else return ""
        }

        fun setInt(key: String, value: Int) {
            mSecurePrefs?.edit()?.putInt(key, value)?.apply()
        }

        fun getInt(key: String): Int {
            if(mSecurePrefs!=null) {
                return mSecurePrefs?.getInt(key, 0)!!
            }else return 0
        }

        fun setBoolean(key: String, value: Boolean) {
            mSecurePrefs?.edit()?.putBoolean(key, value)?.apply()
        }

        fun getBoolean(key: String): Boolean {
            if(mSecurePrefs!=null) {
                return mSecurePrefs?.getBoolean(key, false)!!
            }else return false
        }

        fun setLogin() {
            mSecurePrefs?.edit()?.putBoolean(IS_LOGIN, true)?.apply()
        }

        fun setLogout() {
            mSecurePrefs?.edit()?.putBoolean(IS_LOGIN, false)?.apply()
        }

        fun isLogin(): Boolean {
            if(mSecurePrefs!=null) {
                return mSecurePrefs?.getBoolean(IS_LOGIN, false)!!
            }else return false
        }

        fun clearAll() {
            mSecurePrefs?.edit()?.clear()?.apply()
        }
    }
}