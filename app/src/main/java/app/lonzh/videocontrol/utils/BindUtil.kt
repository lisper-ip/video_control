package app.lonzh.videocontrol.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import app.lonzh.commonlibrary.ext.appContext
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import java.io.File

object BindUtil {
    @JvmStatic
    @BindingAdapter(value = ["imageUrlRound", "placeholder", "error"], requireAll = false)
    fun imageUrlRound(view: ImageView, url: String, placeholder: Drawable, error: Drawable) {
        if (url.isNullOrEmpty()) {
            view.setImageDrawable(error)
        } else {
            Glide.with(appContext)
                .load(url)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(10)))
                .placeholder(placeholder)
                .error(error)
                .into(view)
        }

    }

    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeholder", "error"], requireAll = false)
    fun imageUrl(view: ImageView, url: String, placeholder: Drawable, error: Drawable) {
        if (url.isNullOrEmpty()) {
            view.setImageDrawable(error)
        } else {
            Glide.with(view.context)
                .load(url)
                .placeholder(placeholder)
                .error(error)
                .into(view)
        }

    }

    @JvmStatic
    @BindingAdapter(value = ["imageOval", "placeholder", "error"], requireAll = false)
    fun imageOval(view: ImageView, url: String, placeholder: Drawable, error: Drawable) {
        if (url.isNullOrEmpty()) {
            view.setImageDrawable(error)
        } else {
            Glide.with(view.context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .placeholder(placeholder)
                .error(error)
                .into(view)
        }

    }

    /**
     * 本地图片路径
     */
    @JvmStatic
    @BindingAdapter("imageFile")
    fun imageFile(view: ImageView, url: String) {
        Glide.with(view.context).load(File(url)).into(view)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun imageSrc(view: ImageView, resId: Int) {
        view.setImageResource(resId)
    }
}