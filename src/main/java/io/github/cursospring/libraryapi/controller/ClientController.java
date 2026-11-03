package io.github.cursospring.libraryapi.controller;

import io.github.cursospring.libraryapi.model.Client;
import io.github.cursospring.libraryapi.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    public void salvar(@RequestBody @Valid Client client) {
        log.info("Cadastrando novo Client: {} com scope: {}", client.getClientId(), client.getScope());
        service.salvar(client);
    }
}
