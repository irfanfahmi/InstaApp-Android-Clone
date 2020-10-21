package com.descolab.instaapp_android_clone.Comment

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.descolab.instaapp_android_clone.Helper.Tools
import com.descolab.instaapp_android_clone.Helper.Utils
import com.descolab.instaapp_android_clone.Model.Comment
import com.descolab.instaapp_android_clone.R
import kotlinx.android.synthetic.main.item_comment_recived.view.*
import kotlinx.android.synthetic.main.item_comment_recived.view.ivProfile
import kotlinx.android.synthetic.main.item_feed.view.*

class CommentAdapter (private val mContext: Context,
                      val mItems: ArrayList<Comment>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>(){
    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        val view = mInflater.inflate(R.layout.item_comment_recived, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: CommentAdapter.ViewHolder, position: Int) {
        if (0 <= position && position < mItems.size) {
            val data = mItems[position]
            // Bind your data here
            holder.bind(data)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Comment) {
            val pesan = "<font color='#1B074C'><b>"+data.getUsername().toString()+"</b></font> <font color='#000000'>"+ data.getPesan().toString()+"</font>"
            itemView.tvPesan.text = Html.fromHtml(pesan)
            itemView.tvDate.text = Utils.prettyTime(
                Utils.getDateTime(data.getDate().toString()).toString())
            val baseURL : String = mContext.getString(R.string.base_url)

            if (data.getGambarUser().toString().equals("-")){
                Tools.displayImageOriginal(mContext,itemView.ivProfile,R.drawable.ic_placeholder)
            }else{
                Tools.displayImageOriginal(mContext,itemView.ivProfile,baseURL+data.getGambarUser().toString())
            }


        }
    }
}