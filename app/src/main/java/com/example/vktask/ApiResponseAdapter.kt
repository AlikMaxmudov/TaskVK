    package com.example.vktask

    import com.google.gson.Gson
    import com.google.gson.GsonBuilder
    import com.google.gson.reflect.TypeToken

    class ApiResponseAdapter {
        val gson: Gson = GsonBuilder().setLenient().create()

        inline fun <reified T> fromJson(json: String): T {
            try {
                val type = object : TypeToken<T>() {}.type
                return gson.fromJson(json, type)
            } catch (e: Exception) {
                e.printStackTrace()
                throw RuntimeException("Failed to parse JSON", e)
            }
        }
    }


