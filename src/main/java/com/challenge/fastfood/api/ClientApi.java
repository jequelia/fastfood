package com.challenge.fastfood.api;

import com.challenge.fastfood.controller.ClientController;
import com.challenge.fastfood.api.request.ClientRequest;
import com.challenge.fastfood.api.response.ClientResponse;
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
public class ClientApi {

    private final ClientController clientController;


    @PostMapping
    @Operation(summary = "Create client", description = "Create a client")
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest clientRequest) {
        ClientResponse client = clientController.createClient(clientRequest);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    @Operation(summary = "Get client", description = "Get client")
    public ResponseEntity<ClientResponse> getClient(@RequestParam(required = false) String cpf) {
        ClientResponse clientResponse = clientController.findClient(cpf);
        return ResponseEntity.ok(clientResponse);
    }

}
