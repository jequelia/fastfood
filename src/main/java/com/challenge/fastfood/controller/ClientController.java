package com.challenge.fastfood.controller;

import com.challenge.fastfood.aplication.usecases.client.CreateClientUseCase;
import com.challenge.fastfood.aplication.usecases.client.FindClientUseCase;
import com.challenge.fastfood.domain.entities.Client;

public class ClientController {

    private final CreateClientUseCase createClientUseCase;
    private final FindClientUseCase findClientUseCase;

    public ClientController(
            CreateClientUseCase createClientUseCase,
            FindClientUseCase findClientUseCase) {
        this.createClientUseCase = createClientUseCase;
        this.findClientUseCase = findClientUseCase;

    }

    public Client createClient(Client client) {
        return createClientUseCase.createClient(client);
    }

    public Client findClient(String cpf) {
        return findClientUseCase.findClient(cpf);
    }
}