package io.github.ha4219.ttd.api.response

import io.github.ha4219.ttd.common.Prefs


data class SigninResponse(
    val token: String,
    val refreshToken: String,
    val userName: String,
    val userId: Long
)