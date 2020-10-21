package com.descolab.instaapp_android_clone.Register

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.descolab.instaapp_android_clone.Helper.Utils
import com.descolab.instaapp_android_clone.Login.LoginActivity
import com.descolab.instaapp_android_clone.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(),RegisterContract.View {
    private var mActionListener: RegisterPresenter? = null
    private var progressDialog : ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mActionListener = RegisterPresenter(this@RegisterActivity, this@RegisterActivity)
        progressDialog = ProgressDialog(this@RegisterActivity)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)

        btnRegister.setOnClickListener {
            if(!Utils.isEmpty(etEmail) && !Utils.isEmpty(etPassword)
                && !Utils.isEmpty(etName) && !Utils.isEmpty(etNumberPhone)) {
                mActionListener?.saveRegister(
                    etName.text.toString(),
                    etNumberPhone.text.toString(),
                    etAddress.text.toString(),
                    etUsername.text.toString(),
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }
        }

    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun backToLogin() {
        val builder = AlertDialog.Builder(this@RegisterActivity)
        builder.setTitle("InstaApp")
        builder.setMessage("Register Berhasil, Silahkan Login")
        builder.setCancelable(false)
        builder.setPositiveButton("OK"){dialog, which ->
            val login = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(login)
            finish()
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
