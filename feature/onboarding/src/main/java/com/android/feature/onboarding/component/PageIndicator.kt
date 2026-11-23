package com.android.feature.onboarding.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.android.feature.onboarding.constant.OnboardingConstants
import com.android.feature.onboarding.ui.theme.IndicatorInactive
import com.android.feature.onboarding.ui.theme.PrimaryBlue

@Composable
fun PageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(OnboardingConstants.PAGE_INDICATOR_SPACING_DP.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage
            val width by animateDpAsState(
                targetValue = if (isSelected) {
                    OnboardingConstants.PAGE_INDICATOR_ACTIVE_WIDTH_DP.dp
                } else {
                    OnboardingConstants.PAGE_INDICATOR_INACTIVE_SIZE_DP.dp
                },
                animationSpec = tween(durationMillis = 300),
                label = "indicator_width"
            )

            Box(
                modifier = Modifier
                    .height(OnboardingConstants.PAGE_INDICATOR_INACTIVE_SIZE_DP.dp)
                    .width(width)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) PrimaryBlue else IndicatorInactive
                    )
            )
        }
    }
}
