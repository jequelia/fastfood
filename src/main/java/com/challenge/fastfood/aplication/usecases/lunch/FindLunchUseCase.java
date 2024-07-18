package com.challenge.fastfood.aplication.usecases.lunch;

import com.challenge.fastfood.aplication.gateways.lunch.FindLunch;
import com.challenge.fastfood.domain.entities.Lunch;

import java.util.List;

public class FindLunchUseCase {

    private final FindLunch findLunch;

    public FindLunchUseCase(FindLunch saveLunch) {
        this.findLunch = saveLunch;
    }

    public List<Lunch> findLunchs() {
        return findLunch.findLunchs();
    }

    public Lunch findLunchById(Long id) {
        return findLunch.findLunchById(id);
    }
}
