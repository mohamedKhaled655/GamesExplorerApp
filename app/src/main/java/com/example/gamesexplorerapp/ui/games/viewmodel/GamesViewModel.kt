package com.example.gamesexplorerapp.ui.games.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Result
import com.example.domain.model.Game
import com.example.domain.model.GamesResponse
import com.example.domain.usecase.GetGamesUseCase
import com.example.domain.usecase.GetLastGenreUseCase
import com.example.domain.usecase.SaveLastGenreUseCase
import com.example.domain.usecase.SearchGamesLocallyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
    private val saveLastGenreUseCase: SaveLastGenreUseCase,
    private val getLastGenreUseCase: GetLastGenreUseCase,
    private val searchGamesLocallyUseCase: SearchGamesLocallyUseCase
) : ViewModel() {

    private val _gamesState = MutableStateFlow<Result<GamesResponse>>(Result.Loading)
    val gamesState = _gamesState.asStateFlow()

    private val _selectedGenre = MutableStateFlow<String>("action")
    val selectedGenre = _selectedGenre.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _filteredGames = MutableStateFlow<List<Game>>(emptyList())
    val filteredGames = _filteredGames.asStateFlow()

    private val _currentPage = MutableStateFlow(1)
    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore = _isLoadingMore.asStateFlow()

    private var allLoadedGames = mutableListOf<Game>()

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    filterGames(query)
                }
        }
    }

    fun loadLastGenre() {
        viewModelScope.launch {
            val lastGenre = getLastGenreUseCase() ?: "action"
            _selectedGenre.value = lastGenre
            loadGames(lastGenre, resetPage = true)
        }
    }

    fun onGenreSelected(genre: String) {
        if (_selectedGenre.value != genre) {
            _selectedGenre.value = genre
            _searchQuery.value = ""
            loadGames(genre, resetPage = true)
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun loadMoreGames() {
        if (_isLoadingMore.value) return

        viewModelScope.launch {
            _isLoadingMore.value = true
            val nextPage = _currentPage.value + 1

            getGamesUseCase(_selectedGenre.value, nextPage, 20)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _isLoadingMore.value = false
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> {
                            allLoadedGames.addAll(result.data.results)
                            _currentPage.value = nextPage
                            filterGames(_searchQuery.value)
                        }
                        else -> {}
                    }
                    _isLoadingMore.value = false
                }
        }
    }

    private fun loadGames(genre: String, resetPage: Boolean = false) {
        viewModelScope.launch {
            if (resetPage) {
                _currentPage.value = 1
                allLoadedGames.clear()
            }

            getGamesUseCase(genre, _currentPage.value, 20)
                .flowOn(Dispatchers.IO)
                .catch { e -> _gamesState.value = Result.Failure(e) }
                .collect { result ->
                    _gamesState.value = result

                    if (result is Result.Success) {
                        allLoadedGames.clear()
                        allLoadedGames.addAll(result.data.results)
                        filterGames(_searchQuery.value)
                        saveLastGenreUseCase(genre)
                    }
                }
        }
    }

    private fun filterGames(query: String) {
        _filteredGames.value = if (query.isBlank()) {
            allLoadedGames
        } else {
            searchGamesLocallyUseCase(query, allLoadedGames)
        }
    }
}