package io.github.ha4219.ttd.api.response


data class SigninResponse(
    val token: String,
    val userName: String,
    val userId: Long
)