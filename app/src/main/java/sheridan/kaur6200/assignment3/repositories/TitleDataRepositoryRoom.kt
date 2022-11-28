package sheridan.kaur6200.assignment3.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import sheridan.kaur6200.assignment3.database.TitleDao
import sheridan.kaur6200.assignment3.database.TitleEntity
import sheridan.kaur6200.assignment3.model.Title
import javax.inject.Inject

class TitleRepositoryRoom @Inject constructor(private val titleDao: TitleDao)
    : TitleDataRepository{


    companion object{
        private const val TAG = "TitleRepositoryRoom"
    }
    init{
        Log.d(TAG, "init: the TitleRepositoryRoom object is created")
    }

    override fun getAllTitlesFlow(): Flow<List<Title>> =
        titleDao.getAllTitleEntitiesFlow()
            .map { list -> list.map { it.asTitle() } }
            .flowOn(Dispatchers.IO)

    override suspend fun getTitleById(id: String): Title {
        return titleDao.getTitleEntityById(id.toLong()).asTitle()
    }

    override suspend fun insertTitle(donut: Title): String {
        return titleDao.insertTitleEntity(donut.asEntity()).toString()
    }

    override suspend fun deleteTitle(donut: Title) {
        if (donut.id != null) {
            titleDao.deleteTitleEntity(donut.asEntity())
        }
    }

    override suspend fun deleteTitleById(id: String) {
        titleDao.deleteTitleEntityById(id.toLong())
    }

    override suspend fun updateTitle(donut: Title) {
        titleDao.updateTitleEntity(donut.asEntity())
    }

    override suspend fun deleteAllTitles() {
        titleDao.deleteAllTitleEntities()
    }
}

fun TitleEntity.asTitle(): Title {
    return Title(
        id = id.toString(),
        title = title,
        description = description,
        doneFlag = doneFlag,
        dueDate = dueDate
    )
}

fun Title.asEntity(): TitleEntity {
    return TitleEntity(
        id = id?.toLong() ?: 0L,
        title = title,
        description = description,
        doneFlag = doneFlag,
        dueDate = dueDate)
}