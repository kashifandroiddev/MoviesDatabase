package com.common.network


sealed class AuthType {
    class Custom(val headers: HashMap<String, String>) : AuthType()
}
