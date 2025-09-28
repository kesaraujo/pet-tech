package br.com.fiap.pettech.dominio.pessoa.repository;

import br.com.fiap.pettech.dominio.pessoa.entity.PessoaFisica;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class PessoaFisicaCollectionRepository {

    static private Set<PessoaFisica> pessoas;

    static {
        pessoas = new LinkedHashSet<>();

         PessoaFisica p1 = new PessoaFisica();
        p1.setCpf("123.456.789-12").setNome("Cesar Araujo Mota").setNascimento(LocalDate.of(1979, 05, 31));

        PessoaFisica dependente1 = new PessoaFisica();
        dependente1.setCpf("123.456.789-98").setNome("Arthur Cesar Silva Mota").setNascimento(LocalDate.of(2011, 01, 04));

        p1.addDependente(dependente1);

        save(p1);
        save(dependente1);
    }

    public Collection<PessoaFisica> findAll() {
        return pessoas;
    }

    public Optional<PessoaFisica> findById(Long id) {
        return pessoas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public static PessoaFisica save(PessoaFisica p) {
        p.setId(pessoas.size() + 1L);
        pessoas.add(p);
        return p;
    }

    public Optional<PessoaFisica> update(PessoaFisica pessoaFisica) {
        Optional<PessoaFisica> pessoaASerBuscada = this.findById(pessoaFisica.getId());

        if (pessoaASerBuscada.isPresent()) {
            PessoaFisica pessoa = pessoaASerBuscada.get();
            pessoa.setCpf(pessoaFisica.getCpf());
            pessoa.setNome(pessoaFisica.getNome());
            pessoa.setNascimento(pessoaFisica.getNascimento());

            return  Optional.of(pessoa);
        }

        return Optional.empty();
    }

    public void delete(Long id) {
        pessoas.removeIf(p -> p.getId().equals(id));
    }
}
