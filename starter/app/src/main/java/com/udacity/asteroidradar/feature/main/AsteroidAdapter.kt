package com.udacity.asteroidradar.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.utils.getTodayDate
import com.udacity.asteroidradar.databinding.AsteroidItemLayoutBinding

class AsteroidAdapter(private val asteroids: List<Asteroid>, val onAsteroidItemClicked: (Asteroid)-> Unit): RecyclerView.Adapter<AsteroidAdapter.AsteroidViewHolder>() {
    private lateinit var binding: AsteroidItemLayoutBinding
    private val mutableAsteroidList: MutableList<Asteroid> = asteroids.toMutableList()

    inner class AsteroidViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(asteroid: Asteroid, onAsteroidItemClicked: (Asteroid) -> Unit) {
            binding.asteroid = asteroid
            binding.root.setOnClickListener {
                onAsteroidItemClicked.invoke(asteroid)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        binding = AsteroidItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AsteroidViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.bind(mutableAsteroidList[position], onAsteroidItemClicked)
    }

    fun onShowWeekAsteroidsClicked() {
        mutableAsteroidList.clear()
        mutableAsteroidList.addAll(asteroids)
        notifyDataSetChanged()
    }

    fun onShowTodayAsteroidClicked() {
        val todayDate = getTodayDate()
        mutableAsteroidList.clear()
        mutableAsteroidList.addAll(asteroids.filter { it.closeApproachDate == todayDate })
        notifyDataSetChanged()
    }

    override fun getItemCount()= mutableAsteroidList.size
}