package com.example.gamesexplorerapp.ui.games

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.utils.Result
import com.example.gamesexplorerapp.ui.component.CustomEmpty
import com.example.gamesexplorerapp.ui.component.CustomError
import com.example.gamesexplorerapp.ui.component.CustomLoading

import com.example.gamesexplorerapp.ui.games.viewmodel.GamesViewModel
import com.example.gamesexplorerapp.ui.games.views.GamesList
import com.example.gamesexplorerapp.ui.games.views.GenreChips
import com.example.gamesexplorerapp.ui.games.views.SearchField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(
    viewModel: GamesViewModel = hiltViewModel(),
    onGameClick: (Int) -> Unit
) {
    val gamesState by viewModel.gamesState.collectAsState()
    val selectedGenre by viewModel.selectedGenre.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredGames by viewModel.filteredGames.collectAsState()
    val isLoadingMore by viewModel.isLoadingMore.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLastGenre()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Games Explorer") },
                colors = TopAppBarDefaults.topAppBarColors(
                   // containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Spacer(modifier = Modifier.height(8.dp))
            SearchField(
                query = searchQuery,
                onQueryChange = { viewModel.onSearchQueryChanged(it) },
                onClear = { viewModel.onSearchQueryChanged("") }
            )

            Spacer(modifier = Modifier.height(8.dp))


            GenreChips(
                selectedGenre = selectedGenre,
                onGenreSelected = { viewModel.onGenreSelected(it) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Content
            when (val state = gamesState) {
                is Result.Loading -> {
                    CustomLoading()
                }

                is Result.Success -> {
                    val gamesToShow = if (searchQuery.isNotEmpty()) {
                        filteredGames
                    } else {
                        state.data.results
                    }

                    if (gamesToShow.isEmpty()) {
                        CustomEmpty()
                    } else {
                        GamesList(
                            games = gamesToShow,
                            isLoadingMore = isLoadingMore,
                            onGameClick = onGameClick,
                            onLoadMore = {
                                if (searchQuery.isEmpty()) {
                                    viewModel.loadMoreGames()
                                }
                            }
                        )
                    }
                }

                is Result.Failure -> {
                    CustomError(
                        message = state.exception.message ?: "Failed to load games"
                    )
                }
            }
        }
    }
}