package cz.my.company.eshop.service.impl

import cz.my.company.eshop.exception.impl.NotFoundException
import cz.my.company.eshop.mapper.ClientMapper
import cz.my.company.eshop.model.Client
import cz.my.company.eshop.model.request.CreateClientRequest
import cz.my.company.eshop.model.request.UpdateClientRequest
import cz.my.company.eshop.repository.ClientRepository
import cz.my.company.eshop.service.ClientService
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class ClientServiceImpl(val clientRepository: ClientRepository, private val mapper: ClientMapper) : ClientService {
    override fun getClients(): List<Client> {
        return clientRepository.findAll()
    }

    override fun getClient(clientId: String): Client {

        val client = clientRepository.findById(UUID.fromString(clientId))
        return client.get() ?: throw NotFoundException("Client with uuid: $clientId not found")
    }

    override fun createClient(clientRequest: CreateClientRequest): Client {
        val client = mapper.map(clientRequest)
        client.registeredAt = Instant.now()
        client.updatedAt = Instant.now()
        return clientRepository.save(client)
    }

    override fun updateClient(clientId: String, clientUpdate: UpdateClientRequest): Client {
        val update = mapper.map(clientUpdate, clientId)
        update.updatedAt = Instant.now()
        var client: Client? = null
        clientRepository.findById(UUID.fromString(clientId)).ifPresent { t: Client -> mapper.update(t, update); clientRepository.save(t);client = t }
        return client ?: throw Exception("Something wrong. Client not updated!")
    }

    override fun sendVerifyEmail(clientId: String) {
        TODO("Not yet implemented")
    }

    override fun resetPassword(clientId: String) {
        TODO("Not yet implemented")
    }
}