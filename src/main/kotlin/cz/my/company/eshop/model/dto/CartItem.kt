package cz.my.company.eshop.model.dto

data class CartItemDto(
    val sku: String,
    val name: String,
    var price: Float,
    var totalPrice: Float,
    var amount: Int = 1
) {
  fun inc(amount: Int?):CartItemDto {
    this.amount = this.amount + (amount ?: 1)
    this.totalPrice = this.amount * this.price
    return this
  }

  fun dec(amount: Int?):CartItemDto {
    this.amount = this.amount - (amount ?: 1)
    return this
  }
}