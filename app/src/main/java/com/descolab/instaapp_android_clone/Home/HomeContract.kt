package com.descolab.instaapp_android_clone.Home

import com.descolab.instaapp_android_clone.Model.Feed

class HomeContract {
    interface View{
        fun showFeed(data: ArrayList<Feed>)
        fun showProgressDialog(show: Boolean)
    }

    interface UserActionListener{
        fun loadFeed()
    }
}