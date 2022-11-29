package sheridan.kaur6200.assignment3.ui.binding

import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

private val dateFormatter =
    DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")


fun formatDate(date: Date): String {
    return date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(dateFormatter)
}