package io.github.ha4219.ttd.api


import io.github.ha4219.ttd.api.request.SignupRequest
import io.github.ha4219.ttd.api.response.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface DongApi{
    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    companion object {
        val instance = ApiGenerator()
            .generator(DongApi::class.java)
    }

    @POST("/api/v1/users")
    suspend fun signup(@Body signupRequest: SignupRequest)
        :ApiResponse<Void>
}