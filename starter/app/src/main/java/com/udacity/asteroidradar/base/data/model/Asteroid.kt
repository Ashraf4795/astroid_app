package com.udacity.asteroidradar.base.data.model

import android.os.Parcelable
import com.udacity.asteroidradar.base.data.local.room.entity.AsteroidEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(
    val id: Long, val codename: String, val closeApproachDate: String,
    val absoluteMagnitude: Double, val estimatedDiameter: Double,
    val relativeVelocity: Double, val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
) : Parcelable {

    companion object {
        fun convert(asteroidEntity: AsteroidEntity): Asteroid {
            return Asteroid (
                asteroidEntity.id,
                asteroidEntity.codename,
                asteroidEntity.closeApproachDate,
                asteroidEntity.absoluteMagnitude,
                asteroidEntity.estimatedDiameter,
                asteroidEntity.relativeVelocity,
                asteroidEntity.distanceFromEarth,
                asteroidEntity.isPotentiallyHazardous
            )
        }
    }
}