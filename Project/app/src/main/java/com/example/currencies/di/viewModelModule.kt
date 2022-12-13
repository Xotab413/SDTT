package com.example.currencies.di

import com.example.currencies.presentation.currencies_fragment.CurrenciesViewModel
import com.example.currencies.presentation.dynamics_fragment.DynamicsViewModel
import com.example.currencies.presentation.settings_fragment.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CurrenciesViewModel(get(), get(), get(), get(), get()) }
    viewModel { SettingsViewModel(get(), get()) }
    viewModel { DynamicsViewModel(get()) }
}
