package cz.my.company.eshop.model.dto

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class AddressDto(
        val street: String,
        val city: String,
        val streetNumber: String,
        val zip: Int,
        val country: String)
