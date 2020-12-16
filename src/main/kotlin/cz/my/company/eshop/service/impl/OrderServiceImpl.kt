package cz.my.company.eshop.service.impl

import cz.my.company.eshop.enumeration.OrderState
import cz.my.company.eshop.mapper.OrderMapper
import cz.my.company.eshop.model.Order
import cz.my.company.eshop.model.request.OrderUpdateRequest
import cz.my.company.eshop.repository.OrderRepository
import cz.my.company.eshop.service.OrderService
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*
import kotlin.math.absoluteValue

@Service
class OrderServiceImpl(private val repository: OrderRepository,
                       private val mapper: OrderMapper) : OrderService {
    override fun getOrders(): List<Order> {
        return repository.findAll()
    }

    override fun getOrder(orderId: String): Order {
        return repository.findById(orderId).orElseThrow()
    }

    override fun createOrder(orderRequest: OrderUpdateRequest, clientId: String): Order {
        val order = mapper.map(orderRequest)
        order.customerId=clientId
        order.updatedAt = Instant.now()
        order.createdAt = Instant.now()
        order.id = UUID.randomUUID().toString()
        order.orderState = OrderState.NEW
        order.paymentState = Order.PaymentState.NOT_PAYED
        order.totalPrice = order.items.sumBy { it.count * it.price }
        return repository.save(order)
    }

    override fun getOrdersByCustomer(customerId: String): List<Order> {
        return repository.getOrdersByCustomerId(customerId)
    }

    override fun getOrderState(orderId: String): OrderState {
        TODO("Not yet implemented")
    }

    override fun updateOrder(orderId: String, orderUpdate: OrderUpdateRequest): Order {
        val update = mapper.map(orderUpdate)
        update.updatedAt = Instant.now()
        update.createdAt = Instant.now()
        val order = mapper.update(repository.findById(orderId).get(), update)
        return repository.save(order)
    }
}