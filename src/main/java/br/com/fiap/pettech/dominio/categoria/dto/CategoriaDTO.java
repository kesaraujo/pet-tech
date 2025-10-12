package br.com.fiap.pettech.dominio.categoria.dto;

import br.com.fiap.pettech.dominio.categoria.entity.Categoria;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.UUID;

public class CategoriaDTO {

    private UUID id;

    @NotBlank(message = "Informe o nome da categoria")
    private String nome;

    @NotBlank(message = "Informe a data da criação da categoria")
    private Instant dataDeCriacao;

    public CategoriaDTO() {
    }

    public CategoriaDTO(UUID id, String nome, Instant dataDeCriacao) {
        this.id = id;
        this.nome = nome;
        this.dataDeCriacao = dataDeCriacao;
    }

    public CategoriaDTO(Categoria entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.dataDeCriacao = entidade.getDataDeCriacao();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Instant getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Instant dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
}

