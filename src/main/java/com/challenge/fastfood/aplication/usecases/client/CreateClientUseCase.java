package com.challenge.fastfood.aplication.usecases.client;


import com.challenge.fastfood.interfaces.client.FindClient;
import com.challenge.fastfood.interfaces.client.SaveClient;
import com.challenge.fastfood.config.exception.ClientException;
import com.challenge.fastfood.domain.entities.Client;

public class CreateClientUseCase  {

    private final SaveClient saveClient;
    private final FindClient findClient;

    public CreateClientUseCase(SaveClient saveClient, FindClient findClient) {
        this.saveClient = saveClient;
        this.findClient = findClient;
    }

    public Client createClient(Client client) {

        if (client.getName() == null || client.getCpf() == null || client.getEmail() == null) {
            throw new ClientException("Invalid client, name, cpf and email are required");
        }

        if (client.getName().length() > 100) {
            throw new ClientException("Invalid name, max 100 characters");
        }

        if (client.getCpf().length() > 14 || client.getCpf().length() < 11) {
            throw new ClientException("Invalid CPF, max 14 characters and min 11 characters");
        }

        if (client.getEmail().length() > 100) {
            throw new ClientException("Invalid email, max 100 characters");
        }

        Client clientFind = findClient.findClient(client.getCpf());

        if(clientFind != null ){
            if (clientFind.getCpf() != null || clientFind.getEmail() != null) {
                throw new ClientException("Client already exists");
            }
        }

        return saveClient.saveClient(client);
    }


}
