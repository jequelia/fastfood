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
import com.challenge.fastfood.infra.gateways.client.FindClientAdapter;
import com.challenge.fastfood.infra.gateways.client.SaveClientAdapter;
import com.challenge.fastfood.infra.gateways.lunch.FindLunchAdapter;
import com.challenge.fastfood.infra.gateways.lunch.SaveLunchAdapter;
import com.challenge.fastfood.infra.gateways.lunchItem.EditLunchItemsAdapter;
import com.challenge.fastfood.infra.gateways.lunchItem.FindLunchItemsAdapter;
import com.challenge.fastfood.infra.gateways.lunchItem.SaveLunchItemAdapter;
import com.challenge.fastfood.infra.gateways.mapstruct.ClientMapper;
import com.challenge.fastfood.infra.gateways.mapstruct.LunchItemMapper;
import com.challenge.fastfood.infra.gateways.mapstruct.LunchMapper;
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
    public FindClientAdapter findClientAdapter(ClientRepository clientRepository, ClientMapper clientMapper) {
        return new FindClientAdapter(clientRepository,clientMapper);
    }

    @Bean
    public SaveClientAdapter saveClientAdapter(ClientRepository clientRepository, ClientMapper clientMapper) {
        return new SaveClientAdapter(clientRepository,clientMapper);
    }

    @Bean
    public FindLunchAdapter findLunchAdapter(LunchRepository lunchRepository, LunchMapper lunchMapper) {
        return new FindLunchAdapter(lunchRepository,lunchMapper);
    }

    @Bean
    public SaveLunchAdapter saveLunchAdapter(LunchRepository lunchRepository,
                                             LunchItemsRepository lunchItemsRepository,
                                             ClientRepository clientRepository,
                                             LunchMapper lunchMapper,
                                             LunchItemMapper lunchItemMapper,
                                             ClientMapper clientMapper) {
        return new SaveLunchAdapter(lunchRepository,lunchItemsRepository,clientRepository,lunchMapper,lunchItemMapper,clientMapper);
    }

    @Bean
    public EditLunchItemsAdapter editLunchItemsAdapter(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        return new EditLunchItemsAdapter(lunchItemsRepository,lunchItemMapper);
    }

    @Bean
    public FindLunchItemsAdapter findLunchItemsAdapter(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        return new FindLunchItemsAdapter(lunchItemsRepository,lunchItemMapper);
    }

    @Bean
    public SaveLunchItemAdapter saveLunchItemAdapter(LunchItemsRepository lunchItemsRepository, LunchItemMapper lunchItemMapper) {
        return new SaveLunchItemAdapter(lunchItemsRepository,lunchItemMapper);
    }




}
