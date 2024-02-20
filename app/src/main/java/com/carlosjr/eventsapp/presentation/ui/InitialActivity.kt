package com.carlosjr.eventsapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.carlosjr.eventsapp.databinding.ActivityInitialBinding
import com.carlosjr.eventsapp.helper.extensions.openActivity
import com.carlosjr.eventsapp.helper.extensions.toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InitialActivity : AppCompatActivity() {

    private val binding by lazy { ActivityInitialBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onLoadHomeActivity()
        toast("Initial Activity")
    }

    private fun onLoadHomeActivity() {
        lifecycleScope.launch {
            delay(3500L)
            openActivity(HomeActivity())
        }
    }
}