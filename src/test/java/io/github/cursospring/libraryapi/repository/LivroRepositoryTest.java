package io.github.cursospring.libraryapi.repository;

import io.github.cursospring.libraryapi.model.Autor;
import io.github.cursospring.libraryapi.model.GeneroLivro;
import io.github.cursospring.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("84fc374f-883e-4c4f-87f5-a1cd4362c353"))
                .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);

    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1945, 9, 11));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro Livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Japones");
        autor.setDataNascimento(LocalDate.of(1945, 9, 11));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("bb1628ae-9372-4c22-a5ea-7f1b0dac47ea");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("a43e45a5-a9ff-486e-9529-9a8e7775d35d");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletarPorId() {
        UUID id = UUID.fromString("bb1628ae-9372-4c22-a5ea-7f1b0dac47ea");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivrosTest() {
        UUID id = UUID.fromString("cb3b71b2-5373-4469-beff-9fe446006151");
        Livro livro = repository.findById(id).orElse(null);

        System.out.println("Livro:");
        System.out.println(livro.getTitulo());

        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisarPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("UFO");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorIsbnTest() {
        List<Livro> lista = repository.findByIsbn("90887-84874");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloEPrecoTest() {
        var preco = BigDecimal.valueOf(330.00);
        var tituloPesquisa = "Meus 30 anos";

        List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisa, preco);
        lista.forEach(System.out::println);

    }

    @Test
    void pesquisarPorTituloOuIsbnTest() {
        var isbnPesquisa = "90887-84874";
        var tituloPesquisa = "Meus 30 anos";

        List<Livro> lista = repository.findByTituloOrIsbn(tituloPesquisa, isbnPesquisa);
        lista.forEach(System.out::println);

    }

    @Test
    void listarLivrosComQueryJPQLTest() {
        List<Livro> lista = repository.listarTodosOrdenadoPorTituloAndPreco();
        lista.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivrosTest() {
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepetidosTest() {
        var resultado = repository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosAutoresBrasileirosTest() {
        var resultado = repository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }
}