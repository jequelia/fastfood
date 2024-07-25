package com.challenge.fastfood.interfaceadapters.interfaces.payment;


import com.challenge.fastfood.entities.Payment;

public interface PaymentProcessGatewayInterface {

    Payment processPayment(Payment payment);

    Payment findPaymentByLunchId(Long lunchId);
}
