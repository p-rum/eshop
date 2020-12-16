package cz.my.company.eshop.model.request

import com.fasterxml.jackson.annotation.JsonInclude
import cz.my.company.eshop.model.Address

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CreateClientRequest(
        val email: String,
        val phoneNumber: Number,
        val firstName: String,
        val surname: String,
        val degree: String?,
        val deliveryAddress: Address?,
        val address: Address)