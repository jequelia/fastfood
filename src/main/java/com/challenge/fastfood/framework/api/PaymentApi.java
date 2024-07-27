package com.challenge.fastfood.framework.api;

import com.challenge.fastfood.interfaceadapters.controller.PaymentController;
import com.challenge.fastfood.interfaceadapters.controller.request.PaymentRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.PaymentResponse;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentAdapterInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Tag(name = "payment", description = "Payment Controller")
@RequiredArgsConstructor
public class PaymentApi {

    private PaymentController paymentController;
    private final PaymentAdapterInterface paymentAdapter;
    private final LunchAdapterInterface lunchAdapter;


    @PostMapping("/webhook")
    public ResponseEntity<String> webhook(@RequestBody PaymentRequest paymentRequest) {
        try {
            paymentController = new PaymentController(paymentAdapter,lunchAdapter);
            String status = paymentController.processPayment(paymentRequest);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/consult_payment_status/{numberLunch}")
    public ResponseEntity<PaymentResponse> consultPaymentStatus(@PathVariable Long numberLunch) {
        try {
            paymentController = new PaymentController(paymentAdapter,lunchAdapter);
            PaymentResponse status = paymentController.consultPaymentStatus(numberLunch);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}