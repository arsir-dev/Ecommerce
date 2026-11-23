package com.android.feature.onboarding.section

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.android.feature.onboarding.component.OnboardingPageItem
import com.android.feature.onboarding.constant.OnboardingConstants
import com.android.feature.onboarding.model.OnboardingPage
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPagerSection(
    pages: List<OnboardingPage>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress && pagerState.currentPage < pages.size - 1) {
            delay(OnboardingConstants.AUTO_SLIDE_DURATION_MS)
            if (!pagerState.isScrollInProgress && pagerState.currentPage < pages.size - 1) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) { pageIndex ->
        pages.getOrNull(pageIndex)?.let { page ->
            OnboardingPageItem(page = page)
        }
    }
}
