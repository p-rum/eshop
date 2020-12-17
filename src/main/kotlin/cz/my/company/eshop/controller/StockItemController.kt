package cz.my.company.eshop.controller

import cz.my.company.eshop.model.StockItem
import cz.my.company.eshop.model.request.CreateStockItemRequest
import cz.my.company.eshop.model.request.UpdateStockItemRequest
import cz.my.company.eshop.service.StockItemService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/stockItems")
class StockItemController(private val stockItemService: StockItemService) {

    @GetMapping
    fun getStockItems(): List<StockItem> {
        return stockItemService.getStockItems()
    }

    @GetMapping("/{stockItemId}")
    fun getStockItem(@PathVariable stockItemId:String): StockItem {
        return stockItemService.getStockItem(stockItemId)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces=[MediaType.APPLICATION_JSON_VALUE])
    fun createStockItem(@RequestBody stockItem: CreateStockItemRequest): StockItem {
        return stockItemService.createStockItem(stockItem)
    }

    @PatchMapping("/{stockItemId}",consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateStockItem(@PathVariable stockItemId:String, @RequestBody stockItemUpdate: UpdateStockItemRequest): StockItem {
        return stockItemService.updateStockItem(stockItemId,stockItemUpdate)
    }

}