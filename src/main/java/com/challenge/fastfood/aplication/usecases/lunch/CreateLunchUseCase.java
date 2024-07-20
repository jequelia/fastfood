package com.challenge.fastfood.aplication.usecases.lunch;

import com.challenge.fastfood.interfaces.lunch.SaveLunch;
import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.api.request.LunchRequest;

public class CreateLunchUseCase {

    private final SaveLunch saveLunch;

    public CreateLunchUseCase(SaveLunch saveLunch) {
        this.saveLunch = saveLunch;
    }

    public Lunch saveLunch(Lunch lunch) {
        return saveLunch.saveLunch(lunch);
    }

    public Lunch createLunch(Lunch lunch) {
        return saveLunch.createLunch(lunch);
    }


}
