package com.carlosjr.eventsapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carlosjr.eventsapp.databinding.ActivityInitialBinding

class InitialActivity : AppCompatActivity() {

    private val binding by lazy { ActivityInitialBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}