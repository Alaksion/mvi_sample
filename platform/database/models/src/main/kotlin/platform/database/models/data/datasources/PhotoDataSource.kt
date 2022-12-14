package platform.database.models.data.datasources

import platform.database.models.UriPermissionHandler
import platform.database.models.data.entities.PhotoEntityDao
import platform.database.models.models.photo.PhotoModel
import platform.database.models.models.photo.mapToEntity
import platform.database.models.utils.runQuery
import platform.logs.loggers.AppLogger
import javax.inject.Inject

interface PhotoDataSource {

    suspend fun addPhotos(photos: List<PhotoModel>)

    suspend fun getPhotoById(photoId: Int): PhotoModel

    suspend fun deletePhoto(photo: PhotoModel)

}

internal class PhotoDataSourceImpl @Inject constructor(
    private val photoDao: PhotoEntityDao,
    private val logger: AppLogger,
    private val uriPermission: UriPermissionHandler

) : PhotoDataSource {

    override suspend fun addPhotos(photos: List<PhotoModel>) {
        runQuery(logger) {
            photoDao.addPhotos(
                photos.map { photoModel ->
                    uriPermission.registerPersistentPermission(photoModel.location)
                    photoModel.mapToEntity()
                }
            )
        }
    }

    override suspend fun getPhotoById(photoId: Int): PhotoModel {
        return runQuery(logger) { photoDao.getPhoto(photoId).mapToModel() }
    }

    override suspend fun deletePhoto(photo: PhotoModel) {
        runQuery(logger) {
            photoDao.deletePhoto(photo.mapToEntity())
        }
    }

}
