package cz.my.company.eshop

import cz.my.company.eshop.enumeration.ShoppingCartProcessStep
import cz.my.company.eshop.enumeration.ShoppingCartProcessStep.NEW
import cz.my.company.eshop.model.dto.CartItemDto


class ShoppingCartService {
  val items: MutableMap<String, CartItemDto> = HashMap()
  var currentStep: ShoppingCartProcessStep = NEW

  fun add(itemToAdd: CartItemDto) {
    items.compute(itemToAdd.sku) { _, u -> u?.inc(itemToAdd.amount) ?: itemToAdd }
  }

  fun remove(itemToRemove: CartItemDto) {
    items.remove(itemToRemove.sku)
  }

  fun decreaseAmount(itemToDec: CartItemDto, amount: Int) {
    items.computeIfPresent(itemToDec.sku) { _, u -> u.dec(amount) }
  }


}