package com.challenge.fastfood.interfaceadapters.gateways.payment;

import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.entities.Payment;
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
