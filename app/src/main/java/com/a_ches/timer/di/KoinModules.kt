package com.a_ches.timer.di

import com.a_ches.timer.presentation.viewModel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivityViewModel = module {
    viewModel { MainActivityViewModel() }
}