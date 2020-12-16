package cz.my.company.eshop.mapper

import cz.my.company.eshop.model.Order
import cz.my.company.eshop.model.request.OrderUpdateRequest

import org.mapstruct.Mapper

import org.mapstruct.MappingTarget
import org.springframework.data.domain.jaxb.SpringDataJaxb


@Mapper(componentModel = "spring")
abstract class OrderMapper {

    abstract fun map(createOrderRequest: OrderUpdateRequest): Order

    abstract fun map(updateOrderRequest: OrderUpdateRequest, orderId: String): Order

    abstract fun update(@MappingTarget order: Order, orderUpdate: Order): Order
}