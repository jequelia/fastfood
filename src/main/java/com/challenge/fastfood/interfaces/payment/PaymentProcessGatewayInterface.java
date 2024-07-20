package com.challenge.fastfood.interfaces.payment;


import com.challenge.fastfood.domain.entities.Payment;

public interface PaymentProcessGatewayInterface {

    Payment processPayment(Payment payment);

    Payment findPaymentByLunchId(Long lunchId);
}
