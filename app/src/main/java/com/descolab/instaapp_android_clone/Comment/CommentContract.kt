package com.descolab.instaapp_android_clone.Comment

import com.descolab.instaapp_android_clone.Model.Comment

class CommentContract {
    interface View{
        fun showProgressDialog(show: Boolean)
        fun showComment(data: ArrayList<Comment>)
        fun backToActivity(data: ArrayList<Comment>)
    }
    interface UserActionListener{
        fun loadComment(idComment : String)
        fun sendComment(FeedId : String,MyId : String, pesan: String,date : String)
    }
}