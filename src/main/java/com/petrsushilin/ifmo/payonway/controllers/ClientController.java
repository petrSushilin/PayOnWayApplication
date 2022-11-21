package com.petrsushilin.ifmo.payonway.controllers;

import com.petrsushilin.ifmo.payonway.entity.Client;
import com.petrsushilin.ifmo.payonway.service.ClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "client")
public class ClientController {
    private final ClientService clientService;

    @PostMapping(path = "/register_client")
    public String registerClient(@RequestBody Client client){
        clientService.checkAccountAvailabilityAndCreate(client);
        return "redirect:/";
    }

    @GetMapping(path = "/get/id={clientId}")
    public Client getClient(@PathVariable Long clientId) {
        return clientService.getAccount(clientId);
    }

    @PutMapping(path = "/change/id={clientId}/name")
    public void updateClientName(@PathVariable Long clientId,
                             @RequestParam String name) {
        clientService.updateClientName(clientId, name);
    }

    @DeleteMapping(path = "/delete/id={clientId}")
    public void deleteClient(@PathVariable Long clientId) {
        clientService.deleteAccount(clientId);
    }

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
}
