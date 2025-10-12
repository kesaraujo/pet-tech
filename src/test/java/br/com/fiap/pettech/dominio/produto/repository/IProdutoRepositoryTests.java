package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.exception.ControllerNotFoundException;
import br.com.fiap.pettech.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class IProdutoRepositoryTests {

    @Autowired
    private IProdutoRepository produtoRepository;

    private UUID idExistente;
    private UUID idInexistente;
    private PageRequest pageRequest;
    private long countTotalProdutos;
    private String nomeAtualizado;

    @BeforeEach
    void setUp() throws Exception {
        idExistente = UUID.fromString("4ae8615c-7fa3-4d84-a9a0-e517de0bd4e6");
        idInexistente = UUID.fromString("4ae8615c-7fa3-4d84-a9a0-e517de0bd4e7");
        pageRequest = PageRequest.of(0, 10);
        countTotalProdutos = 5L;
        nomeAtualizado = "Atualização Nome do Produto";
    }

    @Test
    public void findAllDeveRetornarListaObjetosCadastrados() {
        Page produtos = produtoRepository.findAll(this.pageRequest);
        Assertions.assertEquals(produtos.getTotalElements(), this.countTotalProdutos);
    }

    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista() {
        Optional<Produto> result = produtoRepository.findById(this.idExistente);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdDeveRetornarControllerNotFoundExceptionCasoIdNaoExista() {
        Optional<Produto> result = produtoRepository.findById(this.idInexistente);
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            produtoRepository.findById(this.idInexistente).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado."));
        });
    }

    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull() {
        Produto produto = Factory.createProduto();
        produto.setId(null);
        var produtoSalvo = produtoRepository.save(produto);
        Assertions.assertNotNull(produtoSalvo.getId());
    }

    @Test
    public void saveDeveAtualizarObjetoCasoIdNaoSejaNull() {

        Produto produto = Factory.createProduto();
        produto.setId(this.idExistente);
        produto.setNome(this.nomeAtualizado);

        var produtoSalvo = produtoRepository.save(produto);
        Assertions.assertEquals(produtoSalvo.getNome(), this.nomeAtualizado);
    }

    @Test
    public void saveDeveDeletarObjetoCasoExista() {

        produtoRepository.deleteById(this.idExistente);
        Optional<Produto> result = produtoRepository.findById(this.idExistente);
        Assertions.assertFalse(result.isPresent());
    }


}
