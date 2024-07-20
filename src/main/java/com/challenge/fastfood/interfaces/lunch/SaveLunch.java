package com.challenge.fastfood.interfaces.lunch;


import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.api.request.LunchRequest;

public interface SaveLunch {

    Lunch saveLunch(Lunch lunch);
}
