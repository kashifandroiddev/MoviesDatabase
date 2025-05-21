package com.common.utils

import android.util.Log
import com.google.gson.Gson
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class SafeGsonConverterFactory private constructor(
    private val gson: com.google.gson.Gson
) : retrofit2.Converter.Factory() {

    companion object {
        fun create(gson: com.google.gson.Gson = com.google.gson.Gson()) =
            SafeGsonConverterFactory(gson)
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: retrofit2.Retrofit
    ): retrofit2.Converter<okhttp3.ResponseBody, *> {
        val delegate = retrofit2.converter.gson.GsonConverterFactory.create(gson)
            .responseBodyConverter(type, annotations, retrofit)
        return retrofit2.Converter { body ->
            try {
                delegate?.convert(body)
            } catch (e: com.google.gson.JsonParseException) {
                Log.e("Retrofit", "GSON parse error: ${e.message}", e)
                throw e
            }
        }
    }
}