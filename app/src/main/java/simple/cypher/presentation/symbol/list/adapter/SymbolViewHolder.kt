package simple.cypher.presentation.symbol.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.symbol_list_item.view.*
import simple.cypher.R

class SymbolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: SymbolItem, onClickListener: (SymbolItem) -> Unit) = with(itemView) {
        setOnClickListener { onClickListener(item) }
        symbolText.text = item.symbol.toString()
        replacementText.text = item.replacement.toString()
    }

    companion object {
        fun create(parent: ViewGroup) = SymbolViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.symbol_list_item,
                parent,
                false
            )
        )
    }
}