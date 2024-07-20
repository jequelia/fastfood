package com.challenge.fastfood.infra.gateways.lunchItem;

import com.challenge.fastfood.aplication.gateways.lunchItem.FindLunchItems;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.domain.entities.LunchItemType;
import com.challenge.fastfood.infra.mapstruct.LunchItemMapper;
import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemEntity;
import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemsRepository;

import java.util.List;


public class FindLunchItemsImpl implements FindLunchItems {

    private final LunchItemsRepository lunchItemsRepository;
    private final LunchItemMapper lunchItemMapper;

    public FindLunchItemsImpl(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        this.lunchItemsRepository = lunchItemsRepository;
        this.lunchItemMapper = lunchItemMapper;
    }

    @Override
    public List<LunchItem> findLunchItems(LunchItemType type) {
        List<LunchItemEntity> lunchItemEntity;
        if (type == null) {
            lunchItemEntity = lunchItemsRepository.findByStatusTrue();
        } else {
            lunchItemEntity = lunchItemsRepository.findByTypeAndStatusTrue(type);
        }
        return lunchItemMapper.lunchItemEntityToLunchItem(lunchItemEntity);
    }

    @Override
    public LunchItem findLunchItemByName(String name) {
        LunchItemEntity lunchItemEntity = lunchItemsRepository.findByName(name);
        return lunchItemMapper.lunchItemEntityToLunchItem(lunchItemEntity);
    }

    @Override
    public LunchItem findLunchItemById(Long id) {
        LunchItemEntity lunchItemEntity = lunchItemsRepository.findByIdAndStatusTrue(id);
        return lunchItemMapper.lunchItemEntityToLunchItem(lunchItemEntity);
    }

}
