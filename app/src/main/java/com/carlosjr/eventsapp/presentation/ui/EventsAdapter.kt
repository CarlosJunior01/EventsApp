package com.carlosjr.eventsapp.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlosjr.eventsapp.R
import com.carlosjr.eventsapp.databinding.ItemListEventsBinding
import com.carlosjr.eventsapp.presentation.model.EventsVO
import com.squareup.picasso.Picasso

class EventsAdapter(
    val onClick: (EventsVO) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private var eventsList: MutableList<EventsVO> = mutableListOf()

    fun addEventList(eventsList: List<EventsVO>) {
        this.eventsList.addAll(eventsList)
        notifyDataSetChanged()
    }

    inner class EventViewHolder(eventItem: ItemListEventsBinding) :
        RecyclerView.ViewHolder(eventItem.root) {
        private val binding: ItemListEventsBinding

        init {
            this.binding = eventItem
        }

        fun bind(events: EventsVO) {
            val imageUrl = events.image

            binding.textViewEventName.text = events.title
            binding.textViewEventDay.text = "01"
            binding.textViewEventMonth.text = "Fev"
            binding.textViewEventLocation.text = "6 KM"
            Picasso.get()
                .load(imageUrl)
                .error(R.drawable.tech_background)
                .into(binding.imageEvent)

            binding.cardViewContainer.setOnClickListener {
                onClick(events)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemListEventsBinding.inflate(layoutInflater, parent, false)
        return EventViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val movie = eventsList[position]
        holder.bind(movie)
    }

    override fun getItemCount() = eventsList.size
}