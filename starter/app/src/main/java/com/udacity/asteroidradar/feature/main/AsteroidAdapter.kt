package com.udacity.asteroidradar.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.utils.getTodayDate
import com.udacity.asteroidradar.databinding.AsteroidItemLayoutBinding

class AsteroidAdapter(private val data: List<Asteroid>, val onAsteroidItemClicked: (Asteroid)-> Unit): ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(DiffUitlCallBack()) {
    private lateinit var binding: AsteroidItemLayoutBinding
    private val mutableAsteroidList: MutableList<Asteroid> = mutableListOf()

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
        holder.bind(getItem(position), onAsteroidItemClicked)
    }

    fun onShowWeekAsteroidsClicked() {
        submitList(data)
    }

    fun onShowTodayAsteroidClicked() {
        val todayDate = getTodayDate()
        mutableAsteroidList.clear()
        mutableAsteroidList.addAll(data.filter { it.closeApproachDate == todayDate })
        submitList(mutableAsteroidList)
    }
}


class DiffUitlCallBack: DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem
    }
}