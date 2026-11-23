package com.android.feature.onboarding.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnboardingPage(
    val id: Int,
    @DrawableRes val imageResId: Int,
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int
)
