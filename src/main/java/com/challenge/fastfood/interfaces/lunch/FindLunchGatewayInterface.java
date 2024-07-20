package com.challenge.fastfood.interfaces.lunch;

import com.challenge.fastfood.domain.entities.Lunch;

import java.util.List;

public interface FindLunchGatewayInterface {

    List<Lunch> findLunchs();
    Lunch findLunchById(Long id);
}
