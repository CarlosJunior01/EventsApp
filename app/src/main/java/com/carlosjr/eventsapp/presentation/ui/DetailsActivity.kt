package com.carlosjr.eventsapp.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carlosjr.eventsapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}