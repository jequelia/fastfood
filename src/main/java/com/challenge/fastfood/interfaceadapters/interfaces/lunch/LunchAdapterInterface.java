package com.challenge.fastfood.interfaceadapters.interfaces.lunch;

import com.challenge.fastfood.entities.Lunch;
import java.util.List;

public interface LunchAdapterInterface {

    List<Lunch> findLunchs();
    Lunch findLunchById(Long id);
    Lunch saveLunch(Lunch lunch);

    Lunch editLunch(Lunch lunch);
}
