package com.challenge.fastfood.api;

import com.challenge.fastfood.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.domain.entities.Payment;
import com.challenge.fastfood.infra.mapstruct.PaymentMapper;
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

    private final PaymentProcessGatewayInterface paymentAdapterPort;
    private final PaymentMapper paymentMapper;

    @PostMapping("/process/{lunchId}")
    public ResponseEntity<String> processPayment(@PathVariable Long lunchId) {
        Payment payment = paymentAdapterPort.findPaymentByLunchId(lunchId);
        Payment processed = paymentAdapterPort.processPayment(payment);
        return ResponseEntity.ok(processed.getStatus());
    }
}