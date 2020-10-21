package com.descolab.instaapp_android_clone.Home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ToggleButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.descolab.instaapp_android_clone.Comment.CommentActivity
import com.descolab.instaapp_android_clone.Helper.DataConfig
import com.descolab.instaapp_android_clone.Helper.Tools
import com.descolab.instaapp_android_clone.Helper.Utils
import com.descolab.instaapp_android_clone.Model.Feed
import com.descolab.instaapp_android_clone.Model.imageFeed
import com.descolab.instaapp_android_clone.R
import kotlinx.android.synthetic.main.item_feed.view.*


class HomeAdapter(private val mContext: Context,
                  val mItems: ArrayList<Feed>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private val mInflater: LayoutInflater
    companion object {
        var ArrayFeed : ArrayList<imageFeed>? = null
        private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
        private var idUser : String = ""
    }
    init {
        mInflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = mInflater.inflate(R.layout.item_feed, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mItems.size
        //return 5
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        if (0 <= position && position < mItems.size) {
            val data = mItems[position]
            // Bind your data here
            holder.bind(data)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Feed) {

            itemView.tvUsername.text = data.getUsername().toString()
            itemView.tvUsername1.text = data.getUsername().toString()
            itemView.tvLokasi.text = data.getLokasi().toString()
            itemView.tvDeskripsi.text = data.getDeskripsi().toString()
            itemView.tvJmlLike.text = data.getJmlLike().toString()+" Like"
            itemView.tvJmlComment.text = data.getJmlKomentar().toString()+" Komentar"
            itemView.tvTglPost.text =  Utils.prettyTime(
                Utils.getDateTime(data.getTglPost().toString()).toString())

            itemView.btnLike.setChecked(false)
            itemView.btnLike.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    mContext.getApplicationContext(),
                    R.drawable.ic_like
                )
            )
            itemView.btnLike.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                    itemView.btnLike.setBackgroundDrawable(ContextCompat.getDrawable(mContext.getApplicationContext(),
                        R.drawable.ic_like_full
                    //update Like

                    )
                ) else itemView.btnLike.setBackgroundDrawable(ContextCompat.getDrawable(mContext.getApplicationContext(),
                        R.drawable.ic_like
                    //update unLike
                    )
                )
            })

            itemView.btnComment.setOnClickListener {
                val i = Intent(mContext, CommentActivity::class.java)
                i.putExtra("MYUID", DataConfig.getString(DataConfig.USER_ID))
                i.putExtra("FEEDID", data.getIdFeed().toString())
                mContext.startActivity(i)
            }

            val baseURL : String = mContext.getString(R.string.base_url)


            if (data.getGambar_User().toString().equals("-")){
                Tools.displayImageOriginal(mContext,itemView.ivProfile,R.drawable.ic_placeholder)
            }else{
                Tools.displayImageOriginal(mContext,itemView.ivProfile,baseURL+data.getGambar_User().toString())
            }


            Log.d("Cek","isi Gambar User : "+ baseURL+data.getGambar_User().toString())


            ArrayFeed = ArrayList(data.getIsiGambar()!!)
            Log.d("Cek","isi Gambar Feed : "+ ArrayFeed!![2].getImage().toString())

            itemView.mPager.adapter = SlideImageAdapter(mContext, ArrayList(data.getIsiGambar()!!))
            itemView.indicator.setViewPager(mPager)

            val density = mContext.resources.displayMetrics.density

            //Set circle indicator radius
            itemView.indicator.radius = 5 * density


            // Pager listener over indicator
            itemView.indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageSelected(position: Int) {
                    currentPage = position
                }

                override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {
                }

                override fun onPageScrollStateChanged(pos: Int) {
                }
            })

        }
    }



}