package com.common.network

data class JwtInfo (val sessionToken: String?, val company: String, val apiToken: String,val secretKey:String)
data class BasicAuthInfo (val username: String, val password: String)