package com.example.gamesexplorerapp.ui.games.views

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GenreChips(
    selectedGenre: String,
    onGenreSelected: (String) -> Unit
) {
    val genres = listOf(
        "All Games" to "",
        "Action" to "action",
        "RPG" to "role-playing-games-rpg",
        "Strategy" to "strategy",
        "Shooter" to "shooter",
        "Adventure" to "adventure",
        "Puzzle" to "puzzle",
        "Racing" to "racing",
        "Sports" to "sports"
    )

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        genres.forEach { (label, slug) ->
            FilterChip(
                selected = selectedGenre == slug,
                onClick = { onGenreSelected(slug) },
                label = { Text(label) },
                modifier = Modifier.padding(end = 8.dp),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}