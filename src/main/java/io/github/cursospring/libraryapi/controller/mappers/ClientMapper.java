package io.github.cursospring.libraryapi.controller.mappers;

import io.github.cursospring.libraryapi.controller.dto.ClientDTO;
import io.github.cursospring.libraryapi.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientDTO dto);

    ClientDTO toDTO(Client client);
}
