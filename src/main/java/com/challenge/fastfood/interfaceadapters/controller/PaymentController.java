package com.challenge.fastfood.interfaceadapters.controller;

import com.challenge.fastfood.interfaceadapters.gateways.payment.PaymentImplGatewayImpl;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.usecases.payment.PaymentUseCase;
import com.challenge.fastfood.entities.Payment;

public class PaymentController {


    public String processPayment(Long lunchId) {
        PaymentProcessGatewayInterface paymentProcessGatewayInterface = new PaymentImplGatewayImpl();
        PaymentUseCase paymentUseCase = new PaymentUseCase(paymentProcessGatewayInterface);
        Payment payment = paymentUseCase.processPayment(lunchId);
        return payment.getStatus();
    }
}