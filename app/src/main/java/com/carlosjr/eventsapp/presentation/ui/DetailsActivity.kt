package com.carlosjr.eventsapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carlosjr.eventsapp.R
import com.carlosjr.eventsapp.databinding.ActivityDetailsBinding
import com.carlosjr.eventsapp.helper.extensions.parcelable
import com.carlosjr.eventsapp.presentation.model.EventsVO
import com.carlosjr.eventsapp.presentation.ui.HomeActivity.Companion.EVENTS_HOME_ACTIVITY_PARAMETERS
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        setupListeners()
    }

    override fun onStart() {
        super.onStart()
        getExtras()
    }

    private fun getExtras()  {
        intent.extras?.let {
            val eventsResult = intent.parcelable<EventsVO>(EVENTS_HOME_ACTIVITY_PARAMETERS)
            setupResultView(event = eventsResult)
        }
    }

    private fun setupViews() = with(binding) {
        includeActionBar.actionBarTitle.text = getString(R.string.details_event_title)
    }

    private fun setupListeners() = with(binding) {
        includeActionBar.primaryActionButton.setOnClickListener { finish() }
    }

    private fun setupResultView(event: EventsVO?) = with(binding) {
        includeEventDetail.textViewEventTitle.text = event?.title
        includeEventDetail.textViewEventDescription.text = event?.description
        includeEventDetail.textViewEventDate.text = event?.date.toString()

        Picasso.get()
            .load(event?.image)
            .error(R.drawable.tech_background)
            .into(includeEventDetail.imageEvent)
    }
}