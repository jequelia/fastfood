package com.challenge.fastfood.aplication.usecases.lunchItem;

import com.challenge.fastfood.aplication.gateways.lunchItem.FindLunchItems;
import com.challenge.fastfood.aplication.gateways.lunchItem.SaveLunchItem;
import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.domain.entities.LunchItem;

public class CreateLunchItemUseCase {

    private final SaveLunchItem saveLunchItem  ;

    private final FindLunchItems findLunchItems  ;

    public CreateLunchItemUseCase(SaveLunchItem   saveLunchItem  , FindLunchItems   findLunchItems  ) {
        this.saveLunchItem   = saveLunchItem  ;
        this.findLunchItems   = findLunchItems  ;
    }

    public LunchItem createLunchItem(LunchItem lunchItem) {

        LunchItem lunchItemByName = findLunchItems  .findLunchItemByName(lunchItem.getName());
        if(lunchItemByName != null){
            throw new LunchItemException("LunchItem already exists");
        }

        lunchItem.setStatus(true);

        return saveLunchItem  .saveLunchItem(lunchItem);
    }
}
