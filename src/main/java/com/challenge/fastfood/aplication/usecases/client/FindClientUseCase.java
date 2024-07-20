package com.challenge.fastfood.aplication.usecases.client;

import com.challenge.fastfood.interfaces.client.FindClient;
import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.domain.entities.Client;

public class FindClientUseCase {

    private final FindClient findClient;

    public FindClientUseCase(FindClient findClient) {
        this.findClient = findClient;
    }

    public Client findClient(String cpf) {

        if (cpf == null) {
            throw new ClientException("The CPF is required");
        }

        Client client = findClient.findClient(cpf);

        if (client == null || client.getCpf() == null || client.getEmail() == null) {
            throw new ClientException("Client not found");
        }
        return client;
    }
}
