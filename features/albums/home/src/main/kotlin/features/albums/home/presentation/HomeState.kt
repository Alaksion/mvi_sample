package features.albums.home.presentation

import database.models.models.AlbumModel

internal data class HomeState(
    val isInitialized: Boolean = false,
    val albums: List<AlbumModel> = listOf(),
)