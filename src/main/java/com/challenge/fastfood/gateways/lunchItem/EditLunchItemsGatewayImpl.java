package com.challenge.fastfood.gateways.lunchItem;

import com.challenge.fastfood.interfaces.lunchItem.EditLunchItemGatewayInterface;
import com.challenge.fastfood.config.exception.LunchItemException;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.infra.mapstruct.LunchItemMapper;
import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemEntity;
import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemsRepository;
import jakarta.transaction.Transactional;


public class EditLunchItemsGatewayImpl implements EditLunchItemGatewayInterface {

    private final LunchItemsRepository lunchItemsRepository;
    private final LunchItemMapper lunchItemMapper;

    public EditLunchItemsGatewayImpl(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        this.lunchItemsRepository = lunchItemsRepository;
        this.lunchItemMapper = lunchItemMapper;
    }


    @Override
    @Transactional
    public Boolean editStatusLunchItem(Long idLunchItem, Boolean status) {
        try {
            LunchItemEntity lunchItemsRepositoryById = lunchItemsRepository
                    .findById(idLunchItem)
                    .orElseThrow(() -> new LunchItemException("Lunch item not found." ));
            lunchItemsRepositoryById.setStatus(status);
            lunchItemsRepository.save(lunchItemsRepositoryById);
            return true;

    }   catch (Exception e) {
            throw new LunchItemException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public LunchItem editLunchItem(LunchItem lunchItem, Long idLunchItem) {
        LunchItem toLunchItem ;
        try {

            LunchItemEntity lunchItemEntity = lunchItemsRepository.findById(idLunchItem).orElseThrow(() -> new LunchItemException("Lunch item not found." ));
            lunchItemEntity.setStatus(true);
            lunchItemEntity.setType(lunchItem.getType());
            lunchItemEntity.setPrice(lunchItem.getPrice());
            LunchItemEntity lunchItemSave = lunchItemsRepository.save(lunchItemEntity);
            toLunchItem = lunchItemMapper.lunchItemEntityToLunchItem(lunchItemSave);

        } catch (Exception e) {
            throw new LunchItemException(e.getMessage());
        }
        return toLunchItem;
    }
}
