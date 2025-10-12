package br.com.fiap.pettech.dominio.pessoa.dto;

import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

public class PessoaDTO {

    private UUID id;

    @NotBlank(message = "Informe o nome")
    private String nome;
    private LocalDate nascimento;

    @CPF(message = "CPF inválido")
    private String cpf;

    @Email(message = "Informe e-mail no formato válido.")
    private String email;

    public PessoaDTO() {
    }

    public PessoaDTO(UUID id, String nome, LocalDate nascimento, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.email = email;
    }

    public PessoaDTO(Pessoa entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.nascimento = entity.getNascimento();
        this.cpf = entity.getCpf();;
        this.email = entity.getEmail();
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

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
