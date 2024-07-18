package com.challenge.fastfood.infra.gateways.mapstruct;

import com.challenge.fastfood.domain.entities.Payment;
import com.challenge.fastfood.infra.controller.request.PaymentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

//    Payment toPayment(PaymentRequest paymentRequest);

//    PaymentRequest toPaymentRequest(Payment payment);
}
