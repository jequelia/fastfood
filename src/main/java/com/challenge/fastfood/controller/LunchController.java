package com.challenge.fastfood.controller;

import com.challenge.fastfood.api.request.LunchRequest;
import com.challenge.fastfood.interfaces.client.FindClient;
import com.challenge.fastfood.interfaces.lunchItem.FindLunchItems;
import com.challenge.fastfood.aplication.usecases.lunch.CreateLunchUseCase;
import com.challenge.fastfood.aplication.usecases.lunch.FindLunchUseCase;
import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.domain.entities.Client;
import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.domain.entities.LunchItem;

import java.util.ArrayList;
import java.util.List;

public class LunchController {

    private final CreateLunchUseCase createLunchUseCase;
    private final FindLunchUseCase findLunchUseCase;
    private final FindLunchItems findLunchItemsAdapterPort;
    private final FindClient findClientAdapterPort;

    public LunchController(
            CreateLunchUseCase createLunchUseCase,
            FindLunchUseCase findLunchUseCase,
            FindLunchItems findLunchAdapterPort,
            FindClient findClientAdapterPort) {
        this.createLunchUseCase = createLunchUseCase;
        this.findLunchUseCase = findLunchUseCase;
        this.findLunchItemsAdapterPort = findLunchAdapterPort;
        this.findClientAdapterPort = findClientAdapterPort;
    }

    public Lunch createLunch(LunchRequest lunchRequest) {

        List<LunchItem> lunchItems = new ArrayList<>();

        mapperLunch(lunchRequest.drink(), lunchItems);
        mapperLunch(lunchRequest.snack(), lunchItems);
        mapperLunch(lunchRequest.accompaniment(), lunchItems);
        mapperLunch(lunchRequest.dessert(), lunchItems);

        Lunch lunch = new Lunch();
        if (lunchRequest.clientId() != null) {
            Client client = findClientAdapterPort.findClientById(lunchRequest.clientId());
            if (client == null) {
                throw new ClientException("Client id doesn't represent any existing client");
            }
            client.setId(lunchRequest.clientId());
            lunch.setClient(client);
        }
        lunch.setLunchItems(lunchItems);

        return createLunchUseCase.createLunch(lunch);
    }

    private void mapperLunch(List<Long> lunchRequest, List<LunchItem> lunchItems) {
        for (Long lunchItem : lunchRequest) {
            LunchItem itemById = findLunchItemsAdapterPort.findLunchItemById(lunchItem);
            if (itemById != null){
                lunchItems.add(itemById);
            }
        }
    }

    public List<Lunch> findLunchs() {
        return findLunchUseCase.findLunchs();
    }

    public Lunch findLunchById(Long id) {
        return findLunchUseCase.findLunchById(id);
    }
}