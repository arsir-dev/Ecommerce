package com.arsir.data.onboarding.implementation.datastore

import kotlinx.coroutines.flow.Flow

interface OnboardingDatastore {
    fun isFirstInstall(): Flow<Boolean>

    suspend fun setFirstInstall(isFirstInstall: Boolean)
}