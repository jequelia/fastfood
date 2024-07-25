package com.challenge.fastfood.usecases.payment;


import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.entities.Payment;

public class PaymentUseCase {

    private final PaymentProcessGatewayInterface paymentProcessGatewayInterface;

    public PaymentUseCase(PaymentProcessGatewayInterface payment) {
        this.paymentProcessGatewayInterface = payment;
    }

    public Payment processPayment(Long lunchId) {
        Payment payment = paymentProcessGatewayInterface.findPaymentByLunchId(lunchId);
        return paymentProcessGatewayInterface.processPayment(payment);
    }
}
