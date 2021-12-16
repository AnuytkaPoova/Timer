package com.a_ches.timer.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.a_ches.timer.data.TimestampProvider
import com.a_ches.timer.domain.ElapsedTimeCalculator
import com.a_ches.timer.domain.StopwatchListOrchestrator
import com.a_ches.timer.domain.StopwatchStateCalculator
import com.a_ches.timer.domain.StopwatchStateHolder
import com.a_ches.timer.utils.TimestampMillisecondsFormatter

class MainActivityViewModel : ViewModel() {

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        viewModelScope
    )

    val ticker: LiveData<String> = stopwatchListOrchestrator.ticker.asLiveData()

    fun stopwatchStart() {
        stopwatchListOrchestrator.start()
    }

    fun stopwatchPause() {
        stopwatchListOrchestrator.pause()
    }

    fun stopwatchStop() {
        stopwatchListOrchestrator.stop()
    }
}