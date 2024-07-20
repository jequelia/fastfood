package com.challenge.fastfood.controller;

import com.challenge.fastfood.aplication.usecases.lunchItem.CreateLunchItemUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.EditLunchItemUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.FindLunchItemsUseCase;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.domain.entities.LunchItemType;

import java.util.List;

public class LunchItemsController {

    private final CreateLunchItemUseCase createLunchItemUseCase;
    private final EditLunchItemUseCase editLunchItemUseCase;
    private final FindLunchItemsUseCase findLunchItemsUseCase;

    public LunchItemsController(
            CreateLunchItemUseCase createLunchItemUseCase,
            EditLunchItemUseCase editLunchItemUseCase,
            FindLunchItemsUseCase findLunchItemsUseCase
    ) {
        this.createLunchItemUseCase = createLunchItemUseCase;
        this.editLunchItemUseCase = editLunchItemUseCase;
        this.findLunchItemsUseCase = findLunchItemsUseCase;
    }

    public LunchItem createLunchItem(LunchItem lunchItem) {
        return createLunchItemUseCase.createLunchItem(lunchItem);
    }

    public Boolean editStatusLunchItem(Long idLunchItem) {
        return editLunchItemUseCase.editStatusLunchItem(idLunchItem);
    }

    public LunchItem editLunchItem(Long idLunchItem, LunchItem lunchItem) {
        return editLunchItemUseCase.editLunchItem(idLunchItem, lunchItem);
    }

    public List<LunchItem> findLunchItems(LunchItemType type) {
        return findLunchItemsUseCase.findLunchItems(type);
    }

    public LunchItem findLunchItemByName(String name) {
        return findLunchItemsUseCase.findLunchItemByName(name);
    }
}