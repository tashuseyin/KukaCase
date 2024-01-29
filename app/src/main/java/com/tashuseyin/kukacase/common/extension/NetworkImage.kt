package com.tashuseyin.kukacase.common.extension

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import coil.request.ImageRequest
import com.tashuseyin.kukacase.R

fun ImageView.loadImageView(imageUrl: String?) {
    val context = this.context
    this.load(imageUrl) {
        crossfade(600)
        error(R.drawable.ic_placeholder)
        placeholder(placeholderProgressBar(context))
    }
}

fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        setColorSchemeColors(R.color.black)
        start()
    }
}