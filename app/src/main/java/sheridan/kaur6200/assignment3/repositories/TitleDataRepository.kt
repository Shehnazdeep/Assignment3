package sheridan.kaur6200.assignment3.repositories

import kotlinx.coroutines.flow.Flow
import sheridan.kaur6200.assignment3.model.Title

interface TitleDataRepository {

    fun getAllTitlesFlow(): Flow<List<Title>>
    suspend fun getTitleById(id: String): Title
    suspend fun insertTitle(donut: Title): String
    suspend fun deleteTitle(donut: Title)
    suspend fun deleteTitleById(id: String)
    suspend fun updateTitle(donut: Title)
    suspend fun deleteAllTitles()

}