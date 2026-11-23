package com.android.feature.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun OnboardingRoute(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is OnboardingEvent.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }

    OnboardingScreen(
        uiState = uiState,
        onFinishClicked = {
            viewModel.completeOnboarding()
        },
        modifier = modifier
    )
}
