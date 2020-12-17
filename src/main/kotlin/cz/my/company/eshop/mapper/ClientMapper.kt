package cz.my.company.eshop.mapper

import cz.my.company.eshop.model.Client
import cz.my.company.eshop.model.request.CreateClientRequest
import cz.my.company.eshop.model.request.UpdateClientRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.Mappings
import java.time.Instant

@Mapper(componentModel = "spring")
abstract class ClientMapper {

    abstract fun map(createClientRequest: CreateClientRequest):Client

    abstract fun map(updateClientRequest: UpdateClientRequest, clientId:String):Client

    abstract fun update(@MappingTarget client:Client,clientUpdate:Client):Client
}