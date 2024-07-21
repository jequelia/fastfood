package com.challenge.fastfood.config;


import com.challenge.fastfood.controller.ClientController;
import com.challenge.fastfood.controller.LunchController;
import com.challenge.fastfood.controller.LunchItemsController;
import com.challenge.fastfood.controller.PaymentController;
import com.challenge.fastfood.interfaces.client.FindClientGatewayInterface;
import com.challenge.fastfood.interfaces.client.SaveClientGatewayInterface;
import com.challenge.fastfood.interfaces.lunch.FindLunchGatewayInterface;
import com.challenge.fastfood.interfaces.lunch.SaveLunchGatewayInterface;
import com.challenge.fastfood.interfaces.lunchItem.EditLunchItemGatewayInterface;
import com.challenge.fastfood.interfaces.lunchItem.FindLunchItemsGatewayInterface;
import com.challenge.fastfood.interfaces.lunchItem.SaveLunchItemGatewayInterface;
import com.challenge.fastfood.interfaces.payment.PaymentProcessGatewayInterface;
import com.challenge.fastfood.domain.usecases.client.CreateClientUseCase;
import com.challenge.fastfood.domain.usecases.client.FindClientUseCase;
import com.challenge.fastfood.domain.usecases.lunch.CreateLunchUseCase;
import com.challenge.fastfood.domain.usecases.lunch.FindLunchUseCase;
import com.challenge.fastfood.domain.usecases.lunchItem.CreateLunchItemUseCase;
import com.challenge.fastfood.domain.usecases.lunchItem.EditLunchItemUseCase;
import com.challenge.fastfood.domain.usecases.lunchItem.FindLunchItemsUseCase;
import com.challenge.fastfood.domain.usecases.payment.PaymentUseCase;
import com.challenge.fastfood.gateways.client.FindClientGatewayImpl;
import com.challenge.fastfood.gateways.client.SaveClientGatewayImpl;
import com.challenge.fastfood.gateways.lunch.FindLunchGatewayImpl;
import com.challenge.fastfood.gateways.lunch.SaveLunchGatewayImpl;
import com.challenge.fastfood.gateways.lunchItem.EditLunchItemsGatewayImpl;
import com.challenge.fastfood.gateways.lunchItem.FindLunchItemsGatewayImpl;
import com.challenge.fastfood.gateways.lunchItem.SaveLunchItemGatewayImpl;
import com.challenge.fastfood.infra.mapstruct.ClientMapper;
import com.challenge.fastfood.infra.mapstruct.LunchItemMapper;
import com.challenge.fastfood.infra.mapstruct.LunchMapper;
import com.challenge.fastfood.infra.persistence.client.ClientRepository;
import com.challenge.fastfood.infra.persistence.lunch.LunchRepository;
import com.challenge.fastfood.infra.persistence.lunchItem.LunchItemsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {


//    usecases

    @Bean
    public CreateClientUseCase createClientUseCase(SaveClientGatewayInterface saveClientGatewayInterface, FindClientGatewayInterface findClientGatewayInterface) {
        return new CreateClientUseCase(saveClientGatewayInterface, findClientGatewayInterface);
    }

    @Bean
    public FindClientUseCase findClientUseCase(FindClientGatewayInterface findClientGatewayInterface) {
        return new FindClientUseCase(findClientGatewayInterface);
    }

    @Bean
    public CreateLunchItemUseCase createLunchItemUseCase(SaveLunchItemGatewayInterface saveLunchItemGatewayInterface, FindLunchItemsGatewayInterface findLunchItemsGatewayInterface) {
        return new CreateLunchItemUseCase(saveLunchItemGatewayInterface, findLunchItemsGatewayInterface);
    }

    @Bean
    public FindLunchItemsUseCase findLunchItemsUseCase(FindLunchItemsGatewayInterface findLunchItemsGatewayInterface) {
        return new FindLunchItemsUseCase(findLunchItemsGatewayInterface);
    }

    @Bean
    public CreateLunchUseCase createLunchUseCase(SaveLunchGatewayInterface saveLunchGatewayInterface) {
        return new CreateLunchUseCase(saveLunchGatewayInterface);
    }

    @Bean
    public FindLunchUseCase findLunchUseCase(FindLunchGatewayInterface findLunchGatewayInterface) {
        return new FindLunchUseCase(findLunchGatewayInterface);
    }

    @Bean
    public EditLunchItemUseCase editLunchItemUseCase(EditLunchItemGatewayInterface deleteLunchItemAdapterPort, FindLunchItemsGatewayInterface findLunchItemsGatewayInterface) {
        return new EditLunchItemUseCase(deleteLunchItemAdapterPort, findLunchItemsGatewayInterface);
    }

    @Bean
    public PaymentUseCase paymentUseCase(PaymentProcessGatewayInterface paymentAdapterPort) {
        return new PaymentUseCase(paymentAdapterPort);
    }



//    Adapters
    @Bean
    public FindClientGatewayImpl findClientAdapter(ClientRepository clientRepository, ClientMapper clientMapper) {
        return new FindClientGatewayImpl(clientRepository,clientMapper);
    }

    @Bean
    public SaveClientGatewayImpl saveClientAdapter(ClientRepository clientRepository, ClientMapper clientMapper) {
        return new SaveClientGatewayImpl(clientRepository,clientMapper);
    }

    @Bean
    public FindLunchGatewayImpl findLunchAdapter(LunchRepository lunchRepository, LunchMapper lunchMapper) {
        return new FindLunchGatewayImpl(lunchRepository,lunchMapper);
    }

    @Bean
    public SaveLunchGatewayImpl saveLunchAdapter(LunchRepository lunchRepository,
                                                 ClientRepository clientRepository,
                                                 LunchMapper lunchMapper,
                                                 LunchItemMapper lunchItemMapper) {
        return new SaveLunchGatewayImpl(lunchRepository,clientRepository,lunchMapper,lunchItemMapper);
    }

    @Bean
    public EditLunchItemsGatewayImpl editLunchItemsAdapter(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        return new EditLunchItemsGatewayImpl(lunchItemsRepository,lunchItemMapper);
    }

    @Bean
    public FindLunchItemsGatewayImpl findLunchItemsAdapter(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        return new FindLunchItemsGatewayImpl(lunchItemsRepository,lunchItemMapper);
    }

    @Bean
    public SaveLunchItemGatewayImpl saveLunchItemAdapter(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        return new SaveLunchItemGatewayImpl(lunchItemsRepository,lunchItemMapper);
    }

    @Bean
    public ClientController createClientControllerAdapter(
            CreateClientUseCase clientUseCase,
            FindClientUseCase findClientUseCase,ClientMapper clientMapper) {
        return new ClientController(clientUseCase, findClientUseCase, clientMapper);
    }

    @Bean
    public LunchController createLunchControllerAdapter(
            CreateLunchUseCase createLunchUseCase,
            FindLunchUseCase findLunchUseCase,
            FindLunchItemsGatewayInterface findLunchItemsGatewayInterface,
            FindClientGatewayInterface findClientGatewayInterface,
            LunchMapper lunchMapper) {
        return new LunchController(createLunchUseCase, findLunchUseCase, findLunchItemsGatewayInterface, findClientGatewayInterface,lunchMapper);
    }

    @Bean
    public LunchItemsController createLunchItemsControllerAdapter(
            CreateLunchItemUseCase createLunchItemUseCase,
            EditLunchItemUseCase editLunchItemUseCase,
            FindLunchItemsUseCase findLunchItemsUseCase,
            LunchItemMapper lunchItemMapper) {
        return new LunchItemsController(createLunchItemUseCase, editLunchItemUseCase, findLunchItemsUseCase,lunchItemMapper);
    }

    @Bean
    public PaymentController createPaymentControllerAdapter(PaymentUseCase paymentUseCase) {
        return new PaymentController(paymentUseCase);
    }




}
