package com.descolab.instaapp_android_clone.Comment

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.descolab.instaapp_android_clone.Model.Comment
import com.descolab.instaapp_android_clone.R
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : AppCompatActivity(),CommentContract.View {
    private var mAdapter: CommentAdapter? = null
    private var progressDialog : ProgressDialog? = null
    private var mActionListener: CommentPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        mActionListener = CommentPresenter(this, this)

        val feedId :String? = intent.getStringExtra("FEEDID")
        val myId :String? = intent.getStringExtra("MYUID")

        mActionListener?.loadComment(feedId.toString())


        send_chat.setOnClickListener {
            if (text_chat.text.toString()!=null)
            mActionListener?.sendComment(feedId.toString(),myId.toString(),text_chat.text.toString(),
                (System.currentTimeMillis()/1000).toString()
            )else{

            }
        }


    }

    override fun showProgressDialog(show: Boolean) {
        if (show) progressDialog?.show()
        else progressDialog?.dismiss()
    }

    override fun showComment(data: ArrayList<Comment>) {
       mAdapter = CommentAdapter(this,data)
        rvComment?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvComment?.setHasFixedSize(true)
        rvComment?.adapter = mAdapter
    }

    override fun backToActivity(data: ArrayList<Comment>) {
        mAdapter = CommentAdapter(this,data)
        rvComment?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvComment?.setHasFixedSize(true)
        rvComment?.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()
        if (mAdapter!!.itemCount > 0){
            rvComment.smoothScrollToPosition(mAdapter!!.itemCount - 0)
        }
        text_chat.text.clear()
    }
}
