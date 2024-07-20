package com.challenge.fastfood.gateways.payment;

import com.challenge.fastfood.interfaces.payment.PaymentProcess;
import com.challenge.fastfood.domain.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentImpl implements PaymentProcess {

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
