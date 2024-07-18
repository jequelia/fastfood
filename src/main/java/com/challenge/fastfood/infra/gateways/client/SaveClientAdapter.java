package com.challenge.fastfood.infra.gateways.client;

import com.challenge.fastfood.aplication.gateways.client.SaveClient;
import com.challenge.fastfood.domain.entities.Client;
import com.challenge.fastfood.infra.gateways.mapstruct.ClientMapper;
import com.challenge.fastfood.infra.persistence.client.ClientEntity;
import com.challenge.fastfood.infra.persistence.client.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


public class SaveClientAdapter implements SaveClient {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public SaveClientAdapter(ClientRepository clientRepository, ClientMapper clientMapper) {
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
