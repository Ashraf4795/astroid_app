package com.udacity.asteroidradar

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.utils.Status

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("visibilityState")
fun bindProgressBarVisibility(progressBar: ProgressBar, status: Status) {
    if(status is Status.Loading) {
        progressBar.visibility = View.VISIBLE
    } else {
        progressBar.visibility = View.GONE
    }
}

@BindingAdapter("image_url")
fun bindPictureOfDay(imageView: ImageView, url: String?) {
    if (!url.isNullOrBlank()) {
        Glide.with(imageView)
            .load(url)
            .placeholder(R.drawable.placeholder_picture_of_day)
            .into(imageView)
    }
}

@BindingAdapter("talk_back_image")
fun detailsImageTalkBack(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.contentDescription = imageView.context.resources.getString(R.string.talkback_hazardous)
    } else {
        imageView.contentDescription = imageView.context.resources.getString(R.string.talkback_not_hazardous)
    }
}

@BindingAdapter("talk_back")
fun talkback(view: View, contentDescription: String) {
    view.contentDescription = contentDescription
}
