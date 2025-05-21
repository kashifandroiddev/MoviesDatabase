package com.common.utils

import com.common.network.ApiResult
import com.common.network.NetworkParseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception


//fun <T> toResultFlow(call: suspend () -> Response<T?>?): Flow<ApiResult<T?>?> {
//    return flow<ApiResult<T>?> {
//        emit(ApiResult.Loading(true))
//
//        try {
//            val result = call()
//            if (result?.isSuccessful == true) {
//                result?.body()?.let {
//                    emit(ApiResult.Loading(false))
//                    emit(ApiResult.Success(result.body()))
//                }
//            } else {
//                result?.errorBody()?.let {
//                    emit(ApiResult.Loading(false))
//                    emit(ApiResult.Error(NetworkParseError.parseApiError(result)))
//                    it.close()
//                }
//            }
//        } catch (e: Exception) {
//            emit(ApiResult.Loading(false))
//            emit(ApiResult.Error(NetworkParseError.parseApiErrorGeneralException(e)))
//        }
//    }.flowOn(Dispatchers.IO)
//}

fun <T> toResultFlow(call: suspend () -> retrofit2.Response<T?>?): Flow<ApiResult<T?>> = flow {
    // Emit loading true at the start.
    emit(ApiResult.Loading)
    try {
        val response = call()
        if (response?.isSuccessful == true) {
            // Emit success state if the response is successful.
            emit(ApiResult.Success(response.body()))
        } else {
            // Parse the error and emit error state.
            response?.errorBody()?.use {
                emit(
                    ApiResult.Error(
                        NetworkParseError.parseApiError(
                            response
                        )
                    )
                )
            }
                ?: emit(ApiResult.Error(NetworkParseError.errorReturnCustom()))
        }
    } catch (e: Exception) {
        // Emit error state for any exception encountered.
        emit(
            ApiResult.Error(
                NetworkParseError.parseApiErrorGeneralException(
                    e
                )
            )
        )
    }
//    finally {
//        emit(ApiResult.Loading(false))
//    }
}.flowOn(Dispatchers.IO)
