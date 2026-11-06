package com.arsir.data.onboarding.api.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {

    fun isFirstInstall(): Flow<Boolean>

    suspend fun setFirstInstall(isFirstInstall: Boolean)
}