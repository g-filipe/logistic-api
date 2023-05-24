package com.gfilipeprojects.logisticapi.controller;

import com.gfilipeprojects.logisticapi.domain.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping(value = "/clients")
    public List<Client> getClientList() {
        return manager.createQuery("from Client", Client.class)
                .getResultList();
    }
}
