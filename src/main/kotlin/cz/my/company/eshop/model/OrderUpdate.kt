package cz.my.company.eshop.model

import cz.my.company.eshop.enumeration.DeliveryMethod
import cz.my.company.eshop.enumeration.OrderState
import java.time.Instant

data class OrderUpdate(
                       val deliveryAddress: Address?,
                       val items: List<Order.OrderItem>?,
                       val deliveryMethod: DeliveryMethod?,
                       var orderState: OrderState?,
                       val totalPrice: Int?,
                       val bill: Int?,
                       val discount: Int?,
                       var paymentState: Order.PaymentState?,
                       val createdAt: Instant?,
                       var updatedAt: Instant?,
                       var finishedAt: Instant?)