package com.challenge.fastfood.aplication.usecases.lunchItem;


import com.challenge.fastfood.interfaces.lunchItem.FindLunchItems;
import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.domain.entities.LunchItemType;

import java.util.List;

public class FindLunchItemsUseCase {

    private final FindLunchItems findLunchItems;

    public FindLunchItemsUseCase(FindLunchItems findLunchItems) {
        this.findLunchItems = findLunchItems;
    }

    public List<LunchItem> findLunchItems(LunchItemType type) {
        return findLunchItems.findLunchItems(type);
    }

    public LunchItem findLunchItemByName(String name) {

        if (name == null) {
            throw new LunchItemException("Invalid lunch item, name is required");
        }

        return findLunchItems.findLunchItemByName(name);
    }
}
