import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vktask.CurrencyConverterViewModel
import com.example.vktask.CurrencyRepository

class CurrencyConverterViewModelFactory(private val repository: CurrencyRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyConverterViewModel::class.java)) {
            return CurrencyConverterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


