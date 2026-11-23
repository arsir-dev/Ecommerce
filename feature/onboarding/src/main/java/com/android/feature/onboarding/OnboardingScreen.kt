package com.android.feature.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.android.feature.onboarding.model.OnboardingPage
import com.android.feature.onboarding.section.OnboardingBottomSection
import com.android.feature.onboarding.section.OnboardingPagerSection
import com.android.feature.onboarding.ui.theme.EcommerceTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    uiState: OnboardingUiState,
    onFinishClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        pageCount = { uiState.pages.size }
    )

    val isLastPage = pagerState.currentPage == uiState.pages.size - 1

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                OnboardingPagerSection(
                    pages = uiState.pages,
                    pagerState = pagerState,
                    modifier = Modifier.weight(1f)
                )

                OnboardingBottomSection(
                    pageCount = uiState.pages.size,
                    currentPage = pagerState.currentPage,
                    isLastPage = isLastPage,
                    onFinishClicked = onFinishClicked
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    EcommerceTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(
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
            ),
            onFinishClicked = {}
        )
    }
}
