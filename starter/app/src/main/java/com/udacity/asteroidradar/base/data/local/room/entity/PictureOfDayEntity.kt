package com.udacity.asteroidradar.base.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.base.data.model.PictureOfDay

@Entity("picture_of_day_table")
data class PictureOfDayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo("media_type")
    val mediaType: String,
    @ColumnInfo("picture_title")
    val title: String,
    @ColumnInfo("picture_url")
    val url: String,
    @ColumnInfo("picture_date")
    val date: String
) {
    companion object {
        fun convert(pictureOfDay: PictureOfDay): PictureOfDayEntity {
            return PictureOfDayEntity(
                System.currentTimeMillis(),
                pictureOfDay.media_type,
                pictureOfDay.title,
                pictureOfDay.url,
                pictureOfDay.date
            )
        }
    }
}
