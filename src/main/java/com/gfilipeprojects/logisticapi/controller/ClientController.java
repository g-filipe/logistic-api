package com.gfilipeprojects.logisticapi.controller;

import com.gfilipeprojects.logisticapi.domain.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
//@RequestMapping(value = "/clients")
public class ClientController {

    //Spring 3.x exclui o value do @GetMapping caso só haja um endpoint? Ou não haja um @PathVariable
    @GetMapping(value = "/clients")
    public List<Client> clientsList() {
        Client client1 = new Client(1L, "João", "34 99999-1111","joaodascouves@email.com");
        Client client2 = new Client(2L, "Maria", "11 98888-1234","marianeves@email.com");

        return Arrays.asList(client1, client2);
    }
}
