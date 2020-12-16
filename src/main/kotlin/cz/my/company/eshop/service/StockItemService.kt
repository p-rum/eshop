package cz.my.company.eshop.service

import cz.my.company.eshop.model.StockItem
import cz.my.company.eshop.model.request.CreateStockItemRequest
import cz.my.company.eshop.model.request.UpdateStockItemRequest
import org.springframework.stereotype.Service

@Service
interface StockItemService {

    fun getStockItems(): List<StockItem>
    fun getStockItem(stockItemId: String): StockItem
    fun createStockItem(stockItem: CreateStockItemRequest): StockItem
    fun updateStockItem(stockItemId: String, stockItemUpdate: UpdateStockItemRequest): StockItem

}
