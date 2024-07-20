package com.challenge.fastfood.interfaces.client;

import com.challenge.fastfood.domain.entities.Client;

public interface FindClientGatewayInterface {


    Client findClientById(Long id);
    Client findClient(String cpf);
}
