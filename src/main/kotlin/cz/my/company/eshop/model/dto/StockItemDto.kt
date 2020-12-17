package cz.my.company.eshop.model.dto

import com.github.pozo.KotlinBuilder
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
@KotlinBuilder
data class StockItemDto(
    @Id
    var sku: String,
    var name: String?,
    var description: String?,
    var category: String?,
    var price: Long?,
    var imageDirPath: String?,
    var updatedAt: Instant?,
    var availableQuantity: Int?)
