package sheridan.kaur6200.assignment3.model

import java.util.*

data class Title(
    val id: String? = null,
    val title: String = "",
    val description: String = "",
    val doneFlag: Boolean = false,
    val dueDate: Date = Date()
)