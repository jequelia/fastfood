package com.challenge.fastfood.framework.adapter;

import com.challenge.fastfood.entities.Payment;
import com.challenge.fastfood.framework.mapstruct.PaymentMapper;
import com.challenge.fastfood.framework.persistence.payment.PaymentEntity;
import com.challenge.fastfood.framework.persistence.payment.PaymentRepository;
import com.challenge.fastfood.interfaceadapters.interfaces.payment.PaymentAdapterInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class PaymentAdapterImpl implements PaymentAdapterInterface {

    private static final String BILLING_SERVICE_URL = "https://example.com/billing_service";
    private static final String PAYMENT_STATUS_URL = "https://example.com/payment_status/";
    private static final int STATUS_CODE_MOCK = 200;
    private static final String ID_TRANSACTION_MOCK = "ASDFWW562455SSVJIAJMXKDSFNJ157ASD54445";
    private static final String RESPONSE_BODY_STATUS_MOCK = "SUCCESS";
    private final ObjectMapper objectMapper;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentAdapterImpl(ObjectMapper objectMapper, PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.objectMapper = objectMapper;
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
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
    public String httpRequestPayment(Payment payment) throws JsonProcessingException {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BILLING_SERVICE_URL))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(payment)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar requisição para o serviço de pagamento", e);
        }

        if (STATUS_CODE_MOCK == 200) {
            return ID_TRANSACTION_MOCK ;// Supondo que o corpo da resposta contenha o ID da transação
        } else {
            throw new RuntimeException("Falha ao enviar o pedido para o serviço de pagamento. Status: " + response.statusCode());
        }
    }

    @Override
    public String checkPaymentStatus(String transactionId) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(PAYMENT_STATUS_URL + transactionId))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (STATUS_CODE_MOCK == 200) {
            return RESPONSE_BODY_STATUS_MOCK;
        } else {
            throw new RuntimeException("Falha ao consultar o status do pagamento.");
        }
    }

}
