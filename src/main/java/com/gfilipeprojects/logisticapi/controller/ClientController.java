package com.gfilipeprojects.logisticapi.controller;

import com.gfilipeprojects.logisticapi.domain.model.Client;
import com.gfilipeprojects.logisticapi.repository.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> getClientList() {
        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/{clientId}")
     public ResponseEntity<Client> updateClient(@PathVariable Long clientId, @RequestBody Client client) {
          if (!clientRepository.existsById(clientId)) {
              return ResponseEntity.notFound().build();
          }
          client.setId(clientId);
          return ResponseEntity.ok(clientRepository.save(client));
     }
}
