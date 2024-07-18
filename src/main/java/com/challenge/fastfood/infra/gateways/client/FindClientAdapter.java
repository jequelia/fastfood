package com.challenge.fastfood.infra.gateways.client;

import com.challenge.fastfood.aplication.gateways.client.FindClient;
import com.challenge.fastfood.domain.entities.Client;
import com.challenge.fastfood.infra.gateways.mapstruct.ClientMapper;
import com.challenge.fastfood.infra.persistence.client.ClientEntity;
import com.challenge.fastfood.infra.persistence.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public class FindClientAdapter implements FindClient {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public FindClientAdapter(ClientRepository clientRepository, ClientMapper clientMapper) {
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
