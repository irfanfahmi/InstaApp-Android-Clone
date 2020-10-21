package com.descolab.instaapp_android_clone.Home

import android.content.Context
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.descolab.instaapp_android_clone.Helper.Tools
import com.descolab.instaapp_android_clone.Model.imageFeed
import com.descolab.instaapp_android_clone.R


class SlideImageAdapter(private val context: Context, private val imageModelArrayList: ArrayList<imageFeed>) : PagerAdapter() {
    private val inflater: LayoutInflater
    init {
        inflater = LayoutInflater.from(context)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return imageModelArrayList.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.item_sliding_image_layout, view, false)!!

        val imageView = imageLayout
            .findViewById(R.id.image) as ImageView
        val baseURL : String = context.getString(R.string.base_url)
        Log.d("Cek  isi gmbar", baseURL+imageModelArrayList[position].getImage().toString())
        Tools.displayImageOriginal(context,imageView,baseURL+imageModelArrayList[position].getImage().toString())
        view.addView(imageLayout, 0)

        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }
}