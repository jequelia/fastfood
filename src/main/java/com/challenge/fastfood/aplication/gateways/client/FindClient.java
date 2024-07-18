package com.challenge.fastfood.aplication.gateways.client;

import com.challenge.fastfood.domain.entities.Client;

public interface FindClient {


    public Client findClientById(Long id);
    Client findClient(String cpf);
}
