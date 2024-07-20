package com.challenge.fastfood.aplication.usecases.lunchItem;


import com.challenge.fastfood.interfaces.lunchItem.EditLunchItem;
import com.challenge.fastfood.interfaces.lunchItem.FindLunchItems;
import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.domain.entities.LunchItem;

public class EditLunchItemUseCase {

    public static final boolean STATUS = false;
    private final EditLunchItem editLunchItem;
    private final FindLunchItems findLunchItems;

    public EditLunchItemUseCase(EditLunchItem editLunchItem, FindLunchItems findLunchItems) {
        this.editLunchItem = editLunchItem;
        this.findLunchItems = findLunchItems;
    }

    public Boolean editStatusLunchItem(Long idLunchItem) {

        if (idLunchItem == null) {
            throw new LunchItemException("idLunchItem and status are required");
        }

        LunchItem lunchItem = findLunchItems.findLunchItemById(idLunchItem);

        if (lunchItem == null) {
            throw new LunchItemException("Lunch item not found");
        }

        return editLunchItem.editStatusLunchItem(idLunchItem, STATUS);
    }

    public LunchItem editLunchItem(Long idLunchItem, LunchItem lunchItem) {

        if (idLunchItem == null || lunchItem == null) {
            throw new LunchItemException("idLunchItem and lunchItem are required");
        }

        LunchItem lunchItemById = findLunchItems.findLunchItemById(idLunchItem);

        if (lunchItemById == null) {
            throw new LunchItemException("Lunch item not found");
        }

        if (lunchItem.getPrice() <= 0) {
            throw new LunchItemException("Price must be greater than 0");
        }


        return editLunchItem.editLunchItem(lunchItem, idLunchItem);
    }

}
