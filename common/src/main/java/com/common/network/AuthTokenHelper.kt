package com.common.network

import android.util.Base64

class AuthTokenHelper {
    fun getJwtToken(jwtInfo: JwtInfo): String {
//        var builder: JwtBuilder? = null
//        builder = Jwts.builder().setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE)
//            .claim("company", jwtInfo.company)
//            .claim("session_token", jwtInfo.sessionToken)
//            .claim("api_token", jwtInfo.apiToken)
//            .signWith(
//                SignatureAlgorithm.HS256,
//                Base64.encodeToString(jwtInfo.secretKey.toByteArray(), Base64.DEFAULT)
//            )
//        val hashKey = builder!!.compact()
//        return hashKey
        return ""
    }

    fun getBasicAuth(basicAuthInfo: BasicAuthInfo): String {
        val credentials = String.format("%s:%s", basicAuthInfo.username, basicAuthInfo.password)
        return Base64.encodeToString(
            credentials.toByteArray(),
            Base64.URL_SAFE or Base64.NO_WRAP
        )
    }
}