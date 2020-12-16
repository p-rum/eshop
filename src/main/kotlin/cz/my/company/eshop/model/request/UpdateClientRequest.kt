package cz.my.company.eshop.model.request

import com.fasterxml.jackson.annotation.JsonInclude
import cz.my.company.eshop.model.Address
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UpdateClientRequest(
        var email: String?,
        var phoneNumber: Number?,
        var firstName: String?,
        var surname: String?,
        var degree: String?,
        var deliveryAddress: Address?,
        var address: Address?)