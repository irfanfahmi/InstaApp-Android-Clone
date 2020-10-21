package com.descolab.instaapp_android_clone.Helper

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Build
import android.util.TypedValue
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.descolab.instaapp_android_clone.R
import java.text.SimpleDateFormat
import java.util.*

class Tools {
    companion object {
        @JvmStatic
        fun setSystemBarColor(act: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = act.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = act.resources.getColor(R.color.colorPrimaryDark)
            }
        }

        @JvmStatic
        fun setSystemBarColor(act: Activity, @ColorRes color: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = act.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = act.resources.getColor(color)
            }
        }

        @JvmStatic
        fun setSystemBarColorDialog(act: Context, dialog: Dialog, @ColorRes color: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = dialog.window
                window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = act.resources.getColor(color)
            }
        }

        @JvmStatic
        fun setSystemBarLight(act: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val view = act.findViewById<View>(android.R.id.content)
                var flags = view.systemUiVisibility
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                view.systemUiVisibility = flags
            }
        }

        @JvmStatic
        fun setSystemBarLightDialog(dialog: Dialog) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val view = dialog.findViewById<View>(android.R.id.content)
                var flags = view.systemUiVisibility
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                view.systemUiVisibility = flags
            }
        }

        @JvmStatic
        fun clearSystemBarLight(act: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val window = act.window
                window.statusBarColor = ContextCompat.getColor(act, R.color.colorPrimaryDark)
            }
        }

        /**
         * Making notification bar transparent
         */
        @JvmStatic
        fun setSystemBarTransparent(act: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = act.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            }
        }

        @JvmStatic
        fun displayImageOriginal(ctx: Context, img: ImageView, @DrawableRes drawable: Int) {
            try {
                Glide.with(ctx).load(drawable)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img)
            } catch (e: Exception) {
            }

        }

        @JvmStatic
        fun displayImageRound(ctx: Context, img: ImageView, @DrawableRes drawable: Int) {
            try {
                Glide.with(ctx).load(drawable).asBitmap().centerCrop().into(object : BitmapImageViewTarget(img) {
                    override fun setResource(resource: Bitmap) {
                        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(ctx.resources, resource)
                        circularBitmapDrawable.isCircular = true
                        img.setImageDrawable(circularBitmapDrawable)
                    }
                })
            } catch (e: Exception) {
            }

        }



        @JvmStatic
        fun displayImageOriginal(ctx: Context, img: ImageView, url: String) {
            try {
                Glide.with(ctx).load(url)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img)
            } catch (e: Exception) {
            }

        }

        @JvmStatic
        fun getFormattedDateSimple(dateTime: Long?): String {
            val newFormat = SimpleDateFormat("MMMM dd, yyyy")
            return newFormat.format(Date(dateTime!!))
        }

        @JvmStatic
        fun getFormattedDateEvent(dateTime: Long?): String {
            val newFormat = SimpleDateFormat("EEE, MMM dd yyyy")
            return newFormat.format(Date(dateTime!!))
        }

        @JvmStatic
        fun getFormattedTimeEvent(time: Long?): String {
            val newFormat = SimpleDateFormat("h:mm a")
            return newFormat.format(Date(time!!))
        }

        @JvmStatic
        fun getEmailFromName(name: String?): String? {
            return if (name != null && name != "") {
                name.replace(" ".toRegex(), ".").toLowerCase() + "@mail.com"
            } else name
        }

        @JvmStatic
        fun dpToPx(c: Context, dp: Int): Int {
            val r = c.resources
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
        }
        /*
    public static GoogleMap configActivityMaps(GoogleMap googleMap) {
        // set map type
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // Enable / Disable zooming controls
        googleMap.getUiSettings().setZoomControlsEnabled(false);

        // Enable / Disable Compass icon
        googleMap.getUiSettings().setCompassEnabled(true);
        // Enable / Disable Rotate gesture
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        // Enable / Disable zooming @JvmStatic
        functionality
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        return googleMap;
    }*/

//        @JvmStatic
//        fun copyToClipboard(context: Context, data: String) {
//            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//            val clip = ClipData.newPlainText("clipboard", data)
//            clipboard.primaryClip = clip
//            Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
//        }

        @JvmStatic
        fun nestedScrollTo(nested: NestedScrollView, targetView: View) {
            nested.post { nested.scrollTo(500, targetView.bottom) }
        }

        @JvmStatic
        fun dip2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        @JvmStatic
        fun px2dip(context: Context, pxValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }

        @JvmStatic
        fun toggleArrow(view: View): Boolean {
            if (view.rotation == 0f) {
                view.animate().setDuration(200).rotation(180f)
                return true
            } else {
                view.animate().setDuration(200).rotation(0f)
                return false
            }
        }

        @JvmStatic
        fun toggleArrow(show: Boolean, view: View): Boolean {
            return toggleArrow(show, view, true)
        }

        @JvmStatic
        fun toggleArrow(show: Boolean, view: View, delay: Boolean): Boolean {
            if (show) {
                view.animate().setDuration((if (delay) 200 else 0).toLong()).rotation(180f)
                return true
            } else {
                view.animate().setDuration((if (delay) 200 else 0).toLong()).rotation(0f)
                return false
            }
        }

        @JvmStatic
        fun changeNavigateionIconColor(toolbar: Toolbar, @ColorInt color: Int) {
            val drawable = toolbar.navigationIcon
            drawable!!.mutate()
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }

        @JvmStatic
        fun changeMenuIconColor(menu: Menu, @ColorInt color: Int) {
            for (i in 0 until menu.size()) {
                val drawable = menu.getItem(i).icon ?: continue
                drawable.mutate()
                drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            }
        }

        @JvmStatic
        fun changeOverflowMenuIconColor(toolbar: Toolbar, @ColorInt color: Int) {
            try {
                val drawable = toolbar.overflowIcon
                drawable!!.mutate()
                drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            } catch (e: Exception) {
            }

        }

        @JvmStatic
        fun getScreenWidth(): Int {
            return Resources.getSystem().displayMetrics.widthPixels
        }

        @JvmStatic
        fun getScreenHeight(): Int {
            return Resources.getSystem().displayMetrics.heightPixels
        }

        @JvmStatic
        fun toCamelCase(input: String): String {
            var input = input
            input = input.toLowerCase()
            val titleCase = StringBuilder()
            var nextTitleCase = true

            for (c in input.toCharArray()) {
                var newC: Char = c
                if (Character.isSpaceChar(c)) {
                    nextTitleCase = true
                } else if (nextTitleCase) {
                    newC = Character.toTitleCase(c)
                    nextTitleCase = false
                }

                titleCase.append(newC)
            }

            return titleCase.toString()
        }

        @JvmStatic
        fun insertPeriodically(text: String, insert: String, period: Int): String {
            val builder = StringBuilder(text.length + insert.length * (text.length / period) + 1)
            var index = 0
            var prefix = ""
            while (index < text.length) {
                builder.append(prefix)
                prefix = insert
                builder.append(text.substring(index, Math.min(index + period, text.length)))
                index += period
            }
            return builder.toString()
        }


        @JvmStatic
        fun rateAction(activity: Activity) {
            val uri = Uri.parse("market://details?id=" + activity.packageName)
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)
            try {
                activity.startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + activity.packageName)
                    )
                )
            }

        }
    }
}