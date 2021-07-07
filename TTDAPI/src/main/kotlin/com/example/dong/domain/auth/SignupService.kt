package com.example.dong.domain.auth

import com.example.dong.common.DongException
import com.example.dong.domain.user.User
import com.example.dong.domain.user.UserRepository
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class SignupService @Autowired constructor(
    private val userRepository:UserRepository
){
    fun signup(signupRequest: SignupRequest){
        validateRequest(signupRequest)
        checkDuplicates(signupRequest.email)
        registerUser(signupRequest)
    }

    private fun validateRequest(signupRequest: SignupRequest){
        validateEmail(signupRequest.email)
        validateName(signupRequest.name)
        validatePassword(signupRequest.password)
    }

    private fun validateEmail(email: String){
        val isNotValidEmail = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
            .toRegex(RegexOption.IGNORE_CASE)
            .matches(email)
            .not()
        if(isNotValidEmail){
            throw DongException("Emial format is not validated.")
        }
    }

    private fun validateName(name:String){
        if(name.trim().length !in 2..20)
            throw DongException("Name length must be over 2 and 20 down.")
    }

    private fun validatePassword(password:String){
        if(password.trim().length !in 2..20)
            throw DongException("Password's length must be over 8 and 20 down.")
    }

    private fun checkDuplicates(email: String) =
        userRepository.findByEmail(email)?.let{
            throw DongException("This email is already used")
        }

    private fun registerUser(signupRequest: SignupRequest) =
        with(signupRequest) {
            val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
            val user = User(email, hashedPassword,name)
            userRepository.save(user)
        }
}