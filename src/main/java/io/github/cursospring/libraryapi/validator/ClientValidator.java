package io.github.cursospring.libraryapi.validator;

import io.github.cursospring.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.cursospring.libraryapi.model.Client;
import io.github.cursospring.libraryapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidator {

    private final ClientRepository repository;

    public Client validar(Client client) {
        if(existeClientIdCadastrado(client.getClientId())) {
            throw new RegistroDuplicadoException("Client Id já cadastrado no sistema.");
        }

        return client;
    }

    private boolean existeClientIdCadastrado(String clientId) {
        return repository.existsByClientId(clientId);
    }
}
