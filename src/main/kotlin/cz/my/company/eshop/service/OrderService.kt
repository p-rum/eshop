package cz.my.company.eshop.service

import cz.my.company.eshop.enumeration.OrderState
import cz.my.company.eshop.model.Order
import cz.my.company.eshop.model.request.OrderUpdateRequest
import org.springframework.stereotype.Service

@Service
interface OrderService {

    fun getOrders(): List<Order>
    fun getOrder(orderId: String): Order
    fun createOrder(orderRequest: OrderUpdateRequest, clientId: String): Order
    fun getOrdersByCustomer(customerId: String): List<Order>
    fun getOrderState(orderId: String): OrderState
    fun updateOrder(orderId: String, orderUpdate: OrderUpdateRequest): Order

}
