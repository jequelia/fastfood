package com.challenge.fastfood.api;

import com.challenge.fastfood.controller.LunchItemsController;
import com.challenge.fastfood.domain.entities.LunchItem;
import com.challenge.fastfood.domain.entities.LunchItemType;
import com.challenge.fastfood.api.request.LunchItemRequest;
import com.challenge.fastfood.api.response.LunchItemResponse;
import com.challenge.fastfood.infra.mapstruct.LunchItemMapper;
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
public class LunchItemsApi {


    private final LunchItemsController lunchItemsController;
    private final LunchItemMapper lunchItemMapper;

    @GetMapping
    @Operation(summary = "Get available lunch items", description = "Get available lunch items")
    public ResponseEntity<List<LunchItemResponse>> getLunchItems(@RequestParam(required = false) String type) {
        LunchItemType lunchItemType;
        try {
            lunchItemType = type == null ? null : LunchItemType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(List.of());
        }
        List<LunchItem> lunchItemList = lunchItemsController.findLunchItems(lunchItemType);
        return ResponseEntity.ok(lunchItemMapper.lunchItemToLunchItemResponse(lunchItemList));
    }

    @PostMapping
    @Operation(summary = "Create a lunch item", description = "Create a lunch item")
    public ResponseEntity<LunchItemResponse> createLunchItem(@Valid @RequestBody LunchItemRequest lunchItemRequest) {
        LunchItem lunchItem = lunchItemsController.createLunchItem(lunchItemMapper.lunchItemRequestToLunchItem(lunchItemRequest));
        return ResponseEntity.ok(lunchItemMapper.lunchItemToLunchItemResponse(lunchItem));
    }

    @DeleteMapping("/{lunchId}")
    @Operation(summary = "Delete a lunch order", description = "Delete a lunch order")
    public ResponseEntity<Boolean> deleteLunch(@PathVariable Long lunchId){
        Boolean deleteLunchItem = lunchItemsController.editStatusLunchItem(lunchId);
        return ResponseEntity.ok(deleteLunchItem);
    }

    @PutMapping("/{lunchId}")
    @Operation(summary = "Edit a lunch order", description = "Edit a lunch order")
    public ResponseEntity<LunchItemResponse> editLunchItem(@PathVariable Long lunchId, @Valid @RequestBody LunchItemRequest lunchItemRequest) {
        LunchItem toLunchItem = lunchItemMapper.lunchItemRequestToLunchItem(lunchItemRequest);
        LunchItem lunchItem = lunchItemsController.editLunchItem(lunchId, toLunchItem);
        return ResponseEntity.ok(lunchItemMapper.lunchItemToLunchItemResponse(lunchItem));
    }
}
