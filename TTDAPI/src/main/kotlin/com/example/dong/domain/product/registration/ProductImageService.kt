package com.example.dong.domain.product.registration

import com.example.dong.common.DongException
import com.example.dong.domain.auth.UserContextHolder
import com.example.dong.domain.product.*
import net.coobird.thumbnailator.Thumbnails
import net.coobird.thumbnailator.geometry.Positions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Service
class ProductImageService @Autowired constructor(
    private val productImageRepository: ProductImageRepository
){
    @Value("\${dong.file-upload.default-dir}")
    var uploadPath: String? = ""

    fun uploadImage(image: MultipartFile)
        :ProductImageUploadResponse{
        val filePath = saveImageFile(image)
        val productImage = saveImageData(filePath)

        return productImage.id?.let{
            ProductImageUploadResponse(it, filePath)
        } ?: throw DongException("이미지 저장 실패. 다시 시도해주세요.")
    }

    private fun saveImageFile(image: MultipartFile): String{
        val extension = image.originalFilename
            ?.takeLastWhile { it!='.' }
            ?:throw DongException("다른 이미지로 다시 시도해주세요.")

        val uuid = UUID.randomUUID().toString()
        val date = SimpleDateFormat("yyyyMMdd")

        val filePath = "/images/$date/$uuid/$uuid.$extension"
        val targetFile = File("$uploadPath/$filePath")
        val thumbnail = targetFile.absolutePath
            .replace(uuid, "$uuid-thumb")
            .let(::File)

        targetFile.parentFile.mkdirs()
        image.transferTo(targetFile)

        Thumbnails.of(targetFile)
            .crop(Positions.CENTER)
            .size(300,300)
            .outputFormat("jpg")
            .outputQuality(0.8f)
            .toFile(thumbnail)

        return filePath
    }

    private fun saveImageData(filePath: String): ProductImage{
        val productImage = ProductImage(filePath)
        return productImageRepository.save(productImage)
    }


}

