package com.example.currencies.domain.use_case

import com.example.currencies.common.Resource
import com.example.currencies.data.remote.dto.toCurrency
import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCurrenciesFromApiUseCase(
    private val repo: CurrencyRepository
) {
    operator fun invoke(date: String): Flow<Resource<List<Currency>>> = flow {
        try {
            emit(Resource.Loading<List<Currency>>())
            val currencies = repo.getCurrenciesFromApi(date).map {
                it.toCurrency()
            }
            emit(Resource.Success<List<Currency>>(currencies))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<Currency>>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<Currency>>("Couldn't reach server. Check your internet connection."))
        }
    }
}
