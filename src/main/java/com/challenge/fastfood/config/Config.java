package com.challenge.fastfood.config;


import com.challenge.fastfood.aplication.gateways.client.FindClient;
import com.challenge.fastfood.aplication.gateways.client.SaveClient;
import com.challenge.fastfood.aplication.gateways.lunch.FindLunch;
import com.challenge.fastfood.aplication.gateways.lunch.SaveLunch;
import com.challenge.fastfood.aplication.gateways.lunchItem.EditLunchItem;
import com.challenge.fastfood.aplication.gateways.lunchItem.FindLunchItems;
import com.challenge.fastfood.aplication.gateways.lunchItem.SaveLunchItem;
import com.challenge.fastfood.aplication.gateways.payment.PaymentProcess;
import com.challenge.fastfood.aplication.usecases.client.CreateClientUseCase;
import com.challenge.fastfood.aplication.usecases.client.FindClientUseCase;
import com.challenge.fastfood.aplication.usecases.lunch.CreateLunchUseCase;
import com.challenge.fastfood.aplication.usecases.lunch.FindLunchUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.CreateLunchItemUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.EditLunchItemUseCase;
import com.challenge.fastfood.aplication.usecases.lunchItem.FindLunchItemsUseCase;
import com.challenge.fastfood.aplication.usecases.payment.PaymentUseCase;
import com.challenge.fastfood.infra.gateways.client.FindClientImpl;
import com.challenge.fastfood.infra.gateways.client.SaveClientImpl;
import com.challenge.fastfood.infra.gateways.lunch.FindLunchImpl;
import com.challenge.fastfood.infra.gateways.lunch.SaveLunchImpl;
import com.challenge.fastfood.infra.gateways.lunchItem.EditLunchItemsImpl;
import com.challenge.fastfood.infra.gateways.lunchItem.FindLunchItemsImpl;
import com.challenge.fastfood.infra.gateways.lunchItem.SaveLunchItemImpl;
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
                                          LunchItemsRepository lunchItemsRepository,
                                          ClientRepository clientRepository,
                                          LunchMapper lunchMapper,
                                          LunchItemMapper lunchItemMapper,
                                          ClientMapper clientMapper) {
        return new SaveLunchImpl(lunchRepository,lunchItemsRepository,clientRepository,lunchMapper,lunchItemMapper,clientMapper);
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




}
