package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private IProdutoRepository repository;

//    @BeforeAll {
//        UUID id = UUID.fromString("4ae8615c-7fa3-4d84-a9a0-e517de0bd4e6");
//    }

    @Test
    void findAll() {
    }

    @Test
    void findByIdDeveRetornarUmProdutoDTOAoBuscarPorId() {

        UUID id = UUID.fromString("4ae8615c-7fa3-4d84-a9a0-e517de0bd4e6");

        Produto produto = new Produto();
        produto.setNome("PC");
        produto.setDescricao("PC Gamer");
        produto.setUrlImagem("URL PC Gamer");
        produto.setPreco(1500.00);
        produto.setId(null);

        Mockito.when(repository.findById((UUID) ArgumentMatchers.any())).thenReturn(Optional.of(produto));
        ProdutoDTO dto = service.findById(id);

        Assertions.assertNotNull(dto);
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}