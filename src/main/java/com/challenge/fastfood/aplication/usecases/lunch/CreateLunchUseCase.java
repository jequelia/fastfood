package com.challenge.fastfood.aplication.usecases.lunch;

import com.challenge.fastfood.interfaces.lunch.SaveLunchGatewayInterface;
import com.challenge.fastfood.domain.entities.Lunch;

public class CreateLunchUseCase {

    private final SaveLunchGatewayInterface saveLunchGatewayInterface;

    public CreateLunchUseCase(SaveLunchGatewayInterface saveLunchGatewayInterface) {
        this.saveLunchGatewayInterface = saveLunchGatewayInterface;
    }


    public Lunch createLunch(Lunch lunch) {
        return saveLunchGatewayInterface.saveLunch(lunch);
    }


}
