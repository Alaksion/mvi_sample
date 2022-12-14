package platform.database.models.models.album

import platform.database.models.data.entities.AlbumEntity
import platform.database.models.utils.dateFormatter
import java.time.LocalDate

data class AlbumModel(
    val id: Int,
    val name: String,
    val description: String,
    val createdAt: LocalDate,
    val updatedAt: LocalDate,
) {

    internal fun toAlbumEntity(): AlbumEntity {
        return AlbumEntity(
            id = this.id,
            name = this.name,
            description = this.description,
            createdAt = this.createdAt.format(dateFormatter),
            updatedAt = this.updatedAt.format(dateFormatter)
        )
    }

    companion object {
        val fixture = AlbumModel(
            id = 0,
            name = "some name",
            description = "some descritption",
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now()
        )
    }
}
