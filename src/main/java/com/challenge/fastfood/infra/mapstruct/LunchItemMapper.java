package com.challenge.fastfood.infra.mapstruct;

import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.api.request.LunchItemRequest;
import com.challenge.fastfood.api.response.LunchItemResponse;
import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LunchItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    LunchItem lunchItemRequestToLunchItem(LunchItemRequest lunchItemRequest);

    LunchItemResponse lunchItemToLunchItemResponse(LunchItem lunchItem);
    List<LunchItemResponse> lunchItemToLunchItemResponse(List<LunchItem> lunchItems);

    @Mapping(target = "status", source = "status")
    LunchItemEntity lunchItemToLunchItemEntity(LunchItem lunchItem);

    LunchItem lunchItemEntityToLunchItem(LunchItemEntity lunchItemEntity);
    List<LunchItem> lunchItemEntityToLunchItem(List<LunchItemEntity> lunchItemEntities);


}
