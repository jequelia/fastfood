package com.challenge.fastfood.framework.api;

import com.challenge.fastfood.interfaceadapters.controller.LunchController;
import com.challenge.fastfood.interfaceadapters.controller.request.LunchRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.LunchResponse;

import com.challenge.fastfood.interfaceadapters.interfaces.client.ClientAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunchItem.LunchItemAdapterInterface;
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

    private LunchController lunchController;
    private final ClientAdapterInterface clientAdapter;
    private final LunchAdapterInterface lunchAdapter;
    private final LunchItemAdapterInterface lunchItemAdapter;

    @PostMapping
    @Operation(summary = "Create a lunch order", description = "Create a lunch order")
    public ResponseEntity<LunchResponse> createLunch(@RequestBody LunchRequest lunchRequest) {

        lunchController = new LunchController(clientAdapter,lunchAdapter,lunchItemAdapter);
        LunchResponse createdLunch = lunchController.createLunch(lunchRequest);
        return ResponseEntity.ok(createdLunch);
    }

    @GetMapping
    @Operation(summary = "Get lunch orders", description = "Get lunch orders")
    public ResponseEntity<List<LunchResponse>> getAllLunchs() {
        lunchController = new LunchController(clientAdapter,lunchAdapter,lunchItemAdapter);

        List<LunchResponse> lunchs = lunchController.findLunchs();
        return ResponseEntity.ok(lunchs);
    }

    @GetMapping("/{lunchId}")
    @Operation(summary = "Get lunch order", description = "Get lunch order")
    public ResponseEntity<LunchResponse> getLunch(@PathVariable Long lunchId) {
        lunchController = new LunchController(clientAdapter,lunchAdapter,lunchItemAdapter);
        LunchResponse lunchResponse = lunchController.findLunchById(lunchId);
        return ResponseEntity.ok(lunchResponse);
    }

}
