package br.com.fiap.pettech.dominio.usuario.service;

import br.com.fiap.pettech.dominio.usuario.dto.UsuarioDTO;
import br.com.fiap.pettech.dominio.usuario.entity.Usuario;
import br.com.fiap.pettech.dominio.usuario.repository.IUsuarioRepository;
import br.com.fiap.pettech.exception.ControllerNotFoundException;
import br.com.fiap.pettech.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository repository;

    public Page<UsuarioDTO> findAll(PageRequest pagina) {
        var usuarios = repository.findAll(pagina);
        return usuarios.map(u -> new UsuarioDTO(u));
    }

    public UsuarioDTO findById(UUID id) {
        var usuario = repository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Usuário não encontrado."));
        return new UsuarioDTO(usuario);
    }

    public UsuarioDTO save(@Valid UsuarioDTO dto) {
        Usuario entity = new Usuario();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());

        var usuarioSaved = repository.save(entity);
        return new UsuarioDTO(usuarioSaved);
    }

    public UsuarioDTO update(UUID id, UsuarioDTO dto) {
        try {
            Usuario buscausuario = repository.getReferenceById(id);
            buscausuario.setUsername(dto.getUsername());
            buscausuario.setPassword(dto.getPassword());
            buscausuario = repository.save(buscausuario);
            return new UsuarioDTO(buscausuario);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não encontrado." + id);
        }
    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entidade não encontrada. " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade de base. " + id);
        }
    }
}
