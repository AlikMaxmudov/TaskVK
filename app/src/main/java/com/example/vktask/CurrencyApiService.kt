package com.example.vktask

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("latest")
    fun getLatestRates(): Single<ExchangeRate>
}

