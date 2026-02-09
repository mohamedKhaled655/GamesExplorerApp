package com.example.data.local

interface LocalDataSource {
    fun saveLastGenre(genre: String)
    fun getLastGenre(): String?
}