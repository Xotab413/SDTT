package com.example.currencies.domain.use_case

import com.example.currencies.common.Resource
import com.example.currencies.data.remote.dto.toDataPoint
import com.example.currencies.domain.repository.CurrencyRepository
import com.jjoe64.graphview.series.DataPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetDynamicsUseCase(
    private val repo: CurrencyRepository
) {
    operator fun invoke(id: String, start: String, end: String): Flow<Resource<List<DataPoint>>> =
        flow {
            try {
                emit(Resource.Loading<List<DataPoint>>())
                val dynamics = repo.getDynamics(id, start, end).map {
                    it.toDataPoint()
                }
                emit(Resource.Success<List<DataPoint>>(dynamics))
            } catch (e: HttpException) {
                emit(
                    Resource.Error<List<DataPoint>>(
                        e.localizedMessage ?: "An unexpected error occured"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error<List<DataPoint>>("Couldn't reach server. Check your internet connection."))
            }
        }
}
