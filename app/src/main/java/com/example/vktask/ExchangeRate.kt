package com.example.vktask

import com.google.gson.annotations.SerializedName

data class ExchangeRate(
    @SerializedName("USD") val usd: Double,
    @SerializedName("EUR") val eur: Double,
    @SerializedName("GBP") val gbp: Double
)
