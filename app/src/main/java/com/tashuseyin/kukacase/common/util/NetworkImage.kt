package com.tashuseyin.kukacase.common.util

import android.widget.ImageView
import coil.load
import com.tashuseyin.kukacase.R

fun ImageView.loadImageView(imageUrl: String?) {
    this.load(imageUrl) {
        crossfade(600)
        error(R.drawable.ic_placeholder)
    }
}