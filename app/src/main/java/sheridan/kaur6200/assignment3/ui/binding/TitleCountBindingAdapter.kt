package sheridan.kaur6200.assignment3.ui.binding

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import sheridan.kaur6200.assignment3.R
import sheridan.kaur6200.assignment3.model.Title

@SuppressLint("SetTextI18n")
@BindingAdapter("titleCount")
fun bindDonutCount(textView: TextView, list: List<Title>?){
    val count = list?.size ?: 0
    textView.text =
        //"{title_count}To Dos"
    textView.resources.getQuantityString(R.plurals.title_count, count, count)
}