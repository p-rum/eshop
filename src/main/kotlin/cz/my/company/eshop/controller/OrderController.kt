package cz.my.company.eshop.controller

import cz.my.company.eshop.exception.impl.BadRequestException
import cz.my.company.eshop.model.Order
import cz.my.company.eshop.model.request.OrderUpdateRequest
import cz.my.company.eshop.service.OrderService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrders(): List<Order> {
        return orderService.getOrders()
    }

    @GetMapping("/{orderId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrder(@PathVariable orderId: String): Order {
        return orderService.getOrder(orderId)
    }

    @PostMapping("/orders", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createOrder(@CookieValue clientId: String, @RequestBody order: OrderUpdateRequest): Order {

        return orderService.createOrder(order, clientId)
    }

    @PatchMapping("/{orderId}", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateOrder(@PathVariable orderId: String, @RequestBody orderUpdate: OrderUpdateRequest): Order {
        return orderService.updateOrder(orderId, orderUpdate)
    }

}