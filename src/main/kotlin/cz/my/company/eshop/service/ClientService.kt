package cz.my.company.eshop.service

import cz.my.company.eshop.model.Client
import cz.my.company.eshop.model.ClientUpdate
import cz.my.company.eshop.model.request.CreateClientRequest
import cz.my.company.eshop.model.request.UpdateClientRequest
import org.springframework.stereotype.Service

@Service
interface ClientService {

    fun getClients(): List<Client>
    fun getClient(clientId: String): Client
    fun createClient(client: CreateClientRequest): Client
    fun updateClient(clientId: String, clientUpdate: UpdateClientRequest):Client
    fun sendVerifyEmail(clientId: String)
    fun resetPassword(clientId: String)

}
