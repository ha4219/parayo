package io.github.ha4219.ttd.signup

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.github.ha4219.ttd.api.DongApi
import io.github.ha4219.ttd.api.request.SignupRequest
import io.github.ha4219.ttd.api.response.ApiResponse
import io.github.ha4219.ttd.api.response.SigninResponse
import io.github.ha4219.ttd.common.Prefs
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import java.lang.Exception

class SignupViewModel(app: Application):BaseViewModel(app) {

    val email = MutableLiveData("")
    val name = MutableLiveData("")
    val password = MutableLiveData("")

    suspend fun signup(){
        val request = SignupRequest(email.value, password.value, name.value)
        if(isNotValidSignup(request))
            return
        try {
            val response = requestSignup(request)
            onSignupResponse(response)
        }catch (e: Exception){
            error("signup error",e)
            toast("알 수 없는 오류가 발생했습니다.")
        }


    }

    private fun isNotValidSignup(signupReqest: SignupRequest) =
        when{
            signupReqest.isNotValidEmail()->{
                toast("이메일 형식이 정확하지 않습니다.")
                true
            }
            signupReqest.isNotValidName()->{
                toast("이름은 2자 이상 20자 이하로 입력해주세요.")
                true
            }
            signupReqest.isNotValidPassword()-> {
                toast("비밀번호는 8자 이상 20자 이하로 입력해주세요.")
                true
            }
            else -> false
        }

    private suspend fun requestSignup(request: SignupRequest) =
        withContext(IO) {
            DongApi.instance.signup(request)
        }

    private fun onSignupResponse(response:ApiResponse<Void>){
        if(response.success){
            toast("회원 가입이 완료되었습니다. 로그인 후 이용해주세요.")
            finishActivity()
        }else{
            toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }

    private fun onSigninResponse(response: ApiResponse<SigninResponse>) {
        if(response.success && response.data!= null){
            Prefs.token = response.data.token
            Prefs.refreshToken = response.data.refreshToken
            Prefs.userName = response.data.userName
            Prefs.userId = response.data.userId

            toast("로그인되었습니다.")
        }else{
            toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }
}