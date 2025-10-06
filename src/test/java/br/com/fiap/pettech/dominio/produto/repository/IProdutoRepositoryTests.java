package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.exception.ControllerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class IProdutoRepositoryTests {

    @Autowired
    private IProdutoRepository produtoRepository;

    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista() {
        UUID id = UUID.fromString("4ae8615c-7fa3-4d84-a9a0-e517de0bd4e6");
        Optional<Produto> result = produtoRepository.findById(id);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdDeveRetornarControllerNotFoundExceptionCasoIdNaoExista() {
        UUID id = UUID.fromString("4ae8615c-7fa3-4d84-a9a0-e517de0bd4e7");
        Optional<Produto> result = produtoRepository.findById(id);
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            produtoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado."));
        });
    }

    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull() {
        Produto produto = new Produto();
        produto.setNome("PC");
        produto.setDescricao("PC Gamer");
        produto.setUrlImagem("URL PC Gamer");
        produto.setPreco(1500.00);
        produto.setId(null);

        var produtoSalvo = produtoRepository.save(produto);
        Assertions.assertNotNull(produtoSalvo.getId());
    }
}
