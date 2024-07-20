package com.challenge.fastfood.gateways.client;

import com.challenge.fastfood.interfaces.client.FindClient;
import com.challenge.fastfood.domain.entities.Client;
import com.challenge.fastfood.infra.mapstruct.ClientMapper;
import com.challenge.fastfood.infra.persistence.client.ClientEntity;
import com.challenge.fastfood.infra.persistence.client.ClientRepository;

public class FindClientImpl implements FindClient {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public FindClientImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client findClient(String cpf) {
        ClientEntity clientEntity = clientRepository.findByCpf(cpf);
        return clientMapper.clientEntityToClient(clientEntity);
    }

    @Override
    public Client findClientById(Long id) {
        ClientEntity clientEntity = clientRepository.findById(id).orElse(null);
        return clientMapper.clientEntityToClient(clientEntity);
    }

}
