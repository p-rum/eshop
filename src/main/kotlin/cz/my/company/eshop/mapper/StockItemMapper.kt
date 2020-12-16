package cz.my.company.eshop.mapper

import cz.my.company.eshop.model.StockItem
import cz.my.company.eshop.model.request.CreateStockItemRequest
import cz.my.company.eshop.model.request.UpdateStockItemRequest
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget


@Mapper(componentModel = "spring")
abstract class StockItemMapper {

    abstract fun map(createStockItemRequest: CreateStockItemRequest): StockItem

    abstract fun map(updateStockItemRequest: UpdateStockItemRequest, stockItemId: String): StockItem

    abstract fun update(@MappingTarget stockItem: StockItem, stockItemUpdate: StockItem): StockItem
}