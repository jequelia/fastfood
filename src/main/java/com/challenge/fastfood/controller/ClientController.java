package com.challenge.fastfood.controller;

import com.challenge.fastfood.api.request.ClientRequest;
import com.challenge.fastfood.api.response.ClientResponse;
import com.challenge.fastfood.domain.usecases.client.CreateClientUseCase;
import com.challenge.fastfood.domain.usecases.client.FindClientUseCase;
import com.challenge.fastfood.domain.entities.Client;
import com.challenge.fastfood.infra.mapstruct.ClientMapper;

public class ClientController {

    private final CreateClientUseCase createClientUseCase;
    private final FindClientUseCase findClientUseCase;
    private final ClientMapper clientMapper;


    public ClientController(
            CreateClientUseCase createClientUseCase,
            FindClientUseCase findClientUseCase, ClientMapper clientMapper) {
        this.createClientUseCase = createClientUseCase;
        this.findClientUseCase = findClientUseCase;
        this.clientMapper = clientMapper;
    }

    public ClientResponse createClient(ClientRequest clientRequest) {
        Client toClient = clientMapper.clientRequestToClient(clientRequest);
        Client client = createClientUseCase.createClient(toClient);
        return  clientMapper.clientToClientResponse(client);
    }

    public ClientResponse findClient(String cpf) {

        Client client = findClientUseCase.findClient(cpf);

        return  clientMapper.clientToClientResponse(client);
    }
}