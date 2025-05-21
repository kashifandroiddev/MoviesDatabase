package com.common.network

import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.jvm.java
import kotlin.toString


object NetworkParseError {


    /**
     * Parse Error Response from Server Side Response
     */
    fun parseApiError(response: Response<*>): ErrorResponse {
//        if (response.code() == 403 || response.code() == 801) {
//           logout if user is logged in on another device(session expire)
//           NetworkSDK.networkEventListener?.logout()
//        }
        when(response.code()){
            403,801 ->{
//           logout if user is logged in on another device(session expire)
//           NetworkSDK.networkEventListener?.logout()
            }
            300->{
                val errorResponse = ErrorResponse()
                errorResponse.httpResponse = response.code()
                errorResponse.message = response.message().toString()
                return errorResponse
            }
        }
        val errorBody = response.errorBody()?.string()
        var errorResponse: ErrorResponse
        try {
            errorResponse = Gson().fromJson(JSONObject(errorBody.toString()).toString(), ErrorResponse::class.java)
        } catch (ex: Exception) {
            errorResponse = parseApiErrorIntCode(response.code())
            ex.printStackTrace()
        }
        return errorResponse
    }
    /**
     * Generate Local Error Response from http error code
     */
    private fun parseApiErrorIntCode(code: Int): ErrorResponse {
        val errorResponse = ErrorResponse()
        errorResponse.httpResponse = code
        when (code) {
            in 500..510 -> {
//                errorResponse.msg = ApplicationClass.instance.applicationContext.getString(R.string.network_error_message_502)
            }
            in 401..403 -> {
//                errorResponse.msg = ApplicationClass.instance.applicationContext.getString(R.string.network_error_message_403)
            }
            else -> {
//                errorResponse.msg = ApplicationClass.instance.applicationContext.getString(R.string.network_something_wrong)
            }
        }
        return errorResponse
    }

    fun parseApiErrorGeneralException(exception: Exception): ErrorResponse {
        val error = ErrorResponse()
        when (exception) {
            is SocketTimeoutException -> {
                error.message = "Connection Error"
            }
            is ConnectException -> {
                error.message = "No Internet Access!"
            }
            is UnknownHostException -> {
                error.message = "No Internet Access"
            }
            else->{
                error.message = exception.localizedMessage?.toString()
            }
        }
        return error
    }

    fun errorReturnCustom(): ErrorResponse{
        val errorResponse = ErrorResponse()
        errorResponse.httpResponse = 0
        errorResponse.message = "Api response?.isSuccessful not successfully".toString()
        return errorResponse
    }


}