package com.challenge.fastfood.aplication.gateways.payment;


import com.challenge.fastfood.domain.entities.Payment;

public interface PaymentProcess {

    Payment processPayment(Payment payment);

    Payment findPaymentByLunchId(Long lunchId);
}
