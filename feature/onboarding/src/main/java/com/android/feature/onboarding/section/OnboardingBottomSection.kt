package com.android.feature.onboarding.section

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.feature.onboarding.R
import com.android.feature.onboarding.component.PageIndicator
import com.android.feature.onboarding.component.PrimaryButton

@Composable
fun OnboardingBottomSection(
    pageCount: Int,
    currentPage: Int,
    isLastPage: Boolean,
    onFinishClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PageIndicator(
            pageCount = pageCount,
            currentPage = currentPage
        )

        Spacer(modifier = Modifier.height(32.dp))

        AnimatedVisibility(
            visible = isLastPage,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            PrimaryButton(
                text = stringResource(id = R.string.onboarding_button_start),
                onClick = onFinishClicked
            )
        }
    }
}
