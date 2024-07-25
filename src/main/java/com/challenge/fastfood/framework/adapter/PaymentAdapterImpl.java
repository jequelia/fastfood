package com.challenge.fastfood.framework.adapter;

import com.challenge.fastfood.entities.Payment;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentAdapterInterface;

public class PaymentAdapterImpl implements PaymentAdapterInterface {

    @Override
    public Payment processPayment(Payment payment) {
        return null;
    }

    @Override
    public Payment findPaymentByLunchId(Long lunchId) {
        return null;
    }
}
