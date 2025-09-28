package br.com.fiap.pettech.dominio.pessoa.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Vector;

public class PessoaJuricia extends Pessoa {

    private String cnpj;
    private Collection<Pessoa> socios = new Vector<>();

    public PessoaJuricia() {
    }

    public PessoaJuricia(Long id, String nome, LocalDate nascimento, String cnpj, Collection<Pessoa> socios) {
        super(id, nome, nascimento);
        this.cnpj = cnpj;
        this.socios = socios;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public PessoaJuricia addSocio(Pessoa p) {
        //TODO: Não pode ser a mesma pessoa
        socios.add(p);
        return this;
    }

    public PessoaJuricia removerSocio(Pessoa p) {
        socios.remove(p);
        return this;
    }

    public Collection<Pessoa> getSocios() {
        return Collections.unmodifiableCollection(socios);
    }

    @Override
    public String toString() {
        return "PessoaJuricia{" +
                "cnpj='" + cnpj + '\'' +
                ", socios=" + socios +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PessoaJuricia that = (PessoaJuricia) o;
        return Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(cnpj);
        return result;
    }
}
