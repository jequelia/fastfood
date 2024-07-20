package com.challenge.fastfood.gateways.payment;

import com.challenge.fastfood.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.domain.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentImplGatewayImpl implements PaymentProcessGatewayInterface {

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
