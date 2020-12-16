package cz.my.company.eshop.model

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class Address(
        val street: String,
        val city: String,
        val streetNumber: String,
        val zip: Int,
        val country: String)
