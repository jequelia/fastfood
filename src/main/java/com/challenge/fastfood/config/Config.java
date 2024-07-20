package com.challenge.fastfood.config;


import com.challenge.fastfood.controller.ClientController;
import com.challenge.fastfood.controller.LunchController;
import com.challenge.fastfood.controller.LunchItemsController;
import com.challenge.fastfood.controller.PaymentController;
import com.challenge.fastfood.interfaces.client.FindClient;
import com.challenge.fastfood.interfaces.client.SaveClient;
import com.challenge.fastfood.interfaces.lunch.FindLunch;
import com.challenge.fastfood.interfaces.lunch.SaveLunch;
import com.challenge.fastfood.interfaces.lunchItem.EditLunchItem;
import com.challenge.fastfood.interfaces.lunchItem.FindLunchItems;
import com.challenge.fastfood.interfaces.lunchItem.SaveLunchItem;
import com.challenge.fastfood.interfaces.payment.PaymentProcess;
import com.challenge.fastfood.aplication.usecases.client.CreateClientUseCase;
import com.challenge.fastfood.aplication.usecases.client.FindClientUseCase;
import com.challenge.fastfood.aplication.usecases.lunch.CreateLunchUseCase;
import com.challenge.fastfood.aplication.usecases.lunch.FindLunchUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.CreateLunchItemUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.EditLunchItemUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.FindLunchItemsUseCase;
import com.challenge.fastfood.aplication.usecases.payment.PaymentUseCase;
import com.challenge.fastfood.gateways.client.FindClientImpl;
import com.challenge.fastfood.gateways.client.SaveClientImpl;
import com.challenge.fastfood.gateways.lunch.FindLunchImpl;
import com.challenge.fastfood.gateways.lunch.SaveLunchImpl;
import com.challenge.fastfood.gateways.lunchItem.EditLunchItemsImpl;
import com.challenge.fastfood.gateways.lunchItem.FindLunchItemsImpl;
import com.challenge.fastfood.gateways.lunchItem.SaveLunchItemImpl;
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
    public CreateClientUseCase createClientUseCase(SaveClient saveClientAdapterPort, FindClient findClientAdapterPort) {
        return new CreateClientUseCase(saveClientAdapterPort, findClientAdapterPort);
    }

    @Bean
    public FindClientUseCase findClientUseCase(FindClient findClientAdapterPort) {
        return new FindClientUseCase(findClientAdapterPort);
    }

    @Bean
    public CreateLunchItemUseCase createLunchItemUseCase(SaveLunchItem saveLunchItemAdapterPort, FindLunchItems findLunchItemsAdapterPort) {
        return new CreateLunchItemUseCase(saveLunchItemAdapterPort,findLunchItemsAdapterPort);
    }

    @Bean
    public FindLunchItemsUseCase findLunchItemsUseCase(FindLunchItems findLunchItemsAdapterPort) {
        return new FindLunchItemsUseCase(findLunchItemsAdapterPort);
    }

    @Bean
    public CreateLunchUseCase createLunchUseCase(SaveLunch saveLunchAdapterPort) {
        return new CreateLunchUseCase(saveLunchAdapterPort);
    }

    @Bean
    public FindLunchUseCase findLunchUseCase(FindLunch findLunchAdapterPort) {
        return new FindLunchUseCase(findLunchAdapterPort);
    }

    @Bean
    public EditLunchItemUseCase editLunchItemUseCase(EditLunchItem deleteLunchItemAdapterPort, FindLunchItems findLunchItemsAdapter) {
        return new EditLunchItemUseCase(deleteLunchItemAdapterPort,findLunchItemsAdapter );
    }

    @Bean
    public PaymentUseCase paymentUseCase(PaymentProcess paymentAdapterPort) {
        return new PaymentUseCase(paymentAdapterPort);
    }



//    Adapters
    @Bean
    public FindClientImpl findClientAdapter(ClientRepository clientRepository, ClientMapper clientMapper) {
        return new FindClientImpl(clientRepository,clientMapper);
    }

    @Bean
    public SaveClientImpl saveClientAdapter(ClientRepository clientRepository, ClientMapper clientMapper) {
        return new SaveClientImpl(clientRepository,clientMapper);
    }

    @Bean
    public FindLunchImpl findLunchAdapter(LunchRepository lunchRepository, LunchMapper lunchMapper) {
        return new FindLunchImpl(lunchRepository,lunchMapper);
    }

    @Bean
    public SaveLunchImpl saveLunchAdapter(LunchRepository lunchRepository,
                                          ClientRepository clientRepository,
                                          LunchMapper lunchMapper,
                                          LunchItemMapper lunchItemMapper) {
        return new SaveLunchImpl(lunchRepository,clientRepository,lunchMapper,lunchItemMapper);
    }

    @Bean
    public EditLunchItemsImpl editLunchItemsAdapter(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        return new EditLunchItemsImpl(lunchItemsRepository,lunchItemMapper);
    }

    @Bean
    public FindLunchItemsImpl findLunchItemsAdapter(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        return new FindLunchItemsImpl(lunchItemsRepository,lunchItemMapper);
    }

    @Bean
    public SaveLunchItemImpl saveLunchItemAdapter(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        return new SaveLunchItemImpl(lunchItemsRepository,lunchItemMapper);
    }

    @Bean
    public ClientController createClientControllerAdapter(
            CreateClientUseCase clientUseCase,
            FindClientUseCase findClientUseCase) {
        return new ClientController(clientUseCase, findClientUseCase);
    }

    @Bean
    public LunchController createLunchControllerAdapter(
            CreateLunchUseCase createLunchUseCase,
            FindLunchUseCase findLunchUseCase,
            FindLunchItems findLunchItemsAdapterPort,
            FindClient findClientAdapterPort) {
        return new LunchController(createLunchUseCase, findLunchUseCase,findLunchItemsAdapterPort, findClientAdapterPort);
    }

    @Bean
    public LunchItemsController createLunchItemsControllerAdapter(
            CreateLunchItemUseCase createLunchItemUseCase,
            EditLunchItemUseCase editLunchItemUseCase,
            FindLunchItemsUseCase findLunchItemsUseCase) {
        return new LunchItemsController(createLunchItemUseCase, editLunchItemUseCase, findLunchItemsUseCase);
    }

    @Bean
    public PaymentController createPaymentControllerAdapter(PaymentUseCase paymentUseCase) {
        return new PaymentController(paymentUseCase);
    }




}
