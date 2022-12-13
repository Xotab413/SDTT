package com.example.currencies.presentation.currencies_fragment

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencies.R
import com.example.currencies.common.Constants.dateFormatForUI
import com.example.currencies.databinding.CurrenciesFragmentBinding
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrenciesFragment : Fragment() {

    private val viewModel by viewModel<CurrenciesViewModel>()
    private var _binding: CurrenciesFragmentBinding? = null
    private val binding get() = _binding!!

    private var customAdapter = CurrenciesAdapter {
        findNavController().navigate(
            CurrenciesFragmentDirections.actionCurrenciesFragmentToDynamicsFragment(
                it.curID,
                it.curAbbreviation
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initCurrencies()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CurrenciesFragmentBinding.inflate(inflater, container, false)
        bindUI()

        lifecycleScope.launchWhenResumed {
            viewModel.apply {
                updateCurrencies()
                state.collect {
                    when {

                        it.isLoading -> {
                            setHasOptionsMenu(false)
                            binding.apply {
                                loadingGroup.visibility = View.VISIBLE
                                errorTextView.visibility = View.INVISIBLE
                            }
                        }

                        it.error.isNotBlank() -> {
                            setHasOptionsMenu(false)
                            if (it.currencies.isNullOrEmpty()) {
                                binding.apply {
                                    loadingGroup.visibility = View.INVISIBLE
                                    errorTextView.setTextColor(Color.RED)
                                    errorTextView.text = "Error - " + it.error
                                }
                            } else {
                                binding.apply {
                                    loadingGroup.visibility = View.INVISIBLE
                                    errorTextView.visibility = View.INVISIBLE
                                    today.text = it.currencies[1].date
                                }
                                customAdapter.submitList(it.currencies)
                                Toast.makeText(
                                    this@CurrenciesFragment.context,
                                    "An error has occurred - You are watching old currencies!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        else -> {
                            binding.apply {
                                loadingGroup.visibility = View.INVISIBLE
                                errorTextView.visibility = View.INVISIBLE
                            }
                            viewModel.saveCurrencies()
                            customAdapter.submitList(it.currencies)
                            dateBind()
                            setHasOptionsMenu(true)
                        }
                    }
                }
            }
        }
        return binding.root
    }

    private fun bindUI() {
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = customAdapter
            }
            refreshRecycler.setOnRefreshListener {
                viewModel.updateCurrencies()
                binding.refreshRecycler.isRefreshing = false
            }
        }
    }

    private fun dateBind() {
        binding.apply {
            today.text = dateFormatForUI.format(viewModel.todaysDate)
            tomorrow.text = dateFormatForUI.format(viewModel.tomorrowDate)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                val nav = CurrenciesFragmentDirections.actionCurrenciesFragmentToScrollingFragment()
                findNavController().navigate(nav)
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
