package cz.my.company.eshop.model.request

import com.fasterxml.jackson.annotation.JsonInclude
import cz.my.company.eshop.enumeration.DeliveryMethod
import cz.my.company.eshop.enumeration.OrderState
import cz.my.company.eshop.model.Address
import cz.my.company.eshop.model.Order
import java.time.Instant

@JsonInclude(JsonInclude.Include.NON_NULL)
data class OrderUpdateRequest(
        val deliveryAddress: Address?,
        val items: List<Order.OrderItem>?,
        val deliveryMethod: DeliveryMethod?,
        val orderState: OrderState?,
        val totalPrice: Int?,
        val bill: Int?,
        val discount: Int?,
        var paymentState: Order.PaymentState?,
        val createdAt: Instant?,
        var updatedAt: Instant?)