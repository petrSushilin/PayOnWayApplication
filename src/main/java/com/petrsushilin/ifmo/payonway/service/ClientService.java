package com.petrsushilin.ifmo.payonway.service;

import com.petrsushilin.ifmo.payonway.entity.Client;
import com.petrsushilin.ifmo.payonway.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository repository;

    public Client getAccount(Long clientId) {
        return repository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client is not exists"));
    }

    public void checkAccountAvailabilityAndCreate(Client client) {
        String accountLogin = client.getLogin();
        Optional<Client> clientByLogin = repository.findClientByLogin(accountLogin);

        if (clientByLogin.isPresent())
            throw new IllegalArgumentException("This login taken");
        if (accountLogin.length() < 4)
            throw new IllegalArgumentException("Your login is too short");
        if (accountLogin.length() > 16)
            throw new IllegalArgumentException("Your login is too long");
        if (accountLogin.matches("^a-zA-Z.0-9"))
            throw new IllegalArgumentException("Your login should contain letters and dots only");
        if (repository.existsById(client.getId()))
            throw new IllegalArgumentException("Login already taken");

        repository.save(client);
    }

    @Transactional
    public void updateClientName(Long clientId, String name) {
        Client client = repository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client is not exists"));

        if (name != null && name.length() > 1 && !Objects.equals(client.getName(), name))
            client.setName(name);

        repository.save(client);
    }

    public void deleteAccount(Long clientId) {
        if (repository.existsById(clientId))
            repository.deleteById(clientId);
        throw new IllegalArgumentException("Client is not exists");
    }

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }
}
