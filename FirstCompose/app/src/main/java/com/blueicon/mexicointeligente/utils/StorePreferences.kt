package com.blueicon.mexicointeligente.utils

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class StorePreferences(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.DATA_STORE)
        val USER_ID_KEY = intPreferencesKey(Constants.USER_ID)
        val USER_NAME_KEY = stringPreferencesKey(Constants.USER_NAME)
        val USER_EMAIL_KEY = stringPreferencesKey(Constants.USER_EMAIL)
        val USER_PHONE_KEY = stringPreferencesKey(Constants.USER_PHONE)
        val ONBOARDING_KEY = booleanPreferencesKey(Constants.ONBOARDING)
        val CREATE_REALSTATE__KEY = booleanPreferencesKey(Constants.CREATE_REALSTATE)
        val MENU_POSITION_KEY = intPreferencesKey(Constants.MENU_POSITION)
    }

    val getEmailUser: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_EMAIL_KEY] ?: "Sin email"
        }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email
        }
    }

    val getPhoneUser: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_PHONE_KEY] ?: "Sin phone"
        }

    suspend fun savePhone(phone: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PHONE_KEY] = phone
        }
    }

    val getNameUser: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USER_NAME_KEY] ?: "Sin email"
        }

    suspend fun saveName(name: String) {
        Log.e("StorePreferences saveName:", name)
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    val getUserId: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[USER_ID_KEY] ?: 0
        }

    suspend fun saveId(userId: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    val getStatusOnboarding: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[ONBOARDING_KEY] ?: false
        }

    suspend fun saveStatusOnboarding(status: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_KEY] = status
        }
    }

    val getHideCreateRealState: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[CREATE_REALSTATE__KEY] ?: false
        }

    suspend fun saveHideCreateRealState(status: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[CREATE_REALSTATE__KEY] = status
        }
    }

    val getMenuPosition: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[MENU_POSITION_KEY] ?: 0
        }

    suspend fun saveMenuPosition(menuPosition: Int) {
        context.dataStore.edit { preferences ->
            preferences[MENU_POSITION_KEY] = menuPosition
        }
    }
}