package simple.cypher.presentation.symbol.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.symbol_list_fragment.*
import simple.cypher.R
import simple.cypher.presentation.base.BaseFragment
import simple.cypher.presentation.symbol.dialog.SymbolDialog
import simple.cypher.presentation.symbol.list.adapter.SymbolListAdapter
import util.extensions.hideKeyboard
import util.extensions.onClick

class SymbolListFragment : BaseFragment(R.layout.symbol_list_fragment) {

    val viewModel by viewModels<SymbolListViewModel> { SymbolListViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSymbolList()
        setListeners()
        setObservers()
    }

    fun setSymbolList() = symbolList.run {
        adapter = SymbolListAdapter().apply {
            viewModel.liveSymbolItemList(this::submitList)
            onClick(viewModel::onItemClick)
        }
        layoutManager = GridLayoutManager(context, calculateSymbolListSpanCount())
    }

    fun setListeners() {
        addButton.onClick(viewModel::onAddButtonClick)
    }

    fun setObservers() {
        viewModel.liveOpenSymbolDialog(this::openSymbolDialog)
    }

    fun calculateSymbolListSpanCount() = resources.displayMetrics.run {
        val itemWidth = density * 96
        val margins = density * 30
        ((widthPixels - margins) / itemWidth).toInt()
    }

    fun openSymbolDialog(arguments: Bundle?) {
        hideKeyboard()
        val dialog = SymbolDialog()
        dialog.arguments = arguments
        dialog.show(childFragmentManager, null)
    }
}