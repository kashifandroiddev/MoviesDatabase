package com.common.network

import retrofit2.Retrofit

open class BaseApiClientV2 {
    fun getClient(baseUrl: String, authType: AuthType): Retrofit {
        return ApiFactoryV2(baseUrl, authType).getRetrofitClient()
    }
}