package cz.my.company.eshop.controller

import cz.my.company.eshop.model.Client
import cz.my.company.eshop.model.ClientUpdate
import cz.my.company.eshop.model.request.CreateClientRequest
import cz.my.company.eshop.model.request.UpdateClientRequest
import cz.my.company.eshop.service.ClientService
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.data.annotation.AccessType
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.annotation.security.PermitAll
import javax.servlet.annotation.HttpConstraint
import javax.servlet.annotation.HttpMethodConstraint
import javax.servlet.annotation.ServletSecurity


@RestController
@RequestMapping("/clients")
class ClientController(private val clientService: ClientService) {

    @GetMapping(produces=[MediaType.APPLICATION_JSON_VALUE])
    fun getClients(): List<Client> {
        return clientService.getClients()
    }

    @GetMapping("/{clientId}",produces=[MediaType.APPLICATION_JSON_VALUE])
    fun getClient(@PathVariable clientId:String): Client {
        return clientService.getClient(clientId)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces=[MediaType.APPLICATION_JSON_VALUE])
    fun createClient(@RequestBody client:CreateClientRequest): Client {
        return clientService.createClient(client)
    }

    @PatchMapping("/{clientId}",consumes = [MediaType.APPLICATION_JSON_VALUE],produces=[MediaType.APPLICATION_JSON_VALUE])
    fun updateClient(@PathVariable clientId:String,@RequestBody clientUpdate:UpdateClientRequest): Client {
        return clientService.updateClient(clientId,clientUpdate)
    }

}