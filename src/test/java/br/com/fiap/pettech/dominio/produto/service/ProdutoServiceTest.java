package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private IProdutoRepository repository;

    private UUID idExistente;
    private UUID idInexistente;
    private PageRequest pageRequest;
    private String nomeAtualizado;

    private Produto produto;
    private ProdutoDTO produtoDTO;
    private PageImpl<Produto> page;

    public void setUp() {
        idExistente = UUID.fromString("4ae8615c-7fa3-4d84-a9a0-e517de0bd4e6");
        idInexistente = UUID.fromString("4ae8615c-7fa3-4d84-a9a0-e517de0bd4e7");;
        pageRequest = PageRequest.of(0, 10);
        produto = Factory.createProduto();
        produtoDTO = Factory.createProdutoDTO();
        page = new PageImpl<>(List.of(produto));
        nomeAtualizado = "Produto Atualizado.";

        Mockito.when(repository.findById((UUID) ArgumentMatchers.any())).thenReturn(Optional.of(produto));
        Mockito.when(repository.findAll((PageRequest) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(repository.findById(idInexistente)).thenReturn(Optional.empty());
    }

    @Test
    public void findAllDeveRetornarUmaListaDeProdutosDTO() {
        Page dto = service.findAll(this.pageRequest);
        Assertions.assertNotNull(dto);
    }

    @Test
    public  void findByIdDeveRetornarUmProdutoDTOAoBuscarPorId() {
        ProdutoDTO dto = service.findById(this.idExistente);
        Assertions.assertNotNull(dto);
    }

    @Test
    public void findByIdDeveRetornarExcecaoAoBuscarIdInexistente() {
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            service.findById(this.idInexistente);
        });
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}