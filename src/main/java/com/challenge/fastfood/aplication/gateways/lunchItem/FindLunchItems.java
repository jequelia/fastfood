package com.challenge.fastfood.aplication.gateways.lunchItem;


import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.domain.entities.LunchItemType;

import java.util.List;

public interface FindLunchItems {

    public LunchItem findLunchItemById(Long id);
    List<LunchItem> findLunchItems(LunchItemType type);
    LunchItem findLunchItemByName(String name);
}
