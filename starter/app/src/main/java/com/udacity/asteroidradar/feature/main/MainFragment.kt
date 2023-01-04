package com.udacity.asteroidradar.feature.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.base.AsteroidApplication
import com.udacity.asteroidradar.base.data.contract.Repository
import com.udacity.asteroidradar.base.data.model.Asteroid
import com.udacity.asteroidradar.base.utils.Status
import com.udacity.asteroidradar.base.utils.Status.Failure
import com.udacity.asteroidradar.base.utils.Status.Success
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import javax.inject.Inject

class MainFragment : Fragment() {
    private val TAG = "MainFragment"
    @Inject
    lateinit var repository: Repository
    private lateinit var binding: FragmentMainBinding
    private lateinit var asteroidsAdapter: AsteroidAdapter

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel.asteroidsStatus.observe(this.viewLifecycleOwner) {
            handleAsteroidRequestStatus(it)
        }
        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun handleAsteroidRequestStatus(status: Status) {
        when (status) {
            is Success -> {
                initAsteroidsList(status.data)
            }
            is Failure -> {
                Log.e(TAG, status.exception?.message.toString())
            }
            else -> {}
        }
    }

    private fun initAsteroidsList(data: List<Asteroid>?) {
        asteroidsAdapter = AsteroidAdapter(data ?: emptyList())
        binding.asteroidRecycler.adapter = asteroidsAdapter
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
