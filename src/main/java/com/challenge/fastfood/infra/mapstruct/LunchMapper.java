package com.challenge.fastfood.infra.mapstruct;

import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.api.request.LunchRequest;
import com.challenge.fastfood.api.response.LunchResponse;
import com.challenge.fastfood.infra.persistence.lunch.LunchEntity;
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

    @Mapping(target = "cpf", source = "lunch.client.cpf")
    @Mapping(target = "numberLunch", source = "lunch.id")
    LunchResponse lunchToLunchResponse(Lunch lunch);

    @Mapping(target = "cpf", source = "lunch.client.cpf")
    @Mapping(target = "numberLunch", source = "lunch.id")
    List<LunchResponse> lunchsToLunchsResponse(List<Lunch> lunch);

    @Mapping(target = "client.id", source = "clientId")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "lunchItems", ignore = true)
    @Mapping(target = "priceTotal", ignore = true)
    Lunch lunchsRequestToLunch(LunchRequest lunch);


}
