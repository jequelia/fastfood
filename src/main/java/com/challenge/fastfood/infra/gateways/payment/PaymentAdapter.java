package com.challenge.fastfood.infra.gateways.payment;

import com.challenge.fastfood.aplication.gateways.payment.PaymentProcess;
import com.challenge.fastfood.domain.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentAdapter implements PaymentProcess {

    @Override
    public Payment processPayment(Payment payment) {
        payment.setPaymentStatus("PROCESSED");
        return payment;
    }

    @Override
    public Payment findPaymentByLunchId(Long lunchId) {
        return new Payment(lunchId);
    }
}
