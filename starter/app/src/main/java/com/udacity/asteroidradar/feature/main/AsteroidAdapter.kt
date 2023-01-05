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

class AsteroidAdapter(private val data: List<Asteroid>, val onAsteroidItemClicked: OnAsteroidItemClicked): RecyclerView.Adapter<AsteroidAdapter.AsteroidViewHolder>() {
    private lateinit var binding: AsteroidItemLayoutBinding
    private val mutableAsteroidList: MutableList<Asteroid> = data.toMutableList()

    inner class AsteroidViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(asteroid: Asteroid, onAsteroidItemClicked: OnAsteroidItemClicked) {
            binding.asteroid = asteroid
            binding.clickListener = onAsteroidItemClicked
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        binding = AsteroidItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AsteroidViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.bind(mutableAsteroidList.get(position), onAsteroidItemClicked)
    }

    fun onShowWeekAsteroidsClicked() {
        mutableAsteroidList.clear()
        mutableAsteroidList.addAll(data)
        notifyDataSetChanged()
    }

    fun onShowTodayAsteroidClicked() {
        val todayDate = getTodayDate()
        mutableAsteroidList.clear()
        val filteredList = data.filter { it.closeApproachDate == todayDate }
        mutableAsteroidList.addAll(filteredList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = mutableAsteroidList.size

    override fun getItemId(position: Int): Long {
        return mutableAsteroidList.get(position).id
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}

//
//class DiffUitlCallBack: DiffUtil.ItemCallback<Asteroid>() {
//    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
//        return oldItem == newItem
//    }
//}