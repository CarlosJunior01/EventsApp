package com.carlosjr.eventsapp.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlosjr.eventsapp.R
import com.carlosjr.eventsapp.databinding.ItemListEventsBinding
import com.carlosjr.eventsapp.helper.extensions.formatDateDay
import com.carlosjr.eventsapp.helper.extensions.formatDateMonth
import com.carlosjr.eventsapp.helper.extensions.monetaryFormat
import com.carlosjr.eventsapp.helper.extensions.setupPicassoImage
import com.carlosjr.eventsapp.presentation.model.vo.EventsVO

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
            val eventDate = events.date.toString().dropLast(LENGTH_THREE).toLong()

            binding.textViewEventName.text = events.title
            binding.textViewEventDay.text = formatDateDay(eventDate)
            binding.textViewEventMonth.text = formatDateMonth(eventDate)
            binding.textViewEventPrice.text = events.price.monetaryFormat()

            setupPicassoImage(image = imageUrl, error = R.drawable.tech_background, view = binding.imageEvent)

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

    companion object {
        private const val LENGTH_THREE = 3
    }
}