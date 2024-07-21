package com.challenge.fastfood.controller;

import com.challenge.fastfood.api.request.LunchRequest;
import com.challenge.fastfood.api.response.LunchResponse;
import com.challenge.fastfood.infra.mapstruct.LunchMapper;
import com.challenge.fastfood.interfaces.client.FindClientGatewayInterface;
import com.challenge.fastfood.interfaces.lunchItem.FindLunchItemsGatewayInterface;
import com.challenge.fastfood.domain.usecases.lunch.CreateLunchUseCase;
import com.challenge.fastfood.domain.usecases.lunch.FindLunchUseCase;
import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.domain.entities.Client;
import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.domain.entities.LunchItem;

import java.util.ArrayList;
import java.util.List;

public class LunchController {

    private final CreateLunchUseCase createLunchUseCase;
    private final FindLunchUseCase findLunchUseCase;
    private final FindLunchItemsGatewayInterface findLunchItemsGatewayInterface;
    private final FindClientGatewayInterface findClientGatewayInterface;
    private final LunchMapper lunchMapper;


    public LunchController(
            CreateLunchUseCase createLunchUseCase,
            FindLunchUseCase findLunchUseCase,
            FindLunchItemsGatewayInterface findLunch,
            FindClientGatewayInterface findClientGatewayInterface, LunchMapper lunchMapper) {
        this.createLunchUseCase = createLunchUseCase;
        this.findLunchUseCase = findLunchUseCase;
        this.findLunchItemsGatewayInterface = findLunch;
        this.findClientGatewayInterface = findClientGatewayInterface;
        this.lunchMapper = lunchMapper;
    }

    public LunchResponse createLunch(LunchRequest lunchRequest) {

        List<LunchItem> lunchItems = new ArrayList<>();

        mapperLunch(lunchRequest.drink(), lunchItems);
        mapperLunch(lunchRequest.snack(), lunchItems);
        mapperLunch(lunchRequest.accompaniment(), lunchItems);
        mapperLunch(lunchRequest.dessert(), lunchItems);

        Lunch lunch = new Lunch();
        if (lunchRequest.clientId() != null) {
            Client client = findClientGatewayInterface.findClientById(lunchRequest.clientId());
            if (client == null) {
                throw new ClientException("Client id doesn't represent any existing client");
            }
            client.setId(lunchRequest.clientId());
            lunch.setClient(client);
        }
        lunch.setLunchItems(lunchItems);
        Lunch useCaseLunch = createLunchUseCase.createLunch(lunch);

        return lunchMapper.lunchToLunchResponse(useCaseLunch);
    }

    private void mapperLunch(List<Long> lunchRequest, List<LunchItem> lunchItems) {
        for (Long lunchItem : lunchRequest) {
            LunchItem itemById = findLunchItemsGatewayInterface.findLunchItemById(lunchItem);
            if (itemById != null) {
                lunchItems.add(itemById);
            }
        }
    }

    public List<LunchResponse> findLunchs() {
        List<Lunch> lunchs = findLunchUseCase.findLunchs();
        return lunchMapper.lunchsToLunchsResponse(lunchs);
    }

    public LunchResponse findLunchById(Long id) {
        Lunch lunchById = findLunchUseCase.findLunchById(id);
        return lunchMapper.lunchToLunchResponse(lunchById);
    }
}