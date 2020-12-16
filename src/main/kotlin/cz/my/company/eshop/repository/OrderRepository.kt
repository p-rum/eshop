package cz.my.company.eshop.repository

import cz.my.company.eshop.model.Order
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository :MongoRepository<Order,String>{

    fun getOrdersByCustomerId(customerId: String):List<Order>
}