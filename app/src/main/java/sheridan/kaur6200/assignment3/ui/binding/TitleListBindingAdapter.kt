package sheridan.kaur6200.assignment3.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import sheridan.kaur6200.assignment3.model.Title
import sheridan.kaur6200.assignment3.ui.titlelist.TitleListAdapter

@BindingAdapter("titleList")
fun bindDonutList(recyclerView: RecyclerView, list: List<Title>?) {
    if (list is List<Title>) {
        val adapter = recyclerView.adapter as TitleListAdapter
        adapter.submitList(list)
    }
}