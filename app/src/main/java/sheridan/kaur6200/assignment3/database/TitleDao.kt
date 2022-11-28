package sheridan.kaur6200.assignment3.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TitleDao {
    @Query("SELECT * FROM Todo")
    fun getAllTitleEntitiesFlow(): Flow<List<TitleEntity>>


    @Query("SELECT * FROM Todo WHERE id = :id")
    suspend fun getTitleEntityById(id: Long): TitleEntity

    @Insert
    suspend fun insertTitleEntity(todo: TitleEntity): Long

    @Delete
    suspend fun deleteTitleEntity(donut: TitleEntity)

    @Query("DELETE FROM Todo WHERE id=:id")
    suspend fun deleteTitleEntityById(id: Long)

    @Query("DELETE FROM Todo")
    suspend fun deleteAllTitleEntities()

    @Update
    suspend fun updateTitleEntity(donut: TitleEntity)

}