package com.android.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsir.data.onboarding.api.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class SplashDestination {
    LOADING,
    LOGIN,
    ONBOARDING
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

    private val _splashDestination = MutableStateFlow(SplashDestination.LOADING)
    val splashDestination: StateFlow<SplashDestination> = _splashDestination.asStateFlow()

    init {
        startSplashTimerAndCheckFirstInstall()
    }

    private fun startSplashTimerAndCheckFirstInstall() {
        viewModelScope.launch {
            // Keep the delay for the splash screen duration
            delay(3500)
            
            // Check if it's the first install
            val isFirstInstall = onboardingRepository.isFirstInstall().first()
            
            if (isFirstInstall) {
                _splashDestination.value = SplashDestination.ONBOARDING
            } else {
                _splashDestination.value = SplashDestination.LOGIN
            }
        }
    }
}
