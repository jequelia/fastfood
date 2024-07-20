package com.challenge.fastfood.gateways.lunchItem;

import com.challenge.fastfood.interfaces.lunchItem.SaveLunchItem;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.infra.mapstruct.LunchItemMapper;
import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemEntity;
import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemsRepository;
import jakarta.transaction.Transactional;


public class SaveLunchItemImpl implements SaveLunchItem {

    private final LunchItemsRepository lunchItemsRepository;
    private final LunchItemMapper lunchItemMapper;

    public SaveLunchItemImpl(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        this.lunchItemsRepository = lunchItemsRepository;
        this.lunchItemMapper = lunchItemMapper;
    }

    @Override
    @Transactional
    public LunchItem saveLunchItem(LunchItem lunchItem) {
        LunchItemEntity lunchItemEntity = lunchItemMapper.lunchItemToLunchItemEntity(lunchItem);
        LunchItemEntity lunchItemEntitySaved = lunchItemsRepository.save(lunchItemEntity);
        return lunchItemMapper.lunchItemEntityToLunchItem(lunchItemEntitySaved);
    }
}
