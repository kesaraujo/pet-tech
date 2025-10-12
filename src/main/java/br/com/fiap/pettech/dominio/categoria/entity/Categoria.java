package br.com.fiap.pettech.dominio.categoria.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataDeCriacao;

    public Categoria() {
    }

    public Categoria(String nome, Instant dataDeCriacao) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.dataDeCriacao = dataDeCriacao;
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

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataDeCriacao=" + dataDeCriacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
