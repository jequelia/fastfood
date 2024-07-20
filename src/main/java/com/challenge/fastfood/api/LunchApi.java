package com.challenge.fastfood.api;

import com.challenge.fastfood.controller.LunchController;
import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.api.request.LunchRequest;
import com.challenge.fastfood.api.response.LunchResponse;
import com.challenge.fastfood.infra.mapstruct.LunchMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lunch")
@Tag(name = "Lunch", description = "Lunch Controller")
@RequiredArgsConstructor
public class LunchApi {

    private final LunchController lunchController;
    private final LunchMapper lunchMapper;

    @PostMapping
    @Operation(summary = "Create a lunch order", description = "Create a lunch order")
    public ResponseEntity<LunchResponse> createLunch(@RequestBody LunchRequest lunchRequest) {
        Lunch createdLunch = lunchController.createLunch(lunchRequest);
        return ResponseEntity.ok(lunchMapper.lunchToLunchResponse(createdLunch));
    }

    @GetMapping
    @Operation(summary = "Get lunch orders", description = "Get lunch orders")
    public ResponseEntity<List<LunchResponse>> getAllLunchs() {
        List<Lunch> lunchs = lunchController.findLunchs();
        return ResponseEntity.ok(lunchMapper.lunchsToLunchsResponse(lunchs));
    }

    @GetMapping("/{lunchId}")
    @Operation(summary = "Get lunch order", description = "Get lunch order")
    public ResponseEntity<LunchResponse> getLunch(@PathVariable Long lunchId) {
        Lunch lunch = lunchController.findLunchById(lunchId);
        return ResponseEntity.ok(lunchMapper.lunchToLunchResponse(lunch));
    }

}
