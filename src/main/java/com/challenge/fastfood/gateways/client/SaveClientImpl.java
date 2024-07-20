package com.challenge.fastfood.gateways.client;

import com.challenge.fastfood.interfaces.client.SaveClient;
import com.challenge.fastfood.domain.entities.Client;
import com.challenge.fastfood.infra.mapstruct.ClientMapper;
import com.challenge.fastfood.infra.persistence.client.ClientEntity;
import com.challenge.fastfood.infra.persistence.client.ClientRepository;
import jakarta.transaction.Transactional;


public class SaveClientImpl implements SaveClient {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public SaveClientImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    @Transactional
    public Client saveClient(Client client) {


        ClientEntity clientEntity = clientMapper.clientToClientEntity(client);
        ClientEntity clientEntitySaved = clientRepository.save(clientEntity);

        return clientMapper.clientEntityToClient(clientEntitySaved);
    }
}
