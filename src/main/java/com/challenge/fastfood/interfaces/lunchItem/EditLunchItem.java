package com.challenge.fastfood.interfaces.lunchItem;


import com.challenge.fastfood.domain.entities.LunchItem;

public interface EditLunchItem {

    Boolean editStatusLunchItem(Long idLunchItem, Boolean status);

    LunchItem editLunchItem(LunchItem lunchItem, Long idLunchItem);
}
