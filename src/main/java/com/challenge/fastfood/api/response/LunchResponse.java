package com.challenge.fastfood.api.response;

import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record LunchResponse(

        @Schema(description = "The details for the snack of the lunch")
        List<LunchItemEntity> lunchItems,
        String status,
        @Schema(description = "The client CPF", example = "111.111.111-11")
        String cpf,
        @Schema(description = "The total price", example = "142.34")
        Float priceTotal,
        @Schema(description = "The number of the lunch", example = "1")
        Long numberLunch
        ) {
}