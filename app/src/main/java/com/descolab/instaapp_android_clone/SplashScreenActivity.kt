package com.descolab.instaapp_android_clone

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.descolab.instaapp_android_clone.Login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.security.AccessController.getContext

class SplashScreenActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable {
            //setelah loading maka akan langsung berpindah ke home activity
            val login = Intent(this@SplashScreenActivity, LoginActivity::class.java)
            startActivity(login)
            finish()
        }, 2000)


        val typeface = resources.getFont(R.font.monserat)
        tvTagline.typeface = typeface
    }
}
