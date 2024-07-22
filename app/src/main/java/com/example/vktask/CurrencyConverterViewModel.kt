package com.example.vktask

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.time.times

class CurrencyConverterViewModel(private val repository: CurrencyRepository) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _convertedAmount = MutableLiveData<Double>()
    val convertedAmount: LiveData<Double>
        get() = _convertedAmount

    fun convertCurrency(amount: Double) {
        disposables.add(
            repository.getUsdExchangeRate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ conversionRate ->
                    val converted = amount * conversionRate
                    Log.d(TAG, "Conversion rate: $conversionRate, Converted amount: $converted")
                    _convertedAmount.value = converted
                }, { error ->
                    error.printStackTrace()
                    _convertedAmount.value = null
                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
