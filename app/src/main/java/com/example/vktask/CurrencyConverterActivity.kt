package com.example.vktask

import CurrencyConverterViewModelFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CurrencyConverterActivity : AppCompatActivity() {

    private lateinit var viewModel: CurrencyConverterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


        val apiService = retrofit.create(CurrencyApiService::class.java)
        val repository = CurrencyRepository(apiService)
        viewModel = ViewModelProvider(this, CurrencyConverterViewModelFactory(repository)).get(CurrencyConverterViewModel::class.java)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val amountEditText = findViewById<EditText>(R.id.amountEditText)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        calculateButton.setOnClickListener {
            val amountString = amountEditText.text.toString()
            if (amountString.isBlank()) {
                Toast.makeText(this@CurrencyConverterActivity, "Please enter an amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountString.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(this@CurrencyConverterActivity, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.convertCurrency(amount)
            }
        }

        viewModel.convertedAmount.observe(this, Observer { convertedAmount ->
            if (convertedAmount != null) {
                resultTextView.text = convertedAmount.toString()
            } else {
                resultTextView.text = "что-то пошло не так."
            }
        })
    }
}
