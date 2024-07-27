package com.challenge.fastfood.interfaceadapters.interfaces.payment;


import com.challenge.fastfood.entities.Payment;

import java.io.IOException;

public interface PaymentProcessGatewayInterface {

    String processPayment(Payment payment) throws IOException, InterruptedException;
    Payment findPaymentByLunchId(Long lunchId);
    String checkPaymentStatus(String transactionId) throws Exception;
    Payment savePayment(Payment payment);
}
