package com.challenge.fastfood.framework.mapstruct;

import com.challenge.fastfood.entities.Payment;
import com.challenge.fastfood.framework.persistence.payment.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    Payment toPayment(PaymentEntity paymentEntity);


    PaymentEntity toPaymentEntity(Payment payment);
    @Mapping(source = "id", target = "transactionId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "pointOfInteraction.transactionData.qrCode", target = "qrCode")
    @Mapping(source = "pointOfInteraction.transactionData.ticketUrl", target = "ticketUrl")
    Payment toPaymentDomain(com.mercadopago.resources.payment.Payment payment);


}
