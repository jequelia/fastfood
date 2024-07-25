package com.challenge.fastfood.framework.mapstruct;

import com.challenge.fastfood.entities.Lunch;
import com.challenge.fastfood.framework.persistence.lunch.LunchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LunchMapper {

    @Mapping(target = "lunchItems", source = "lunchItems")
    @Mapping(target = "priceTotal", source = "priceTotal")
    Lunch lunchEntityToLunch(LunchEntity lunchEntity);


    List<Lunch> lunchsEntityToLunchs(List<LunchEntity> lunchEntity);


}
