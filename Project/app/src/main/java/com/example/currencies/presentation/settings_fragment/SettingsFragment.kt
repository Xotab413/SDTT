package com.example.currencies.presentation.settings_fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencies.R
import com.example.currencies.databinding.SettingsFragmentBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {

    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<SettingsViewModel>()

    private var adapterq = RecyclerViewAdapter()

    private val itemTouchHelper by lazy {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                // getting the adapter
                val adapter = recyclerView.adapter as RecyclerViewAdapter

                // the position from where item has been moved
                val from = viewHolder.adapterPosition

                // the position where the item is moved
                val to = target.adapterPosition

                val tmp = adapter.currentList[from]
                viewModel.apply {
                    state.value.removeAt(from)
                    state.value.add(to, tmp)
                }
                adapter.notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }
        }

        ItemTouchHelper(simpleItemTouchCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.settings.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterq
            setItemViewCacheSize(30)
        }
        itemTouchHelper.attachToRecyclerView(binding.settings)
        lifecycleScope.launchWhenCreated {
            viewModel.setCurrencies()
            viewModel.state.collect {
                adapterq.submitList(it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigate_up -> {
                viewModel.save()
                findNavController().navigateUp()
            }
            else -> findNavController().navigateUp()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
