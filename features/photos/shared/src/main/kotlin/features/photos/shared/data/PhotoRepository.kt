package features.photos.shared.data

import android.net.Uri
import platform.database.models.data.datasources.PhotoDataSource
import platform.database.models.models.photo.PhotoModel
import javax.inject.Inject

interface PhotoRepository {
    suspend fun addPhotos(albumId: Int, photos: List<Uri>)

    suspend fun getPhotoById(photoId: Int): PhotoModel

    suspend fun deletePhoto(photo: PhotoModel)
}

internal class PhotoRepositoryImpl @Inject constructor(
    private val dataSource: PhotoDataSource
) : PhotoRepository {

    override suspend fun addPhotos(albumId: Int, photos: List<Uri>) {
        val photoModels = photos.map {
            PhotoModel(
                photoId = 0,
                albumId = albumId,
                location = it
            )
        }
        dataSource.addPhotos(photoModels)
    }

    override suspend fun getPhotoById(photoId: Int): PhotoModel {
        return dataSource.getPhotoById(photoId)
    }

    override suspend fun deletePhoto(photo: PhotoModel) {
        dataSource.deletePhoto(photo)
    }

}
