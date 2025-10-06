package br.com.fiap.pettech.dominio.produto.controller;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.dominio.produto.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProdutoService service;

    @Mock
    private IProdutoRepository repository;

    @Test
    void findAll() {
    }

    @Test
    void findByIdDeveRetornarProdutoDTOCasoIdExista() throws Exception {

        UUID id = UUID.fromString("4ae8615c-7fa3-4d84-a9a0-e517de0bd4e6");

        ProdutoDTO dto = new ProdutoDTO();
        dto.setNome("PC");
        dto.setDescricao("PC Gamer");
        dto.setUrlImage("URL PC Gamer");
        dto.setPreco(1500.00);
        dto.setId(id);

        Mockito.when(service.findById(id)).thenReturn(dto);

        ResultActions result = mockMvc.perform(get("/produtos/{id}", id).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
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