package com.example.vktask

import io.reactivex.Single


class CurrencyRepository(private val apiService: CurrencyApiService) {
    private val apiResponseAdapter = ApiResponseAdapter()

    fun getUsdExchangeRate(): Single<Double> {
        return apiService.getLatestRates()
            .map { response ->
                response.usd
            }
    }

}
