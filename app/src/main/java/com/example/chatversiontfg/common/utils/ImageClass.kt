package com.example.chatversiontfg.common.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ImageClass {
    fun loadImage(img : String,iv : ImageView,context : Context){
        Glide.with(context)
            .load(img)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(iv)
    }
}