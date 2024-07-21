package com.challenge.fastfood.api;

import com.challenge.fastfood.controller.LunchController;
import com.challenge.fastfood.api.request.LunchRequest;
import com.challenge.fastfood.api.response.LunchResponse;
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

    @PostMapping
    @Operation(summary = "Create a lunch order", description = "Create a lunch order")
    public ResponseEntity<LunchResponse> createLunch(@RequestBody LunchRequest lunchRequest) {
        LunchResponse createdLunch = lunchController.createLunch(lunchRequest);
        return ResponseEntity.ok(createdLunch);
    }

    @GetMapping
    @Operation(summary = "Get lunch orders", description = "Get lunch orders")
    public ResponseEntity<List<LunchResponse>> getAllLunchs() {
        List<LunchResponse> lunchs = lunchController.findLunchs();
        return ResponseEntity.ok(lunchs);
    }

    @GetMapping("/{lunchId}")
    @Operation(summary = "Get lunch order", description = "Get lunch order")
    public ResponseEntity<LunchResponse> getLunch(@PathVariable Long lunchId) {
        LunchResponse lunchResponse = lunchController.findLunchById(lunchId);
        return ResponseEntity.ok(lunchResponse);
    }

}
