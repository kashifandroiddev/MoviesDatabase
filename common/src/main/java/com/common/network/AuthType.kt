package com.common.network


sealed class AuthType {
    class JwtToken(val jwtInfo: JwtInfo) : AuthType()
    class BasicAuth(val authInfo: BasicAuthInfo) : AuthType()
    class Custom(val headers: HashMap<String, String>) : AuthType()
}
