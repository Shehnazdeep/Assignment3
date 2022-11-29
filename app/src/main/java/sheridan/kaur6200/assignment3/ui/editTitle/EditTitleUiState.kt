package sheridan.kaur6200.assignment3.ui.editTitle

import sheridan.kaur6200.assignment3.model.Title
import java.util.*

data class EditTitleUiState(
    val isTitleLoaded: Boolean,
    val dueDate: Date,
    val initialTitle: Title
)
