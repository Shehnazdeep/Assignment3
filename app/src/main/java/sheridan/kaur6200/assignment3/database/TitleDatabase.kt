package sheridan.kaur6200.assignment3.database

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [TitleEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TitleDatabase: RoomDatabase() {
    abstract fun titleDao(): TitleDao
}