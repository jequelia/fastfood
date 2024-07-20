package com.challenge.fastfood.gateways.lunch;

import com.challenge.fastfood.interfaces.lunch.SaveLunchGatewayInterface;
import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.infra.mapstruct.LunchItemMapper;
import com.challenge.fastfood.infra.mapstruct.LunchMapper;
import com.challenge.fastfood.infra.persistence.client.ClientEntity;
import com.challenge.fastfood.infra.persistence.client.ClientRepository;
import com.challenge.fastfood.infra.persistence.lunch.LunchEntity;
import com.challenge.fastfood.infra.persistence.lunch.LunchRepository;
import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemEntity;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;


public class SaveLunchGatewayImpl implements SaveLunchGatewayInterface {

    private final LunchRepository lunchRepository;
    private final ClientRepository clientRepository;
    private final LunchMapper lunchMapper;
    private final LunchItemMapper lunchItemMapper;

    public SaveLunchGatewayImpl(LunchRepository lunchRepository,

                                ClientRepository clientRepository,
                                LunchMapper lunchMapper,
                                LunchItemMapper lunchItemMapper) {
        this.lunchRepository = lunchRepository;
        this.clientRepository = clientRepository;
        this.lunchMapper = lunchMapper;
        this.lunchItemMapper = lunchItemMapper;
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



}
