package com.example.gamesexplorerapp.ui.game_details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Result
import com.example.domain.model.GameDetail
import com.example.domain.usecase.GetGameDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val getGameDetailsUseCase: GetGameDetailsUseCase
) : ViewModel() {

    private val _gameDetailsState = MutableStateFlow<Result<GameDetail>>(Result.Loading)
    val gameDetailsState = _gameDetailsState.asStateFlow()

    fun loadGameDetails(gameId: Int) {
        viewModelScope.launch {
            getGameDetailsUseCase(gameId)
                .flowOn(Dispatchers.IO)
                .catch { e -> _gameDetailsState.value = Result.Failure(e) }
                .collect { result ->
                    _gameDetailsState.value = result
                }
        }
    }
}