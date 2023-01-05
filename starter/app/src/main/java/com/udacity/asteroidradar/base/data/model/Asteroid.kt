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

    fun contentDescriptionForHazardous(isPotentiallyHazardous: Boolean): String {
        return if (isPotentiallyHazardous) {
            "and it's potential to be a hazardous asteroid"
        } else {
            "it's not a hazardous asteroid, thank god"
        }
    }
    val contentDescription = """$codename, and the close approach date is $closeApproachDate, with estimated diameter $estimatedDiameter,
        |this asteroid far from earth by $distanceFromEarth, ${contentDescriptionForHazardous(isPotentiallyHazardous)}
    """.trimMargin()

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