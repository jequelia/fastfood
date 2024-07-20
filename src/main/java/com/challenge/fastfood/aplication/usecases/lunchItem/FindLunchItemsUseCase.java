package com.challenge.fastfood.aplication.usecases.lunchItem;


import com.challenge.fastfood.interfaces.lunchItem.FindLunchItemsGatewayInterface;
import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.domain.entities.LunchItemType;

import java.util.List;

public class FindLunchItemsUseCase {

    private final FindLunchItemsGatewayInterface findLunchItemsGatewayInterface;

    public FindLunchItemsUseCase(FindLunchItemsGatewayInterface findLunchItemsGatewayInterface) {
        this.findLunchItemsGatewayInterface = findLunchItemsGatewayInterface;
    }

    public List<LunchItem> findLunchItems(LunchItemType type) {
        return findLunchItemsGatewayInterface.findLunchItems(type);
    }

    public LunchItem findLunchItemByName(String name) {

        if (name == null) {
            throw new LunchItemException("Invalid lunch item, name is required");
        }

        return findLunchItemsGatewayInterface.findLunchItemByName(name);
    }
}
