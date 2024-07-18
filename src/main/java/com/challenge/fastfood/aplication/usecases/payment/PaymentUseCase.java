package com.challenge.fastfood.aplication.usecases.payment;


import com.challenge.fastfood.aplication.gateways.payment.PaymentProcess;
import com.challenge.fastfood.domain.entities.Payment;

public class PaymentUseCase {

    private final PaymentProcess paymentProcess;

    public PaymentUseCase(PaymentProcess payment) {
        this.paymentProcess = payment;
    }

    public Payment processPayment(Payment payment) {
        return paymentProcess.processPayment(payment);
    }
}
