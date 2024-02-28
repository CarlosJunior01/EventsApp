package com.carlosjr.eventsapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlosjr.eventsapp.databinding.ActivityHomeBinding
import com.carlosjr.eventsapp.helper.extensions.setVisible
import com.carlosjr.eventsapp.presentation.viewmodel.EventsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val eventsViewModel: EventsViewModel by viewModels()
    private var eventsAdapter: EventsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() = with(binding) {
        customLoading.loadingContainer.setVisible(show = true)
    }

    override fun onStart() {
        super.onStart()
        setupAdapter()
        setupRecyclerView()
        setupObserve()
    }

    private fun setupObserve() = with(binding) {
        eventsViewModel.eventsLiveData.observe(this@HomeActivity) { eventsVO ->
            eventsAdapter?.addEventList(eventsList = eventsVO)
            customLoading.loadingContainer.setVisible(show = false)
        }
    }

    private fun setupAdapter() {
        eventsAdapter = EventsAdapter { events ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(EVENTS_HOME_ACTIVITY_PARAMETERS, events)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() = with(binding) {
        recyclerViewEvents.adapter = eventsAdapter
        recyclerViewEvents.layoutManager = LinearLayoutManager(this@HomeActivity)
    }

    companion object {
        const val EVENTS_HOME_ACTIVITY_PARAMETERS = "events"
    }
}