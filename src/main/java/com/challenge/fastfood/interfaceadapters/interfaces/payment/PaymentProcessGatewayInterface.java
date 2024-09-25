package com.challenge.fastfood.interfaceadapters.interfaces.payment;


import com.challenge.fastfood.entities.Payment;

import java.io.IOException;

public interface PaymentProcessGatewayInterface {

    Payment processPayment(Payment payment) throws IOException, InterruptedException;
    Payment findPaymentByLunchId(Long lunchId);
    Payment findPaymentByTransactionId(String transactionId);
    String checkPaymentStatus(String transactionId) throws Exception;
    Payment savePayment(Payment payment);
}
