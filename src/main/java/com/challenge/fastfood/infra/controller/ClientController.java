package com.challenge.fastfood.infra.controller;

import com.challenge.fastfood.aplication.usecases.client.CreateClientUseCase;
import com.challenge.fastfood.aplication.usecases.client.FindClientUseCase;
import com.challenge.fastfood.domain.entities.Client;
import com.challenge.fastfood.infra.controller.request.ClientRequest;
import com.challenge.fastfood.infra.controller.response.ClientResponse;
import com.challenge.fastfood.infra.gateways.mapstruct.ClientMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@Tag(name = "Client", description = "Client Controller")
@RequiredArgsConstructor
public class ClientController {

    private final ClientMapper clientMapper;

    private final CreateClientUseCase createClient;

    private final FindClientUseCase findClient;


    @PostMapping
    @Operation(summary = "Create client", description = "Create a client")
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest clientRequest) {
        Client client = createClient.createClient(clientMapper.clientRequestToClient(clientRequest));
        return ResponseEntity.ok(clientMapper.clientToClientResponse(client));
    }

    @GetMapping
    @Operation(summary = "Get client", description = "Get client")
    public ResponseEntity<ClientResponse> getClient(@RequestParam(required = false) String cpf) {
        Client client = findClient.findClient(cpf);
        return ResponseEntity.ok(clientMapper.clientToClientResponse(client));
    }

}
