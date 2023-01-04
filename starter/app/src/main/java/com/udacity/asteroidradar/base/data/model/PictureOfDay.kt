package com.udacity.asteroidradar.base.data.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.udacity.asteroidradar.base.data.local.room.entity.PictureOfDayEntity

data class PictureOfDay(
    val date: String,
    val media_type: String,
    val title: String,
    val url: String,
) {
    companion object {
        fun convert(pictureOfDayEntity: PictureOfDayEntity): PictureOfDay {
            return PictureOfDay(
                pictureOfDayEntity.mediaType,
                pictureOfDayEntity.title,
                pictureOfDayEntity.url,
                pictureOfDayEntity.date
            )
        }
    }
}