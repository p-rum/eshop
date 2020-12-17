package cz.my.company.eshop.model

import cz.my.company.eshop.enumeration.OrderState
import cz.my.company.eshop.model.dto.CartItemDto
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ShoppingCart (
    val id:ObjectId?,
    val items: MutableList<CartItemDto>,
    val client: Client,
    var state: OrderState?
)