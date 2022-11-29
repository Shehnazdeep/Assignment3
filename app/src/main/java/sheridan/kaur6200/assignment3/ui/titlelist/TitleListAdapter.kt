package sheridan.kaur6200.assignment3.ui.titlelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sheridan.kaur6200.assignment3.databinding.TitleListItemBinding
import sheridan.kaur6200.assignment3.model.Title

class TitleListAdapter( private var onItemClick: (itemId: String) -> Unit,
                        private var onItemDelete: (itemId: String) -> Unit) :
    ListAdapter<Title, TitleListAdapter.ViewHolder>(ITEM_COMPARATOR) {

    inner class ViewHolder(
        private val binding: TitleListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(title: Title) {
            binding.title = title
            with(binding){
                deleteButton.setOnClickListener {
                    onItemDelete(title.id!!)
                }
                root.setOnClickListener {
                    onItemClick(title.id!!)
                }
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TitleListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Title>() {

            // Called to check whether two objects represent the same item.
            override fun areItemsTheSame(oldItem: Title, newItem: Title): Boolean {
                return oldItem.id == newItem.id
            }

            // Called to check whether two items have the same data
            override fun areContentsTheSame(oldItem: Title, newItem: Title): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}