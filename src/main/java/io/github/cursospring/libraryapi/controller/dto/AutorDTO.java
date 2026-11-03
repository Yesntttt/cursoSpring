package io.github.cursospring.libraryapi.controller.dto;

import io.github.cursospring.libraryapi.model.Autor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Autor")
public record AutorDTO(
        UUID id,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão")
        @Schema(name = "nome")
        String nome,
        @NotNull(message = "Campo obrigatório")
        @Past(message = "Não pode ser uma data futura")
        @Schema(name = "dataNascimento")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 2, max = 50, message = "Campo fora do tamanho padrão")
        @Schema(name = "nacionalidade")
        String nacionalidade) {

    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
