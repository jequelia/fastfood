package com.challenge.fastfood.controller;

import com.challenge.fastfood.aplication.usecases.payment.PaymentUseCase;
import com.challenge.fastfood.domain.entities.Payment;

public class PaymentController {

    private final PaymentUseCase paymentUseCase;


    public PaymentController(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    public String processPayment(Long lunchId) {

        Payment payment = paymentUseCase.processPayment(lunchId);

        return payment.getStatus();
    }
}