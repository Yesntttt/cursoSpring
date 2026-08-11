package io.github.cursospring.libraryapi.service;

import io.github.cursospring.libraryapi.controller.dto.AutorDTO;
import io.github.cursospring.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.cursospring.libraryapi.model.Autor;
import io.github.cursospring.libraryapi.repository.AutorRepository;
import io.github.cursospring.libraryapi.repository.LivroRepository;
import io.github.cursospring.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;
    private final AutorValidator validator;
    private final LivroRepository livroRepository;

    public Autor salvar(Autor autor) {
        validator.validar(autor);
        return repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(UUID id) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado!"));

        if(possuiLivro(autor)) {
            throw new OperacaoNaoPermitidaException(
                    "Não é permitido deletar um autor que possui um livro."
            );
        }

        repository.delete(autor);
    }

    public List<AutorDTO> pesquisar(String nome, String nacionalidade) {
        List<Autor> autores = repository.findByNomeAndNacionalidade(nome, nacionalidade);

        return autores.stream()
                .map(autor -> new AutorDTO(
                        autor.getId(),
                        autor.getNome(),
                        autor.getDataNascimento(),
                        autor.getNacionalidade()))
                .toList();
    }

    public void atualizar(UUID id, AutorDTO dto) {
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

        autor.setNome(dto.nome());
        autor.setDataNascimento(dto.dataNascimento());
        autor.setNacionalidade(dto.nacionalidade());

        validator.validar(autor);

        repository.save(autor);
    }

    public boolean possuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);
    }
}
