package cz.my.company.eshop.repository

import cz.my.company.eshop.model.Category
import cz.my.company.eshop.model.StockItem
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StockItemRepository : MongoRepository<StockItem, String> {
    override fun findById(stockItemId: String): Optional<StockItem>
    fun getStockItemsByNameOrderByCategory(name: String): List<StockItem>
    fun getStockItemsByCategoryOrderByUpdatedAt(category: String): List<StockItem>

}