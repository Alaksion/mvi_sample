package features.albums.shared.repository

import features.albums.shared.domain.model.CreateAlbumDTO
import features.albums.shared.domain.repository.AlbumRepository
import platform.database.models.models.AlbumModel
import platform.database.models.models.AlbumWithPhotosModel
import platform.database.models.models.CreateAlbumModel
import javax.inject.Inject

internal class AlbumsRepositoryImpl @Inject constructor(
    private val albumDataSource: platform.database.models.data.datasources.AlbumDataSource
) : AlbumRepository {

    override suspend fun getAlbums(): List<AlbumModel> {
        return albumDataSource.getAll()
    }

    override suspend fun getAlbumById(id: Int): AlbumWithPhotosModel {
        return albumDataSource.getById(id)
    }

    override suspend fun createAlbum(data: CreateAlbumDTO) {
        albumDataSource.create(
            CreateAlbumModel(
                name = data.name,
                description = data.description
            )
        )
    }

}
