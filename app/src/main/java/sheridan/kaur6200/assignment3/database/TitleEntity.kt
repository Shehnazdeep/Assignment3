package sheridan.kaur6200.assignment3.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "Todo")
data class TitleEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
   val title: String,
    val description: String = "",

    @ColumnInfo(name = "done_flag")
    val doneFlag: Boolean,

    @ColumnInfo(name = "due_date")
    val dueDate: Date = Date()




)
