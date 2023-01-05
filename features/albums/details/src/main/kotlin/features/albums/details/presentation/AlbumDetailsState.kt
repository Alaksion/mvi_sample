package features.albums.details.presentation

import platform.database.models.models.AlbumModel
import platform.database.models.models.PhotoModelData

internal data class AlbumDetailsState(
    val album: AlbumModel = AlbumModel.fixture,
    val photos: List<PhotoModelData> = listOf(),
    val isInitialized: Boolean = false
)
