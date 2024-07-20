package com.challenge.fastfood.aplication.gateways.lunch;


import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.api.request.LunchRequest;

public interface SaveLunch {

    Lunch saveLunch(Lunch lunch);
    Lunch createLunch(LunchRequest lunchRequest);
}
