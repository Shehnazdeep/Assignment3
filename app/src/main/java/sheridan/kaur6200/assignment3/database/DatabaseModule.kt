package sheridan.kaur6200.assignment3.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val TAG = "DataBaseModule"

    @Provides
    fun provideTitleDao(database: TitleDatabase): TitleDao {
        Log.d(TAG, "provideTitleDao: the TitleDao object is returned")
        return database.titleDao()
    }

    @Singleton
    @Provides
    fun provideTitleDatabase(@ApplicationContext context: Context): TitleDatabase {
        Log.d(TAG, "provideTitleDatabase: the database object is created")
        return Room.databaseBuilder(
            context,
            TitleDatabase::class.java,
            "title_database"
        ).build()
    }
}