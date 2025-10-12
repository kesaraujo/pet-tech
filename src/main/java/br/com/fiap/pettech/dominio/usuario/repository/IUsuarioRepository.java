package br.com.fiap.pettech.dominio.usuario.repository;

import br.com.fiap.pettech.dominio.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, UUID> {

}
