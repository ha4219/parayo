package io.github.ha4219.ttd.product.registration

import io.github.ha4219.ttd.api.DongApi
import io.github.ha4219.ttd.api.request.ProductRegistrationRequest
import io.github.ha4219.ttd.api.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import retrofit2.Response
import java.lang.Exception

class ProductRegistrar: AnkoLogger {

    suspend fun register(request: ProductRegistrationRequest) = when{
        request.isNotValidName ->
            ApiResponse.error("상품명을 조건에 맞게 입력해주세요.")
        request.isNotValidDescription ->
            ApiResponse.error("")
        request.isNotValidPrice ->
            ApiResponse.error("")
        request.isNotValidCategoryId ->
            ApiResponse.error("")
        request.isNotValidImageIds ->
            ApiResponse.error("")
        else -> withContext(Dispatchers.IO){
            try{
                DongApi.instance.registerProduct(request)
            }catch (e:Exception){
                error("상품 등록 오류",e)
                ApiResponse.error<Response<Void>>(
                    "알 수 없는 오류가 발생했습니다."
                )
            }
        }
    }
}