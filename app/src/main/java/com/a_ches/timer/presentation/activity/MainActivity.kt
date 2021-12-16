package com.a_ches.timer.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.a_ches.timer.R
import com.a_ches.timer.data.TimestampProvider
import com.a_ches.timer.databinding.ActivityMainBinding
import com.a_ches.timer.domain.ElapsedTimeCalculator
import com.a_ches.timer.domain.StopwatchListOrchestrator
import com.a_ches.timer.domain.StopwatchStateCalculator
import com.a_ches.timer.domain.StopwatchStateHolder
import com.a_ches.timer.presentation.viewModel.MainActivityViewModel
import com.a_ches.timer.utils.TimestampMillisecondsFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by inject()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObserve()
        initListeners()
    }

    private fun initObserve() {
        mainActivityViewModel.ticker.observe(this) {
            binding.textTime.text = it
        }
    }

    private fun initListeners() {
        with(binding) {
            buttonStart.setOnClickListener {
                mainActivityViewModel.stopwatchStart()
            }
            buttonPause.setOnClickListener {
                mainActivityViewModel.stopwatchPause()
            }
            buttonStop.setOnClickListener {
                mainActivityViewModel.stopwatchStop()
            }
        }
    }
}