package com.challenge.fastfood.infra.mapstruct;


import com.challenge.fastfood.domain.entities.Client;
import com.challenge.fastfood.api.request.ClientRequest;
import com.challenge.fastfood.api.response.ClientResponse;
import com.challenge.fastfood.infra.persistence.client.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {




    @Mapping(target = "id", ignore = true)
    Client clientRequestToClient(ClientRequest clientRequest);

    ClientResponse clientToClientResponse(Client client);

//    List<ClientResponse> clientListToClientResponse(List<Client> client);
    @Mapping(target = "lunches", ignore = true)
    ClientEntity clientToClientEntity(Client client);

    Client clientEntityToClient(ClientEntity clientEntity);

    List<Client> clientListEntityToClient(List<ClientEntity> clientEntity);

}
