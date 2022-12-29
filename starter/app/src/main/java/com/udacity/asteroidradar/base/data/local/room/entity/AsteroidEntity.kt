package com.udacity.asteroidradar.base.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("asteroid_table")
data class AsteroidEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo("code_name")
    val codename: String,
    @ColumnInfo("close_approach_date")
    val closeApproachDate: String,
    @ColumnInfo("absolute_magnitude")
    val absoluteMagnitude: Double,
    @ColumnInfo("estimated_diameter")
    val estimatedDiameter: Double,
    @ColumnInfo("relative_velocity")
    val relativeVelocity: Double,
    @ColumnInfo("distance_from_earth")
    val distanceFromEarth: Double,
    @ColumnInfo("is_hazardous")
    val isPotentiallyHazardous: Boolean
)
