package cz.my.company.eshop.service.impl

import cz.my.company.eshop.mapper.StockItemMapper
import cz.my.company.eshop.model.StockItem
import cz.my.company.eshop.model.request.CreateStockItemRequest
import cz.my.company.eshop.model.request.UpdateStockItemRequest
import cz.my.company.eshop.repository.StockItemRepository
import cz.my.company.eshop.service.StockItemService
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class StockItemServiceImpl(private val repository: StockItemRepository, private val mapper: StockItemMapper) : StockItemService {
    override fun getStockItems(): List<StockItem> {
        return repository.findAll()
    }

    override fun getStockItem(stockItemId: String): StockItem {
        return repository.findById(stockItemId).orElseThrow()
    }

    override fun createStockItem(stockItem: CreateStockItemRequest): StockItem {
        return repository.save(mapper.map(stockItem))
    }

    override fun updateStockItem(stockItemId: String, stockItemUpdate: UpdateStockItemRequest): StockItem {
        val update = mapper.map(stockItemUpdate,stockItemId)
        update.updatedAt = Instant.now()
        val stockItem = mapper.update(repository.findById(stockItemId).get(), update)
        return repository.save(stockItem)
    }
}