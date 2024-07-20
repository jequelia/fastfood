package com.challenge.fastfood.aplication.usecases.lunch;

import com.challenge.fastfood.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.domain.entities.Lunch;

import java.util.List;

public class FindLunchUseCase {

    private final FindLunchGatewayInterface findLunchGatewayInterface;

    public FindLunchUseCase(FindLunchGatewayInterface saveLunch) {
        this.findLunchGatewayInterface = saveLunch;
    }

    public List<Lunch> findLunchs() {
        return findLunchGatewayInterface.findLunchs();
    }

    public Lunch findLunchById(Long id) {
        return findLunchGatewayInterface.findLunchById(id);
    }
}
