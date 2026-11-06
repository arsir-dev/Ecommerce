package com.arsir.data.onboarding.implementation.repository

import com.arsir.data.onboarding.api.repository.OnboardingRepository
import com.arsir.data.onboarding.implementation.datastore.OnboardingDatastore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingDatastore: OnboardingDatastore
) : OnboardingRepository {
    override fun isFirstInstall(): Flow<Boolean> {
        return onboardingDatastore.isFirstInstall()
    }

    override suspend fun setFirstInstall(isFirstInstall: Boolean) = withContext(Dispatchers.IO) {
        onboardingDatastore.setFirstInstall(isFirstInstall = isFirstInstall)
    }

}