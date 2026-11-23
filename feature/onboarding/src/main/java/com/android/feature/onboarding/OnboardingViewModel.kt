package com.android.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.feature.onboarding.model.OnboardingPage
import com.arsir.data.onboarding.api.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OnboardingUiState(
    val pages: List<OnboardingPage> = emptyList(),
    val isLoading: Boolean = false
)

sealed interface OnboardingEvent {
    data object NavigateToLogin : OnboardingEvent
}

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        OnboardingUiState(
            pages = listOf(
                OnboardingPage(
                    id = 1,
                    imageResId = R.drawable.belanja,
                    titleResId = R.string.onboarding_page1_title,
                    descriptionResId = R.string.onboarding_page1_description
                ),
                OnboardingPage(
                    id = 2,
                    imageResId = R.drawable.produk,
                    titleResId = R.string.onboarding_page2_title,
                    descriptionResId = R.string.onboarding_page2_description
                ),
                OnboardingPage(
                    id = 3,
                    imageResId = R.drawable.aman,
                    titleResId = R.string.onboarding_page3_title,
                    descriptionResId = R.string.onboarding_page3_description
                )
            )
        )
    )
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<OnboardingEvent>()
    val event: SharedFlow<OnboardingEvent> = _event.asSharedFlow()

    fun completeOnboarding() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            onboardingRepository.setFirstInstall(false)
            _uiState.value = _uiState.value.copy(isLoading = false)
            _event.emit(OnboardingEvent.NavigateToLogin)
        }
    }
}
