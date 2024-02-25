package com.carlosjr.eventsapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.carlosjr.eventsapp.databinding.ActivityInitialBinding
import com.carlosjr.eventsapp.helper.extensions.openActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InitialActivity : AppCompatActivity() {

    private val binding by lazy { ActivityInitialBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onLoadHomeActivity()
    }

    private fun onLoadHomeActivity() {
        lifecycleScope.launch {
            delay(3000L)
            openActivity(HomeActivity())
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}