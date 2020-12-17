package cz.my.company.eshop.model.dto

import com.github.pozo.KotlinBuilder
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.validation.annotation.Validated
import java.time.Instant
import javax.annotation.processing.Generated
import javax.validation.constraints.Email

@Validated
@Document
@KotlinBuilder
data class ClientDto(
        @Id
        @Generated
        val id: String?,
        @Email
        @Indexed
        val email: String,
        val phoneNumber: Number,
        val firstName: String,
        val surname: String,
        val degree: String?,
        var registeredAt: Instant?,
        var updatedAt: Instant?,
        val deliveryAddress: AddressDto?,
        val address: AddressDto)
