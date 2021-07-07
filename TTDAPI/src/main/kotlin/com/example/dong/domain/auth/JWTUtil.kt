package com.example.dong.domain.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object JWTUtil {

    private const val ISSUER = "Dong"
    private const val SUBJECT = "AUTH"
    private const val EXPIRE_TIME = 60L*60*2*1000

    private val SECRET = "your-secret"
    private val algorithm: Algorithm = Algorithm.HMAC256(SECRET)

    fun createToken(email:String) = JWT.create()
        .withIssuer(ISSUER)
        .withSubject(SUBJECT)
        .withIssuedAt(Date())
        .withExpiresAt(Date(Date().time+ EXPIRE_TIME))
        .withClaim(JWTClaims.EMAIL, email)
        .sign(algorithm)

    object JWTClaims {
        const val EMAIL = "email"
    }
}