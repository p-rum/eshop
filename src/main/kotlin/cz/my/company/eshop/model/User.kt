package cz.my.company.eshop.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id
    val id: ObjectId?,
    var surname: String,
    var firstname: String,
    var active: Boolean = true,
    @Indexed(unique = true)
    var email: String
)
