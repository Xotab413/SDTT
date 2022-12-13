package com.example.currencies.presentation.dynamics_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.currencies.common.Constants
import com.example.currencies.databinding.DynamicsFragmentBinding
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DynamicsFragment : Fragment() {

    private var _binding: DynamicsFragmentBinding? = null
    private val binding get() = _binding!!
    private val navigationArgs: DynamicsFragmentArgs by navArgs()
    private val viewModel by viewModel<DynamicsViewModel>()

    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.updateState(navigationArgs.id.toString())
        _binding = DynamicsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when {
                    state.isLoading -> {
                        binding.apply {
                            loadingGroup.visibility = View.VISIBLE
                            graph.visibility = View.INVISIBLE
                        }
                    }

                    state.error.isNotBlank() -> {
                        Toast.makeText(
                            this@DynamicsFragment.context,
                            "An error has occurred - You are watching old currencies!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        bindUI()
                        val series: LineGraphSeries<DataPoint> = LineGraphSeries(arrayOf())
                        var d = -30.0

                        state.points.forEach {
                            series.appendData(DataPoint(d++, it.y), true, 30)
                        }
                        binding.apply {
                            loadingGroup.visibility = View.INVISIBLE
                            graph.visibility = View.VISIBLE
                            graph.addSeries(series)
                            graph.viewport.isScalable = true
                            series.setOnDataPointTapListener { _, dataPoint ->
                                calendar.add(Calendar.DAY_OF_YEAR, dataPoint.x.toInt())
                                Toast.makeText(
                                    activity,
                                    "Курс ${Constants.dateFormatForUI.format(calendar.time)} - " + dataPoint.y,
                                    Toast.LENGTH_SHORT
                                ).show()
                                calendar.add(Calendar.DAY_OF_YEAR, (dataPoint.x * (-1)).toInt())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun bindUI() {
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM")
        binding.apply {
            dynamicCurrency.text = navigationArgs.name
            calendar.add(Calendar.DAY_OF_YEAR, -5)
            minus5Days.text = dateFormat.format(calendar.time)
            calendar.add(Calendar.DAY_OF_YEAR, -10)
            minus15Days.text = dateFormat.format(calendar.time)
            calendar.add(Calendar.DAY_OF_YEAR, -10)
            minus25Days.text = dateFormat.format(calendar.time)
            calendar.add(Calendar.DAY_OF_YEAR, 25)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
