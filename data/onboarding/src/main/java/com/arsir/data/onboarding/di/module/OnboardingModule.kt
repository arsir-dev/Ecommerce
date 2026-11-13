package com.arsir.data.onboarding.di.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.arsir.data.onboarding.api.repository.OnboardingRepository
import com.arsir.data.onboarding.implementation.datastore.OnboardingDatastore
import com.arsir.data.onboarding.implementation.datastore.OnboardingDatastoreImpl
import com.arsir.data.onboarding.implementation.repository.OnboardingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnboardingModule {
    private const val ONBOARDING_DATASTORE_NAME = "onboarding_preferences"

    @Provides
    @Singleton
    fun provideOnboardingDatastoreInstance(
        onboardingDatastoreImpl: OnboardingDatastoreImpl
    ): OnboardingDatastore {
        return onboardingDatastoreImpl
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository {
        return onboardingRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideOnboardingDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(ONBOARDING_DATASTORE_NAME) }
        )
    }
}
