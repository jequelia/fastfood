package com.challenge.fastfood.interfaceadapters.controller;

import com.challenge.fastfood.interfaceadapters.controller.request.PaymentRequest;
import com.challenge.fastfood.interfaceadapters.controller.response.PaymentResponse;
import com.challenge.fastfood.interfaceadapters.gateways.lunch.FindLunchGatewayImpl;
import com.challenge.fastfood.interfaceadapters.gateways.payment.PaymentImplGatewayImpl;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.lunch.LunchAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentAdapterInterface;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.interfaceadapters.presenter.PaymentPresenter;
import com.challenge.fastfood.usecases.payment.PaymentUseCase;
import com.challenge.fastfood.entities.Payment;

public class PaymentController {

    private final PaymentAdapterInterface paymentAdapter;
    private final LunchAdapterInterface lunchAdapter;

    public PaymentController(PaymentAdapterInterface paymentAdapter, LunchAdapterInterface lunchAdapter) {
        this.paymentAdapter = paymentAdapter;
        this.lunchAdapter = lunchAdapter;
    }

    public String processPayment(PaymentRequest paymentRequest) {
        PaymentProcessGatewayInterface paymentProcessGatewayInterface = new PaymentImplGatewayImpl(paymentAdapter);
        FindLunchGatewayInterface findLunchGatewayInterface = new FindLunchGatewayImpl(lunchAdapter);

        PaymentUseCase paymentUseCase = new PaymentUseCase(paymentProcessGatewayInterface,findLunchGatewayInterface);
        PaymentPresenter paymentPresenter = new PaymentPresenter();
        Payment toPayment = paymentPresenter.toPayment(paymentRequest);
        Payment payment = paymentUseCase.processPayment(toPayment);
        return payment.getStatus();
    }

    public PaymentResponse consultPaymentStatus(Long numberLunch) throws Exception {
        PaymentProcessGatewayInterface paymentProcessGatewayInterface = new PaymentImplGatewayImpl(paymentAdapter);
        FindLunchGatewayInterface findLunchGatewayInterface = new FindLunchGatewayImpl(lunchAdapter);

        PaymentUseCase paymentUseCase = new PaymentUseCase(paymentProcessGatewayInterface,findLunchGatewayInterface);
        Payment payment = paymentUseCase.checkPaymentStatus(numberLunch);
        PaymentPresenter paymentPresenter = new PaymentPresenter();
        return paymentPresenter.toPaymentResponse(payment);

    }

}