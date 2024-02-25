package com.carlosjr.eventsapp.helper.extensions

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun View.setVisible(show: Boolean) {
    if (show) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

fun setupPicassoImage(image: String?, error: Int, view: ImageView) {
    Picasso.get()
        .load(image)
        .error(error)
        .into(view)
}