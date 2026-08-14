package com.example.smartwallet.Data.Datastore

import android.content.Context
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(
    name = "user_session"
)

class UserSession(
    private val context: Context
) {

    private companion object {
        val CURRENT_USER_ID = longPreferencesKey("current_user_id")
    }

    suspend fun saveUserId(userId: Long) {
        context.dataStore.edit { preferences ->
            preferences[CURRENT_USER_ID] = userId
        }
    }

    suspend fun getUserId(): Long? {
        val preferences = context.dataStore.data.first()

        return preferences[CURRENT_USER_ID]
    }

    suspend fun clearUserId() {
        context.dataStore.edit { preferences ->
            preferences.remove(CURRENT_USER_ID)
        }
    }
}
