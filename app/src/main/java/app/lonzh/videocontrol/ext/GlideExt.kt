package app.lonzh.travel.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


fun ImageView.load(url: String){
    Glide.with(context)
        .load(url)
        .into(this)
}


fun ImageView.load(url: String, placeHolder: Int, error: Int){
    Glide.with(context)
        .load(url)
        .apply(RequestOptions().transform(CenterCrop()))
        .placeholder(placeHolder)
        .error(error)
        .into(this)
}

fun ImageView.loadOval(url: String, placeHolder: Int, error: Int){
    Glide.with(context)
        .load(url)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .placeholder(placeHolder)
        .error(error)
        .into(this)
}

fun ImageView.loadRound(url: String, placeHolder: Int, error: Int){
    Glide.with(context)
        .load(url)
        .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(10)))
        .placeholder(placeHolder)
        .error(error)
        .into(this)
}