package sheridan.kaur6200.assignment3.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("doneFlagValue")
fun bindDoneFlagValue(textView: TextView, value: Boolean?){
    if (value is Boolean) {
        val resources = textView.resources
        textView.text =
            if (value) {
              "Done"
            } else {
              "Not Done"
            }
    }
}