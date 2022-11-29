package sheridan.kaur6200.assignment3.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter

import java.util.*

@BindingAdapter("dateValue")
fun bindDate(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = formatDate(date)
}
