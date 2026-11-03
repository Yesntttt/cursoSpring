package io.github.cursospring.libraryapi.service;

import io.github.cursospring.libraryapi.controller.dto.ClientDTO;
import io.github.cursospring.libraryapi.controller.mappers.ClientMapper;
import io.github.cursospring.libraryapi.model.Client;
import io.github.cursospring.libraryapi.repository.ClientRepository;
import io.github.cursospring.libraryapi.validator.ClientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;
    private final ClientValidator validator;
    private final PasswordEncoder encoder;
    private final ClientMapper mapper;

    public void salvar(Client client) {
        validator.validar(client);

        var senhaCrip = encoder.encode(client.getClientSecret());
        client.setClientSecret(senhaCrip);

        repository.save(client);
    }

    public Client obterPorClientId(String clientId) {
        return repository.findByClientId(clientId);
    }
}
