package com.udacity.asteroidradar.feature.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.base.AsteroidApplication
import com.udacity.asteroidradar.base.data.AsteroidRepository
import com.udacity.asteroidradar.base.data.contract.Repository
import com.udacity.asteroidradar.base.data.local.LocalDataSourceImpl
import com.udacity.asteroidradar.base.data.remote.RemoteDataSourceImpl
import com.udacity.asteroidradar.base.data.remote.retrofit.AsteroidApi
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var repository: Repository

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel.asteroidsStatus.observe(this.viewLifecycleOwner) {
            Log.d("main", it.toString())
        }
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AsteroidApplication).appComponent().inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
