package com.challenge.fastfood.aplication.usecases.payment;


import com.challenge.fastfood.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.domain.entities.Payment;

public class PaymentUseCase {

    private final PaymentProcessGatewayInterface paymentProcessGatewayInterface;

    public PaymentUseCase(PaymentProcessGatewayInterface payment) {
        this.paymentProcessGatewayInterface = payment;
    }

    public Payment processPayment(Payment payment) {
        return paymentProcessGatewayInterface.processPayment(payment);
    }
}
