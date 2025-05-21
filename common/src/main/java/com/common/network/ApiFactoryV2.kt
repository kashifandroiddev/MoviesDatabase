package com.common.network

import com.google.gson.GsonBuilder
import com.common.utils.SafeGsonConverterFactory
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ApiFactoryV2(private val baseUrl: String, private val authType: AuthType?) {
    private val authInterceptor = Interceptor { chain ->
        val tag = NetworkUtils.getRandomNetworkId()
        var request = chain.request()
        when (authType) {
            is AuthType.JwtToken -> {
                request = request.newBuilder()
                    .tag(tag)
                    .addHeader(AUTHORIZATION, "${BEARER_AUTH} ${AuthTokenHelper().getJwtToken(authType.jwtInfo)}")
                    .build()
            }
            is AuthType.BasicAuth -> {
                request = request.newBuilder()
                    .addHeader(AUTHORIZATION, "${BASIC_AUTH} ${AuthTokenHelper().getBasicAuth(authType.authInfo)}")
                    .tag(tag)
                    .build()
            }
            is AuthType.Custom -> {
                val builder = request.newBuilder()
                authType.headers.forEach {
                    builder.addHeader(it.key, it.value)
                }
                builder.tag(tag)
                request = builder.build()
            }
            else -> {}
        }
//        NetworkProfilerManager.addNetworkCall(request, tag)

        val response = chain.proceed(request)
        val rawJson = response.body?.string() ?: ""
        val isDecrypted = rawJson.trim().startsWith("{") && rawJson.trim().endsWith("}")
        val responseBuilder = response.newBuilder()
        if (isDecrypted) {
            if (NetworkUtils.isJSONValid(rawJson)) {
//                NetworkProfilerManager.setResponseToCorrespondingRequest(response, rawJson)
//                NetworkLogging.logDebug("ResponseJSON", rawJson)
                responseBuilder.body(rawJson.toResponseBody(response.body?.contentType()))
            }else{
//                NetworkUtils.getErrorResponse(response)
                responseBuilder.body(rawJson.toResponseBody(response.body?.contentType()))
            }
        } else {

//            val decryptedResponse = ApisEncryptionUtils.getInstance()?.decryptString(rawJson)
//            if (decryptedResponse != null) {
//                NetworkLogging.logDebug("Response decryptedResponse$decryptedResponse")
//                if (NetworkResponseUtils.isJSONValid(decryptedResponse)) {
//                    NetworkProfilerManager.setResponseToCorrespondingRequest(response, decryptedResponse)
//                    response.newBuilder().body(decryptedResponse.toResponseBody(response.body?.contentType())).build()
//                } else {
//                    NetworkResponseUtils.getErrorResponse(response)
//                }
//            } else {
//                if (NetworkResponseUtils.isJSONValid(rawJson)) {
//                    NetworkProfilerManager.setResponseToCorrespondingRequest(response, rawJson)
//                    response.newBuilder().body(rawJson.toResponseBody(response.body?.contentType())).build()
//                } else {
//                    NetworkResponseUtils.getErrorResponse(response)
//                }
//            }
        }
        responseBuilder.build()
    }

    private val apiClient = getOkHttpBuilder().build()


    var gson = GsonBuilder()
        .setLenient()
        .serializeNulls()
        .serializeSpecialFloatingPointValues()
        .create()



    fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .client(apiClient)
            .baseUrl(baseUrl)
//          this line is comment not required in latest version of retrofit because in latest version it already have support od suspend functions in retrofit
//          .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(SafeGsonConverterFactory.Companion.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun getOkHttpBuilder(): OkHttpClient.Builder {
        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
        builder.addInterceptor(authInterceptor)
//        if (NetworkSDK.getNetworkConfig().isSSlPinningEnabled()) {
//            builder.certificatePinner(SSLManager.getCertificatePinner(baseUrl))
//        }
        builder.addInterceptor(HttpLoggingInterceptor().apply {
//            if (NetworkSDK.isNetworkLoggingEnabled) {
//                level = HttpLoggingInterceptor.Level.BODY
//            } else {
//                level = HttpLoggingInterceptor.Level.NONE
//            }
            level = HttpLoggingInterceptor.Level.BODY
        })
        builder.connectTimeout(1, TimeUnit.MINUTES)
        builder.writeTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(1, TimeUnit.MINUTES)
        return builder
    }
}