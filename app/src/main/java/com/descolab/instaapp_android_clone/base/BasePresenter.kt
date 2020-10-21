package com.descolab.instaapp_android_clone.base

import android.app.AlertDialog
import android.content.Context
import com.descolab.instaapp_android_clone.Helper.DataConfig

import org.json.JSONException
import org.json.JSONObject
import java.util.*

open class BasePresenter {
    fun showDialog(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Insta App")
        builder.setMessage(message)
        builder.setPositiveButton("OK"){dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showErrorMessage(context: Context, errorMessage: String) {
        try {
            val `object` = JSONObject(errorMessage)
            showDialog(context, `object`.getString("ErrMsg"))
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    fun getHeaders(): Map<String, String> {
        val headers = HashMap<String, String>()
        headers["Authorization"] = "Bearer " + DataConfig.getString(DataConfig.TOKEN)
        return headers
    }

    fun autoLogout(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Insta App")
        builder.setMessage("Token Anda kadaluarsa. Silakan melakukan login kembali.")
        builder.setPositiveButton("OK"){dialog, which ->
            DataConfig.clearAll()
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
    }
}