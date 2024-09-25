package com.challenge.fastfood.framework.adapter;

import com.challenge.fastfood.config.exception.PaymentException;
import com.challenge.fastfood.entities.Payment;
import com.challenge.fastfood.entities.PaymentProviderEnum;
import com.challenge.fastfood.framework.mapstruct.PaymentMapper;
import com.challenge.fastfood.framework.persistence.payment.PaymentEntity;
import com.challenge.fastfood.framework.persistence.payment.PaymentRepository;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentAdapterInterface;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

@Component
@Log4j2
@RequiredArgsConstructor
public class PaymentAdapterImpl implements PaymentAdapterInterface {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;


    @Value("${dataprovider.payment.mercado-pago.access-token:}")
    private String accessToken;

    @Value("${dataprovider.payment.mercado-pago.default-payer-email:}")
    private String defaultPayerEmail;

    @Value("${api.url:}")
    private String apiUrl;



    public PaymentProviderEnum getProviderCode() {
        return PaymentProviderEnum.MERCADO_PAGO;
    }

    @Override
    public Payment savePayment(Payment payment) {
        PaymentEntity paymentEntity = paymentMapper.toPaymentEntity(payment);
        PaymentEntity save = paymentRepository.save(paymentEntity);
        return paymentMapper.toPayment(save);
    }

    @Override
    public Payment findByNumberLunch(Long numberLunch) {
        PaymentEntity payment = paymentRepository.findByNumberLunch(numberLunch);
        return paymentMapper.toPayment(payment);
    }

    @Override
    public Payment findPaymentByTransactionId(String transactionId) {
        PaymentEntity payment = paymentRepository.findPaymentByTransactionId(transactionId);
        return paymentMapper.toPayment(payment);
    }

    @Override
    public Payment httpRequestPayment(Payment payment)  {

        MercadoPagoConfig.setAccessToken(accessToken);
        MercadoPagoConfig.setLoggingLevel(Level.FINEST);

        PaymentClient paymentClient = new PaymentClient();

        Map<String, String> customHeaders = new HashMap<String, String>();
        customHeaders.put("x-idempotency-key", payment.getNumberLunch().toString());

        MPRequestOptions.builder()
                //.accessToken("custom_access_token")
                .connectionRequestTimeout(2000)
                .connectionTimeout(2000)
                .socketTimeout(2000)
                .customHeaders(customHeaders)
                .build();

        String payerEmail = null;

        if( payment.getEmailClient() != null) {
            payerEmail = payment.getEmailClient();
        } else {
            payerEmail = this.defaultPayerEmail;
        }


        String notificationUrl = null;


        if(this.apiUrl != null && !this.apiUrl.isBlank()) {
            notificationUrl = apiUrl + "/payment/webhook";
        }

        log.info("notificationUrl: {}", notificationUrl);


        PaymentCreateRequest createRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(BigDecimal.valueOf(payment.getPriceTotal()))
                        .externalReference(payment.getNumberLunch().toString())
                        .description("fiap-techfood")
                        .installments(1)
                        .paymentMethodId("pix")
                        .notificationUrl(notificationUrl)
                        .payer(PaymentPayerRequest.builder().email(payerEmail).build())
                        .build();

        try {
            com.mercadopago.resources.payment.Payment paymentResponse = paymentClient.create(createRequest);
            if(PaymentStatus.PENDING.equals(paymentResponse.getStatus())) {
                Payment paymentDomain = this.paymentMapper.toPaymentDomain(paymentResponse);
                return paymentDomain;
            } else {
                throw new PaymentException("Falha para criar pagamento");
            }

        }catch (MPException e) {
            log.error("Error generate payment {}", e.getMessage());
            log.error(e.getMessage());
            throw new PaymentException("Falha para criar pagamento");
        } catch (MPApiException e) {
            log.error("Error generate payment {}", e.getApiResponse().getContent());
            log.error("Error map {}", e.getApiResponse().getHeaders());
            log.error("Error status code {}", e.getApiResponse().getStatusCode());
            throw new PaymentException("Falha para criar pagamento");
        }
    }

    @Override
    public String checkPaymentStatus(String transactionId) throws Exception {
        MercadoPagoConfig.setAccessToken(accessToken);
        MercadoPagoConfig.setLoggingLevel(Level.WARNING);

        PaymentClient paymentClient = new PaymentClient();



        MPRequestOptions.builder()
                .connectionRequestTimeout(2000)
                .connectionTimeout(2000)
                .socketTimeout(2000)
                .build();


        try {
            Long paymentMpId = Long.parseLong(transactionId);
            com.mercadopago.resources.payment.Payment paymentResponse = paymentClient.get(paymentMpId);

            Payment paymentDomain = this.paymentMapper.toPaymentDomain(paymentResponse);
            return paymentDomain.getStatus();
        } catch (NumberFormatException e) {
            log.error("Error to get payment: Invalid id given. Id: " + transactionId);
            throw new PaymentException("Error to get payment. Invalid Long Id: " + transactionId);
        } catch (MPException | MPApiException e) {
            log.error("Error generate payment {}", e.getMessage());
            log.error(e.getMessage());
            throw new PaymentException("Error to get payment.");
        }
    }

}
