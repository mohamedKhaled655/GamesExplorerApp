package com.example.gamesexplorerapp.ui.game_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.core.utils.Result
import com.example.domain.model.GameDetail
import com.example.gamesexplorerapp.ui.component.CustomError
import com.example.gamesexplorerapp.ui.component.CustomLoading
import com.example.gamesexplorerapp.ui.game_details.viewmodel.GameDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsScreen(
    gameId: Int,
    onBackClick: () -> Unit,
    viewModel: GameDetailsViewModel = hiltViewModel()
) {
    val gameDetailsState by viewModel.gameDetailsState.collectAsState()

    LaunchedEffect(gameId) {
        viewModel.loadGameDetails(gameId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Game Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    //containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        when (val state = gameDetailsState) {
            is Result.Loading -> {
                CustomLoading()
            }

            is Result.Success -> {
                GameDetailsContent(
                    game = state.data,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is Result.Failure -> {
                CustomError(
                    message = state.exception.message ?: "Failed to load game details"
                )
            }
        }
    }
}

@Composable
fun GameDetailsContent(
    game: GameDetail,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(game.backgroundImage)
                .crossfade(true)
                .build(),
            contentDescription = game.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = game.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "⭐", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = String.format("%.1f", game.rating),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = " / ${game.ratingTop}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }


                game.metacritic?.let { score ->
                    Surface(
                        color = when {
                            score >= 75 -> Color(0xFF6DC849)
                            score >= 50 -> Color(0xFFFFBD3F)
                            else -> Color(0xFFFF6874)
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Metacritic: $score",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                game.released?.let {
                    InfoChip(label = "Released", value = it)
                }

                if (game.playtime > 0) {
                    InfoChip(label = "Playtime", value = "${game.playtime} hours")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            if (game.genres.isNotEmpty()) {
                SectionTitle("Genres")
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    game.genres.take(4).forEach { genre ->
                        AssistChip(
                            onClick = {},
                            label = { Text(genre.name) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }


            if (game.platforms.isNotEmpty()) {
                SectionTitle("Platforms")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = game.platforms.joinToString(", ") { it.name },
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
            }


            game.description?.let { desc ->
                SectionTitle("About")
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
            }


            if (game.developers.isNotEmpty()) {
                SectionTitle("Developers")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = game.developers.joinToString(", ") { it.name },
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
            }


            if (game.publishers.isNotEmpty()) {
                SectionTitle("Publishers")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = game.publishers.joinToString(", ") { it.name },
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
            }


            game.esrbRating?.let { rating ->
                SectionTitle("ESRB Rating")
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = rating.name,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun InfoChip(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}