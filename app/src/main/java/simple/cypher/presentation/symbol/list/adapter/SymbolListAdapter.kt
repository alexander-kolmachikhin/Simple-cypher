package simple.cypher.presentation.symbol.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class SymbolListAdapter : ListAdapter<SymbolItem, SymbolViewHolder>(
    object : DiffUtil.ItemCallback<SymbolItem>() {
        override fun areItemsTheSame(oldItem: SymbolItem, newItem: SymbolItem) = oldItem == newItem
        override fun areContentsTheSame(oldItem: SymbolItem, newItem: SymbolItem) = oldItem == newItem
    }
) {

    private var onClickListener = { _: SymbolItem -> }

    fun onClick(listener: (SymbolItem) -> Unit) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SymbolViewHolder.create(parent)

    override fun onBindViewHolder(holder: SymbolViewHolder, position: Int) = holder.bind(getItem(holder.adapterPosition), onClickListener)

}