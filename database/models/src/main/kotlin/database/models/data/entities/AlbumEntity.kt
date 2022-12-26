package database.models.data.entities

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import database.models.models.AlbumModel
import database.models.models.CreateAlbumModel
import database.models.utils.dateFormatter
import java.time.LocalDate
import java.util.UUID

@Entity(tableName = "album")
internal data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "created_at")
    val createdAt: String,

    @ColumnInfo(name = "updated_at")
    val updatedAt: String
) {
    internal fun mapToModel(): AlbumModel {
        return AlbumModel(
            id = this.id,
            name = this.name,
            description = this.description,
            createdAt = LocalDate.from(dateFormatter.parse(this.createdAt)),
            updatedAt = LocalDate.from(dateFormatter.parse(this.createdAt))
        )
    }

    companion object {
        fun createFromModel(model: CreateAlbumModel): AlbumEntity {
            return AlbumEntity(
                id = 0,
                createdAt = LocalDate.now().format(dateFormatter),
                updatedAt = LocalDate.now().format(dateFormatter),
                name = model.name,
                description = model.description
            )
        }

        val fixture = AlbumEntity(
            id = 0,
            name = "sample value",
            description = "sample description",
            createdAt = "22-12-2022",
            updatedAt = "22-12-2022"
        )
    }
}

@Dao
internal interface AlbumEntityDao {

    @Query("SELECT * FROM album")
    suspend fun getAll(): List<AlbumEntity>

    @Query("SELECT * FROM album where id = :albumId")
    suspend fun getById(albumId: UUID): AlbumEntity

    @Insert
    suspend fun create(album: AlbumEntity)

    @Delete
    suspend fun delete(album: AlbumEntity)
}