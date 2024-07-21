package com.challenge.fastfood.controller;

import com.challenge.fastfood.api.request.LunchItemRequest;
import com.challenge.fastfood.api.response.LunchItemResponse;
import com.challenge.fastfood.aplication.usecases.lunchItem.CreateLunchItemUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.EditLunchItemUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.FindLunchItemsUseCase;
import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.domain.entities.LunchItemType;
import com.challenge.fastfood.infra.mapstruct.LunchItemMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class LunchItemsController {

    private final CreateLunchItemUseCase createLunchItemUseCase;
    private final EditLunchItemUseCase editLunchItemUseCase;
    private final FindLunchItemsUseCase findLunchItemsUseCase;
    private final LunchItemMapper lunchItemMapper;


    public LunchItemsController(
            CreateLunchItemUseCase createLunchItemUseCase,
            EditLunchItemUseCase editLunchItemUseCase,
            FindLunchItemsUseCase findLunchItemsUseCase, LunchItemMapper lunchItemMapper
    ) {
        this.createLunchItemUseCase = createLunchItemUseCase;
        this.editLunchItemUseCase = editLunchItemUseCase;
        this.findLunchItemsUseCase = findLunchItemsUseCase;
        this.lunchItemMapper = lunchItemMapper;
    }

    public LunchItemResponse createLunchItem(LunchItemRequest lunchItemRequest) {
        LunchItem toLunchItem = lunchItemMapper.lunchItemRequestToLunchItem(lunchItemRequest);
        LunchItem lunchItem = createLunchItemUseCase.createLunchItem(toLunchItem);
        return lunchItemMapper.lunchItemToLunchItemResponse(lunchItem);
    }

    public Boolean editStatusLunchItem(Long idLunchItem) {
        return editLunchItemUseCase.editStatusLunchItem(idLunchItem);
    }

    public LunchItemResponse editLunchItem(Long idLunchItem, LunchItemRequest lunchItemRequest) {
        LunchItem toLunchItem = lunchItemMapper.lunchItemRequestToLunchItem(lunchItemRequest);
        LunchItem lunchItem = editLunchItemUseCase.editLunchItem(idLunchItem, toLunchItem);
        return lunchItemMapper.lunchItemToLunchItemResponse(lunchItem);
    }

    public List<LunchItemResponse> findLunchItems(String type) {
        LunchItemType lunchItemType;
        try {
            lunchItemType = type == null ? null : LunchItemType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new LunchItemException("type not found");
        }

        List<LunchItem> lunchItems = findLunchItemsUseCase.findLunchItems(lunchItemType);

        return lunchItemMapper.lunchItemToLunchItemResponse(lunchItems);
    }

    public LunchItem findLunchItemByName(String name) {
        return findLunchItemsUseCase.findLunchItemByName(name);
    }
}