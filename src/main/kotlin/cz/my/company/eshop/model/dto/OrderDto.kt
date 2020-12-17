package cz.my.company.eshop.model.dto

import com.github.pozo.KotlinBuilder
import cz.my.company.eshop.enumeration.DeliveryMethod
import cz.my.company.eshop.enumeration.OrderState
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
@KotlinBuilder
data class OrderDto(
    @Id
    var id: String?,
    @Indexed
    var customerId: String?,
    val deliveryAddress: AddressDto?,
    val items: List<OrderItem>,
    val deliveryMethod: DeliveryMethod?,
    var orderState: OrderState?,
    var totalPrice: Int?,
    val bill: Int?,
    val discount: Int?,
    var paymentState: PaymentState?,
    var createdAt: Instant?,
    var updatedAt: Instant?,
    var finishedAt: Instant?) {

  data class OrderItem(
      val itemId: String,
      val count: Int,
      val size: String?,
      val color: String?,
      val customAttributes: List<String>?,
      val price: Int)

  enum class PaymentState {
    PAYED,
    NOT_PAYED
  }
}
