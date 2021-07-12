package io.github.ha4219.ttd.api


import io.github.ha4219.ttd.api.request.ProductRegistrationRequest
import io.github.ha4219.ttd.api.request.SigninRequest
import io.github.ha4219.ttd.api.request.SignupRequest
import io.github.ha4219.ttd.api.response.ApiResponse
import io.github.ha4219.ttd.api.response.ProductImageUploadResponse
import io.github.ha4219.ttd.api.response.SigninResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface DongApi{
    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    companion object {
        val instance = ApiGenerator()
            .generate(DongApi::class.java)
    }

    @POST("/api/v1/users")
    suspend fun signup(@Body signupRequest: SignupRequest)
        :ApiResponse<Void>

    @POST("/api/v1/signin")
    suspend fun signin(@Body signinRequest: SigninRequest)
        :ApiResponse<SigninResponse>


    @Multipart
    @POST("/api/v1/product_images")
    suspend fun uploadProductImages(
        @Part images: MultipartBody.Part
    ):ApiResponse<ProductImageUploadResponse>

    @POST("/api/v1/products")
    suspend fun registerProduct(
        @Part request: ProductRegistrationRequest
    ):ApiResponse<Response<Void>>
}