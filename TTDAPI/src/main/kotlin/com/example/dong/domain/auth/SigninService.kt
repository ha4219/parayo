package com.example.dong.domain.auth

import com.example.dong.common.DongException
import com.example.dong.domain.user.UserRepository
import com.example.dong.domain.user.User
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class SigninService @Autowired constructor(
    private val userRepository: UserRepository
){

    fun signin(signinRequest: SigninRequest): SigninResponse{
        val user = userRepository
            .findByEmail(signinRequest.email.toLowerCase())
            ?: throw DongException("로그인 정보를 확인해주세요.")

        if (isNotValidPassword(signinRequest.password, user.password)) {
            throw DongException("로그인 정보를 확인해주세요.")
        }

        return responseWithTokens(user)
    }

    private fun isNotValidPassword(
        plain: String,
        hashed: String
    ) = BCrypt.checkpw(plain, hashed).not()

    private fun responseWithTokens(user: User) = user.id?.let{
        userId -> SigninResponse(
        JWTUtil.createToken(user.email),
        JWTUtil.createRefreshToken(user.email),
        user.name,
        userId
    )
    } ?: throw IllegalStateException("user.id 없음.")
}