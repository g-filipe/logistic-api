package com.gfilipeprojects.logisticapi.repository;

import com.gfilipeprojects.logisticapi.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
