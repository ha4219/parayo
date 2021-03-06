package io.github.ha4219.ttd.api

import io.github.ha4219.ttd.api.response.ApiResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface DongRefreshApi {

    @POST("/api/v1/refresh_token")
    suspend fun refreshToken(
        @Query("grant_type") grantType: String = "refresh_token"
    ): ApiResponse<String>

    companion object {
        val instance = ApiGenerator()
            .generateRefreshClient(DongRefreshApi::class.java)
    }
}