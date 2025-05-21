package com.common.network

import retrofit2.Retrofit

open class BaseApiClientV2 {

    fun getClient(baseUrl: String, authType: AuthType): Retrofit {
        return ApiFactoryV2(baseUrl, authType).getRetrofitClient()
    }

//    fun getDefaultBasicAuthClient(): Retrofit {
//        NetworkSDK.getNetworkConfig().getConfigParams()?.let {
//            if (it.containsKey(KEY_BASE_URL)) {
//                it[KEY_BASE_URL]?.let { baseUrl ->
//                    NetworkSDK.getNetworkConfig().getBasicAuthInfo()?.let { basicAuthInfo ->
//                        return ApiFactoryV2(baseUrl, AuthType.BasicAuth(basicAuthInfo)).getRetrofitClient()
//                    }
//                }
//            }
//        }
//       throw java.lang.Exception("getDefaultBasicAuthClient not ready")
//    }


//    fun getDefaultJwtTokenClient(baseUrl: String): Retrofit {
//        return NetworkSDK.getNetworkConfig().getJwtInfo()
//            ?.let { jwtInfo -> ApiFactoryV2(baseUrl, AuthType.JwtToken(jwtInfo)).getRetrofitClient() }
//            ?: throw java.lang.Exception("getDefaultJwtTokenClient not ready")
//    }
}