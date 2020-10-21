package com.descolab.instaapp_android_clone.Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.descolab.instaapp_android_clone.AddPhoto.AddPhotoFragment
import com.descolab.instaapp_android_clone.App
import com.descolab.instaapp_android_clone.Hashtag.HashtagFragment
import com.descolab.instaapp_android_clone.Helper.DataConfig
import com.descolab.instaapp_android_clone.Helper.Utils
import com.descolab.instaapp_android_clone.Home.HomeFragment
import com.descolab.instaapp_android_clone.Like.LikeFragment
import com.descolab.instaapp_android_clone.Login.LoginActivity
import com.descolab.instaapp_android_clone.Message.MessageActivity
import com.descolab.instaapp_android_clone.Profile.ProfileFragment
import com.descolab.instaapp_android_clone.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        @JvmStatic lateinit var instance: MainActivity
        @JvmStatic var popup = 0
    }

    init {
        instance = this
    }

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.frame_container, HomeFragment(), null)
        transaction.commit()

        DataConfig.mSecurePrefs = App.get().defaultSharedPreferences
        App.get().sharedPreferences1000

        setSupportActionBar(toolbarid)
        // Now get the support action bar
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.title = ""

        // Display the app icon in action bar/toolbar
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setLogo(R.drawable.ic_camera)
        actionBar?.setDisplayUseLogoEnabled(true)

        navigation.enableShiftingMode(false)
        navigation.enableItemShiftingMode(false)



    }

    override fun onBackPressed() {
        super.onBackPressed()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_container)
        navigation.onNavigationItemSelectedListener = null
        currentFragment?.let { setBottomMenu(it) }
        navigation.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }

    override fun onResume() {
        super.onResume()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_container)
        navigation.onNavigationItemSelectedListener = null
        currentFragment?.let { setBottomMenu(it) }
        navigation.onNavigationItemSelectedListener = mOnNavigationItemSelectedListener
    }

    private fun hideOption(id: Int) {
        val item = menu?.findItem(id)
        if (item != null) {
            item.isVisible = false
        }
    }

    private fun showOption(id: Int) {
        val item = menu?.findItem(id)
        if (item != null) {
            item.isVisible = true
        }
    }



    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is HomeFragment) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {
                            hideOption(R.id.menu_logout)
                            showOption(R.id.menu_send)
                            Utils.addFragment(
                                it,
                                HomeFragment(), null
                            )
                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_hastag -> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is HashtagFragment) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {
                            hideOption(R.id.menu_logout)
                            showOption(R.id.menu_send)

                            Utils.addFragment(
                                it,
                                HashtagFragment(), null
                            )
                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_add -> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is AddPhotoFragment) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {
                            hideOption(R.id.menu_logout)
                            showOption(R.id.menu_send)

                            Utils.addFragment(
                                it,
                                AddPhotoFragment(), null
                            )
                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_like-> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is LikeFragment) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {
                            hideOption(R.id.menu_logout)
                            showOption(R.id.menu_send)

                            Utils.addFragment(
                                it,
                                LikeFragment(), null
                            )
                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_profil -> {
                    if (supportFragmentManager.findFragmentById(R.id.frame_container) !is ProfileFragment) {
                        supportFragmentManager.findFragmentById(R.id.frame_container)?.let {
                            hideOption(R.id.menu_send)
                            showOption(R.id.menu_logout)

                            Utils.addFragment(
                                it,
                                ProfileFragment(), null
                            )

                        }
                    }
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    fun setBottomMenu(fragment: Fragment) {
        navigation.visibility = View.VISIBLE
        if (fragment is HomeFragment) {
            navigation.currentItem = 0
        } else if (fragment is HashtagFragment) {
            navigation.currentItem = 1
        } else if (fragment is AddPhotoFragment) {
            navigation.currentItem = 2
        } else if (fragment is LikeFragment) {
            navigation.currentItem = 3
        } else if (fragment is ProfileFragment) {
            navigation.currentItem = 4
        } else {
            navigation.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.menu_send -> {
                Utils.showToast(this, "Message")
                val i = Intent(this, MessageActivity::class.java)
                startActivity(i)
            }

            R.id.menu_logout -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("InstaApp")
                builder.setMessage("Apakah anda ingin logout?")
                builder.setPositiveButton("Ya"){dialog, which ->
                    DataConfig.clearAll()
                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                    Utils.showToast(this, "Logout Berhasil")
                    dialog.dismiss()
                }

                builder.setNegativeButton("Tidak"){dialog,which ->
                    dialog.dismiss()
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
