package com.challenge.fastfood.adapter.out.repository;

import com.challenge.fastfood.adapter.out.mapstruct.LunchItemMapper;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.domain.entities.LunchItemType;
import com.challenge.fastfood.domain.ports.out.FindLunchItemsAdapterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindLunchItemsAdapter implements FindLunchItemsAdapterPort {

    private final LunchItemsRepository lunchItemsRepository;
    private final LunchItemMapper lunchItemMapper;

    @Override
    public List<LunchItem> findLunchItems(LunchItemType type) {
        List<LunchItemEntity> lunchItemEntity = lunchItemsRepository.findByType(type);
        return lunchItemMapper.lunchItemEntityToLunchItem(lunchItemEntity);
    }

}