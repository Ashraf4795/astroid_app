package com.udacity.asteroidradar.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidItemLayoutBinding

class AsteroidAdapter(private val asteroids: List<Asteroid>): RecyclerView.Adapter<AsteroidAdapter.AsteroidViewHolder>() {
    private lateinit var binding: AsteroidItemLayoutBinding

    inner class AsteroidViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(asteroid: Asteroid) {
            binding.asteroid = asteroid
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        binding = AsteroidItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AsteroidViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.bind(asteroids[position])
    }

    override fun getItemCount()= asteroids.size
}