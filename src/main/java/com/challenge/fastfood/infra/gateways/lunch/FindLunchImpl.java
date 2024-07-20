package com.challenge.fastfood.infra.gateways.lunch;

import com.challenge.fastfood.aplication.gateways.lunch.FindLunch;
import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.infra.mapstruct.LunchMapper;
import com.challenge.fastfood.infra.persistence.lunch.LunchEntity;
import com.challenge.fastfood.infra.persistence.lunch.LunchRepository;

import java.util.List;


public class FindLunchImpl implements FindLunch {

    private final LunchRepository lunchRepository;
    private final LunchMapper lunchMapper;

    public FindLunchImpl(LunchRepository lunchRepository, LunchMapper lunchMapper) {
        this.lunchRepository = lunchRepository;
        this.lunchMapper = lunchMapper;
    }


    @Override
    public List<Lunch> findLunchs() {
        List<LunchEntity> lunchRepositoryAll = lunchRepository.findAll();
        return lunchMapper.lunchsEntityToLunchs(lunchRepositoryAll);
    }

    @Override
    public Lunch findLunchById(Long id) {
        LunchEntity lunchRepositoryAll = lunchRepository.findById(id).orElse(null);
        return lunchMapper.lunchEntityToLunch(lunchRepositoryAll);
    }
}
