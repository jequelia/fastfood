package com.challenge.fastfood.api;

import com.challenge.fastfood.controller.PaymentController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@Tag(name = "payment", description = "Payment Controller")
@RequiredArgsConstructor
public class PaymentApi {

    private final PaymentController paymentController;

    @PostMapping("/process/{lunchId}")
    public ResponseEntity<String> processPayment(@PathVariable Long lunchId) {
        String status = paymentController.processPayment(lunchId);
        return ResponseEntity.ok(status);
    }
}