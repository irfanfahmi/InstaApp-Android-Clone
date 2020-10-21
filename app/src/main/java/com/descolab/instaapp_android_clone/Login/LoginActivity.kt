package com.descolab.instaapp_android_clone.Login

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.descolab.instaapp_android_clone.App
import com.descolab.instaapp_android_clone.Helper.DataConfig
import com.descolab.instaapp_android_clone.Helper.Utils
import com.descolab.instaapp_android_clone.Main.MainActivity
import com.descolab.instaapp_android_clone.Model.User
import com.descolab.instaapp_android_clone.R
import com.descolab.instaapp_android_clone.Register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),LoginContract.View {
    private val TAG = "LoginActivity"
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: LoginPresenter? = null
    var token : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = LoginPresenter(this@LoginActivity, this@LoginActivity)

        DataConfig.mSecurePrefs = App.get().defaultSharedPreferences
        App.get().sharedPreferences1000
        if(DataConfig.isLogin()){
            val home = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(home)
            finish()
        }

        btnLogin.setOnClickListener {
            if(!Utils.isEmpty(etUsername) && !Utils.isEmpty(etPassword)) {
                mActionListener?.login(Utils.getString(etUsername), Utils.getString(etPassword),token
                )
            }
        }

        tvDaftarAkun.setOnClickListener {
            val reg = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(reg)

        }

    }

    override fun enterMainActivity(user: User) {
        Log.d("CEK",user.getNama_lengkap())
        if(user.getStatus() == "1") {
            user.getId_user()?.let { DataConfig.setString(DataConfig.USER_ID, it) }
            user.getNama_lengkap()?.let { DataConfig.setString(DataConfig.NAME, it) }
            user.getAlamat()?.let { DataConfig.setString(DataConfig.ADDRESS, it) }
            user.getNo_hp()?.let { DataConfig.setString(DataConfig.PHONE_NUMBER, it) }
            user.getUsername()?.let { DataConfig.setString(DataConfig.USERNAME, it) }
            user.getJmlPost()?.let { DataConfig.setString(DataConfig.TOTAL_POST, it) }
            user.getEmail()?.let { DataConfig.setString(DataConfig.EMAIL, it) }
            user.getPassword()?.let { DataConfig.setString(DataConfig.PASSWORD, it) }
            user.getTtl()?.let { DataConfig.setString(DataConfig.BIRTHDAY, it) }
            user.getGambar()?.let { DataConfig.setString(DataConfig.AVATAR, it) }
            user.getToken()?.let { DataConfig.setString(DataConfig.TOKEN, it) }
            user.getStatus()?.let { DataConfig.setString(DataConfig.STATUS, it) }
            DataConfig.setLogin()
            val home = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(home)
            finish()
        }else{
            val builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Insta App")
            builder.setMessage("Akun Anda belum aktif masih di verifikasi, silahkan tunggu mendapatkan email!.")
            builder.setPositiveButton("OK"){dialog, which ->
                dialog.dismiss()
            }
            val dialog: android.app.AlertDialog = builder.create()
            dialog.show()
        }
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }


}
