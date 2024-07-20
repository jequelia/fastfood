package com.challenge.fastfood.controller;

import com.challenge.fastfood.aplication.usecases.payment.PaymentUseCase;
import com.challenge.fastfood.domain.entities.Payment;

public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    public PaymentController(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    public Payment processPayment(Payment payment) {
        return paymentUseCase.processPayment(payment);
    }
}