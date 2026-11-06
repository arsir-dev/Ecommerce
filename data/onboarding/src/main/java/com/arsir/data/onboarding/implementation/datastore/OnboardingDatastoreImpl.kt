package com.arsir.data.onboarding.implementation.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnboardingDatastoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : OnboardingDatastore {
    override fun isFirstInstall(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_FIRST_INSTALL_KEY] ?: true
        }
    }

    override suspend fun setFirstInstall(isFirstInstall: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_FIRST_INSTALL_KEY] = isFirstInstall
        }
    }

    private companion object {
        private val IS_FIRST_INSTALL_KEY = booleanPreferencesKey("is_first_install")
    }
}