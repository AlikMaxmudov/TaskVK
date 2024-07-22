1. Kotlin: Основной язык разработки приложения
2. Android Architecture ComponentsЕ:
  ViewModel, LiveData.
3. Retrofit.
4. RxJava
5. Gson
6. Data Binding
7. Архитектура:
   MVVM.

Инициализация Retrofit: В CurrencyConverterActivity создается экземпляр Retrofit для выполнения сетевых запросов.
Запрос к API: В CurrencyRepository вызывается метод getUsdExchangeRate(), который использует Retrofit для выполнения HTTP-запроса и получения курсов валют.
Обновление данных: В CurrencyConverterViewModel вызывается метод convertCurrency(), который выполняет асинхронный запрос к API и обновляет LiveData с конвертированными данными.
Отображение данных: В CurrencyConverterActivity наблюдатель LiveData обновляет TextView с конвертированными данными, когда данные изменяются.
