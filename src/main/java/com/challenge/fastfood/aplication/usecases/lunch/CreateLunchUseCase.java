package com.challenge.fastfood.aplication.usecases.lunch;

import com.challenge.fastfood.aplication.gateways.lunch.SaveLunch;
import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.infra.controller.request.LunchRequest;

public class CreateLunchUseCase {

    private final SaveLunch saveLunch;

    public CreateLunchUseCase(SaveLunch saveLunch) {
        this.saveLunch = saveLunch;
    }

    public Lunch saveLunch(Lunch lunch) {
        return saveLunch.saveLunch(lunch);
    }

    public Lunch createLunch(LunchRequest lunch) {
        return saveLunch.createLunch(lunch);
    }


}
