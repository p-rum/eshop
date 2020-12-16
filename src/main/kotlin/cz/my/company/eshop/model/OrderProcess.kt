package cz.my.company.eshop.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*
import kotlin.collections.LinkedHashMap

@Document
data class OrderProcess(
        @Id
        val id: UUID,
        var history: LinkedHashMap<OrderUpdate, Instant>,
        val finished: Boolean)