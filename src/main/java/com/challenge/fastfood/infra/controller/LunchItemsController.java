package com.challenge.fastfood.infra.controller;

import com.challenge.fastfood.aplication.usecases.lunchItem.CreateLunchItemUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.EditLunchItemUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.FindLunchItemsUseCase;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.domain.entities.LunchItemType;
import com.challenge.fastfood.infra.controller.request.LunchItemRequest;
import com.challenge.fastfood.infra.controller.response.LunchItemResponse;
import com.challenge.fastfood.infra.gateways.mapstruct.LunchItemMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lunch-items")
@Tag(name = "LunchItems", description = "Lunch Items Controller")
@RequiredArgsConstructor
public class LunchItemsController {

    private final LunchItemMapper lunchItemMapper;

    private final CreateLunchItemUseCase createLunchItemUseCase;

    private final EditLunchItemUseCase editLunchItemUseCase;

    private final FindLunchItemsUseCase findLunchItemsUseCase;


    @GetMapping
    @Operation(summary = "Get available lunch items", description = "Get available lunch items")
    public ResponseEntity<List<LunchItemResponse>> getLunchItems(@RequestParam(required = false) String type) {
        LunchItemType lunchItemType;
        try {
            lunchItemType = type == null ? null : LunchItemType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
        List<LunchItem> lunchItemList = findLunchItemsUseCase.findLunchItems(lunchItemType);
        return ResponseEntity.ok(lunchItemMapper.lunchItemToLunchItemResponse(lunchItemList));
    }

    @PostMapping
    @Operation(summary = "Create a lunch item", description = "Create a lunch item")
    public ResponseEntity<LunchItemResponse> createLunchItem(@Valid @RequestBody LunchItemRequest lunchItemRequest) {
        LunchItem lunchItem = createLunchItemUseCase.createLunchItem(lunchItemMapper.lunchItemRequestToLunchItem(lunchItemRequest));
        return ResponseEntity.ok(lunchItemMapper.lunchItemToLunchItemResponse(lunchItem));
    }

    @DeleteMapping("/{lunchId}")
    @Operation(summary = "Delete a lunch order", description = "Delete a lunch order")
    public ResponseEntity<Boolean> deleteLunch(@PathVariable Long lunchId){
        Boolean deleteLunchItem = editLunchItemUseCase.editStatusLunchItem(lunchId);
        return ResponseEntity.ok(deleteLunchItem);
    }

    @PutMapping("/{lunchId}")
    @Operation(summary = "Edit a lunch order", description = "Edit a lunch order")
    public ResponseEntity<LunchItemResponse> editLunchItem(@PathVariable Long lunchId, @Valid @RequestBody LunchItemRequest lunchItemRequest) {
        LunchItem toLunchItem = lunchItemMapper.lunchItemRequestToLunchItem(lunchItemRequest);
        LunchItem lunchItem = editLunchItemUseCase.editLunchItem(lunchId, toLunchItem);
        return ResponseEntity.ok(lunchItemMapper.lunchItemToLunchItemResponse(lunchItem));
    }
}
