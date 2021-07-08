package com.example.dong.domain.product

import com.example.dong.domain.jpa.BaseEntity
import java.util.*
import javax.persistence.*


@Entity(name = "product_image")
class ProductImage(
    var path: String,
    var productId: Long? = null
): BaseEntity() {
}