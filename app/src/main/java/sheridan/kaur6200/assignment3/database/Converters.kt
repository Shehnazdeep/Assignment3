package sheridan.kaur6200.assignment3.database

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun fromLongToDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun fromDateToLong(dueDate: Date): Long {
        return dueDate.time
    }

    }
