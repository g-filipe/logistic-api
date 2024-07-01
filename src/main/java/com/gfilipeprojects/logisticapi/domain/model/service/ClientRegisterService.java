package com.gfilipeprojects.logisticapi.domain.model.service;

import com.gfilipeprojects.logisticapi.domain.model.Client;
import com.gfilipeprojects.logisticapi.domain.model.exception.DomainException;
import com.gfilipeprojects.logisticapi.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClientRegisterService {
    private ClientRepository clientRepository;

    @Transactional
    public Client saveClient(Client client) {
       boolean duplicatedEmail = clientRepository.findByEmail(client.getEmail())
               .stream()
               .anyMatch(clientEmail -> !clientEmail.equals(client));

       if (duplicatedEmail) {
           throw new DomainException("This email is already in use!");
       }
        return clientRepository.save(client);
    }
    @Transactional
    public void removeClientById(Long clientId) {
        clientRepository.deleteById(clientId);
    }

}
