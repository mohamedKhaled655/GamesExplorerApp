package com.example.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalDataSource {

    companion object {
        private const val KEY_LAST_GENRE = "last_genre"
    }

    override fun saveLastGenre(genre: String) {
        sharedPreferences.edit().putString(KEY_LAST_GENRE, genre).apply()
    }

    override fun getLastGenre(): String? {
        return sharedPreferences.getString(KEY_LAST_GENRE, "action")
    }
}