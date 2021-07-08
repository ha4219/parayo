package com.example.dong.domain.product.registration

import com.example.dong.common.DongException
import com.example.dong.domain.auth.UserContextHolder
import com.example.dong.domain.product.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductRegistrationService @Autowired constructor(
    private val productImageRepository: ProductImageRepository,
    private val productRepository: ProductRepository,
    private val userContextHolder: UserContextHolder
){
    fun register(request: ProductRegistrationRequest) =
        userContextHolder.id?.let{userId->
            val images by lazy {findAndValidateImages(request.imageIds)}
            request.validateRequest()
            request.toProduct(images, userId)
                .run(::save)
        } ?: throw DongException("상품 등록에 필요한 사용자 정보가 존재하지 않습니다.")

    private fun findAndValidateImages(imageIds: List<Long?>) =
        productImageRepository.findByIdIn(imageIds.filterNotNull())
            .also{images->
                images.forEach{image->
                    if(image.productId != null)
                        throw DongException("이미 등록된 상품입니다.")
                }
            }

    private fun save(product: Product) = productRepository.save(product)
}

private fun ProductRegistrationRequest.validateRequest() = when{
    name.length !in 1..40 || imageIds.size !in 1..4 || imageIds.filterNotNull().isEmpty() ||
            description.length !in 1..500 || price<=0 ->
        throw DongException("올바르지 않은 상품 정보입니다.")
    else -> {}

}

private fun ProductRegistrationRequest.toProduct(
    images: MutableList<ProductImage>,
    userId: Long
) = Product(
    name,
    description,
    price,
    categoryId,
    ProductStatus.SELLABLE,
    images,
    userId
)