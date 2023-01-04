package com.udacity.asteroidradar.base.data.local.room.dao

import androidx.room.*
import com.udacity.asteroidradar.base.data.local.room.entity.AsteroidEntity
import com.udacity.asteroidradar.base.data.local.room.entity.PictureOfDayEntity

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsteroids(asteroids: List<AsteroidEntity>)

    @Query("SELECT * FROM asteroid_table ORDER BY distance_from_earth ASC")
    suspend fun getAsteroids(): List<AsteroidEntity>

    @Delete
    suspend fun deleteAsteroid(asteroidEntity: AsteroidEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictureOfDay(picture: PictureOfDayEntity)

    @Query("SELECT * FROM picture_of_day_table")
    suspend fun getPictureOfDay(): PictureOfDayEntity?

    @Query("DELETE FROM picture_of_day_table")
    suspend fun deletePictureOfDay()
}