package features.albums.details.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import database.models.models.PhotoModel
import features.albums.shared.domain.repository.AlbumRepository
import kotlinx.coroutines.CoroutineDispatcher
import platform.injection.IODispatcher
import platform.uistate.uistate.UiStateViewModel
import javax.inject.Inject

internal sealed class AlbumDetailsIntent {
    data class LoadAlbumData(val albumId: Int) : AlbumDetailsIntent()
    data class RetryLoad(val albumId: Int) : AlbumDetailsIntent()
}

@HiltViewModel
internal class AlbumDetailsViewModel @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val repository: AlbumRepository,
) : UiStateViewModel<AlbumDetailsState>(AlbumDetailsState(), dispatcher) {

    fun handleIntent(intent: AlbumDetailsIntent) {
        when (intent) {
            is AlbumDetailsIntent.LoadAlbumData -> loadData(intent.albumId)
            is AlbumDetailsIntent.RetryLoad -> loadData(intent.albumId, true)
        }
    }

    private fun loadData(
        albumId: Int,
        forceLoad: Boolean = false
    ) {
        if (stateData.isInitialized.not() || forceLoad) {
            setState { currentState ->
                val response = repository.getAlbumById(albumId)
                currentState.copy(
                    album = response.album,
                    photos = listOf(
                        PhotoModel.Remote("https://via.placeholder.com/150"),
                        PhotoModel.Remote("https://via.placeholder.com/150"),
                        PhotoModel.Remote("https://via.placeholder.com/150")
                    ),
                    isInitialized = true
                )
            }
        }
    }

}