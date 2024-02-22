package com.carlosjr.eventsapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlosjr.eventsapp.databinding.ActivityHomeBinding
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

    override fun onStart() {
        super.onStart()
        setupAdapter()
        setupRecyclerView()
        setupObserve()
    }

    private fun setupObserve() {
        eventsViewModel.eventsLiveData.observe(this@HomeActivity) { eventsVO ->
            eventsAdapter?.addEventList(eventsList = eventsVO)
        }
    }

    private fun setupAdapter() {
        eventsAdapter = EventsAdapter { events ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("events", events)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() = with(binding) {
        recyclerViewEvents.adapter = eventsAdapter
        recyclerViewEvents.layoutManager = LinearLayoutManager(this@HomeActivity)
    }

    private fun setupViews() = with(binding) {
        /*var eventsResult = ""
        eventsViewModel.eventsLiveData.observe(this@HomeActivity) { userList ->
            userList.forEach { eventsVO ->
                eventsResult += "[ID:${eventsVO.id}] - Title: ${eventsVO.title}\n"
            }
            textEventsCategory.text = eventsResult
        }*/
    }
}