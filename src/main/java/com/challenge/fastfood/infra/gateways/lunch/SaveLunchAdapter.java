package com.challenge.fastfood.infra.gateways.lunch;

import com.challenge.fastfood.aplication.gateways.lunch.SaveLunch;
import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.domain.entities.Client;
import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.infra.controller.request.LunchRequest;
import com.challenge.fastfood.infra.gateways.mapstruct.ClientMapper;
import com.challenge.fastfood.infra.gateways.mapstruct.LunchItemMapper;
import com.challenge.fastfood.infra.gateways.mapstruct.LunchMapper;
import com.challenge.fastfood.infra.persistence.client.ClientEntity;
import com.challenge.fastfood.infra.persistence.client.ClientRepository;
import com.challenge.fastfood.infra.persistence.lunch.LunchEntity;
import com.challenge.fastfood.infra.persistence.lunch.LunchRepository;
import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemEntity;
import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class SaveLunchAdapter implements SaveLunch {

    private final LunchRepository lunchRepository;
    private final LunchItemsRepository lunchItemsRepository;
    private final ClientRepository clientRepository;
    private final LunchMapper lunchMapper;
    private final LunchItemMapper lunchItemMapper;
    private final ClientMapper clientMapper;

    public SaveLunchAdapter(LunchRepository lunchRepository,
                            LunchItemsRepository lunchItemsRepository,
                            ClientRepository clientRepository,
                            LunchMapper lunchMapper,
                            LunchItemMapper lunchItemMapper,
                            ClientMapper clientMapper) {
        this.lunchRepository = lunchRepository;
        this.lunchItemsRepository = lunchItemsRepository;
        this.clientRepository = clientRepository;
        this.lunchMapper = lunchMapper;
        this.lunchItemMapper = lunchItemMapper;
        this.clientMapper = clientMapper;
    }

    @Override
    @Transactional
    public Lunch saveLunch(Lunch lunch) {

        List<LunchItemEntity> lunchItems = new ArrayList<>();
        ClientEntity clientEntity = null;

        for(LunchItem item : lunch.getLunchItems()){
            LunchItemEntity lunchItemEntity = lunchItemMapper.lunchItemToLunchItemEntity(item);
            lunchItems.add(lunchItemEntity);
        }


        clientEntity = getClientEntity(lunch, clientEntity);

        LunchEntity lunchEntity = new LunchEntity();
        lunchEntity.setLunchItems(lunchItems);
        lunchEntity.setClient(clientEntity);
        lunchEntity.setStatus("PENDENTE");
        double price = 0;
        for (LunchItemEntity lunchItem : lunchItems) {
            price += lunchItem.getPrice();
        }
        lunchEntity.setPriceTotal(price);
        LunchEntity lunchEntitySaved = lunchRepository.save(lunchEntity);

        return lunchMapper.lunchEntityToLunch(lunchEntitySaved);
    }

    private ClientEntity getClientEntity(Lunch lunch, ClientEntity clientEntity) {
        if(lunch.getClient() != null && lunch.getClient().getId() != null) {
            clientEntity = clientRepository.findById(lunch.getClient().getId() ).orElse(null);
        }
        return clientEntity;
    }

    @Override
    public Lunch createLunch(LunchRequest lunchRequest) {

        List<LunchItem> lunchItems = new ArrayList<>();

        mapperLunch(lunchRequest.drink(), lunchItems);
        mapperLunch(lunchRequest.snack(), lunchItems);
        mapperLunch(lunchRequest.accompaniment(), lunchItems);
        mapperLunch(lunchRequest.dessert(), lunchItems);

        Lunch lunch = new Lunch();
        if (lunchRequest.clientId() != null) {
            ClientEntity client = clientRepository.findById(lunchRequest.clientId()).orElse(null);
            Client toClient = clientMapper.clientEntityToClient(client);
            if (toClient == null) {
                throw new ClientException("Client id doesn't represent any existing client");
            }
            toClient.setId(lunchRequest.clientId());
            lunch.setClient(toClient);
        }
        lunch.setLunchItems(lunchItems);

        return lunch;
    }

    private void mapperLunch(List<Long> lunchRequest, List<LunchItem> lunchItems) {
        for (Long lunchItem : lunchRequest) {
            LunchItemEntity itemById = lunchItemsRepository.findById(lunchItem).orElse(null);
            LunchItem toLunchItem = lunchItemMapper.lunchItemEntityToLunchItem(itemById);
            if (toLunchItem != null){
                lunchItems.add(toLunchItem);
            }
        }
    }




}
