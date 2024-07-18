package com.challenge.fastfood.infra.controller;

import com.challenge.fastfood.aplication.usecases.lunch.CreateLunchUseCase;
import com.challenge.fastfood.aplication.usecases.lunch.FindLunchUseCase;
import com.challenge.fastfood.domain.entities.Lunch;
import com.challenge.fastfood.infra.controller.request.LunchRequest;
import com.challenge.fastfood.infra.controller.response.LunchResponse;
import com.challenge.fastfood.infra.gateways.mapstruct.LunchMapper;
import com.challenge.fastfood.infra.persistence.lunch.LunchRepository;
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
public class LunchController {

    private final LunchMapper lunchMapper;

    private final CreateLunchUseCase createLunchUseCase;
    private final FindLunchUseCase findLunchUseCase;

    @PostMapping
    @Operation(summary = "Create a lunch order", description = "Create a lunch order")
    public ResponseEntity<LunchResponse> createLunch(@RequestBody LunchRequest lunchRequest) {
        Lunch createdLunch = createLunchUseCase.createLunch(lunchRequest);
        Lunch savedLunch = createLunchUseCase.saveLunch(createdLunch);
        return ResponseEntity.ok(lunchMapper.lunchToLunchResponse(savedLunch));
    }

    @GetMapping
    @Operation(summary = "Get lunch orders", description = "Get lunch orders")
    public ResponseEntity<List<LunchResponse>> getAllLunchs() {
        List<Lunch> lunchs = findLunchUseCase.findLunchs();
        return ResponseEntity.ok(lunchMapper.lunchsToLunchsResponse(lunchs));
    }

    @GetMapping("/{lunchId}")
    @Operation(summary = "Get lunch order", description = "Get lunch order")
    public ResponseEntity<LunchResponse> getLunch(@PathVariable Long lunchId) {
        Lunch lunch = findLunchUseCase.findLunchById(lunchId);
        return ResponseEntity.ok(lunchMapper.lunchToLunchResponse(lunch));
    }

}
