package com.common.network


// use this class after implementation code
sealed class ApiResult<out T> {
    data class Success<out T>(val data: T?) : ApiResult<T>()
    data class Error(val exception: ErrorResponse) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}