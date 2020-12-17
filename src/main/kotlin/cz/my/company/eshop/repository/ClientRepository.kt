package cz.my.company.eshop.repository

import cz.my.company.eshop.model.Client
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClientRepository : MongoRepository<Client, UUID> {

    fun getClientByEmail(email: String): Client?
    fun getClientsByFirstNameAndSurname(firstName: String, surname: String): List<Client>?
    fun deleteClientByEmail(email: String)
}