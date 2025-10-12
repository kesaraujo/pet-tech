package br.com.fiap.pettech.dominio.pessoa.repository;

import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, UUID> {
}
