package com.challenge.fastfood.adapter.outbound.mapstruct;


import com.challenge.fastfood.adapter.inbound.controller.request.ClientRequest;
import com.challenge.fastfood.adapter.inbound.controller.response.ClientResponse;
import com.challenge.fastfood.adapter.outbound.repository.ClientEntity;
import com.challenge.fastfood.domain.actor.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    Client clientRequestToClient(ClientRequest clientRequest);

    ClientResponse clientToClientResponse(Client client);

    ClientEntity clientToClientEntity(Client client);

    Client clientEntityToClient(ClientEntity clientEntity);

}
