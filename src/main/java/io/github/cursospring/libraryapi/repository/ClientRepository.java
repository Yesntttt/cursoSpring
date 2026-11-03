package io.github.cursospring.libraryapi.repository;

import io.github.cursospring.libraryapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    public Client findByClientId(String clientId);
    public Boolean existsByClientId(String clientId);
}
