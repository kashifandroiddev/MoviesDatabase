package com.common.network

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
abstract class BaseResponse(
    @field:SerializedName("http_response")
    var httpResponse: Int? = 0,

    @field:SerializedName("code")
    val code: Int = 0,

    @SerializedName("cmd")
    var cmd: String? = null,

    @SerializedName("message")
    var message: String? = null,

    @field:SerializedName("success")
val success: Boolean? = null,

)